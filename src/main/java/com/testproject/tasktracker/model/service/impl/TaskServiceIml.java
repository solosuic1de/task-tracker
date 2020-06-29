package com.testproject.tasktracker.model.service.impl;

import com.testproject.tasktracker.model.domain.entity.Task;
import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.domain.enums.Sort;
import com.testproject.tasktracker.model.domain.enums.Status;
import com.testproject.tasktracker.model.domain.exception.TaskNotFoundException;
import com.testproject.tasktracker.model.domain.exception.TaskPermissionDeniedException;
import com.testproject.tasktracker.model.repository.TaskRepository;
import com.testproject.tasktracker.model.service.TaskService;
import com.testproject.tasktracker.model.service.UserService;
import com.testproject.tasktracker.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TaskServiceIml implements TaskService {
    private TaskRepository taskRepository;
    private UserService userService;

    @Autowired
    public TaskServiceIml(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public Task create(Task task, long userId) {
        User user = userService.findById(userId);
        task.setUser(user);
        log.info("try to save task {}", task.getTitle());
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task, long userId) {
        Task taskToUpdate = getAndValidatePermissions(task.getId(), userId);
        PropertyUtils.copyNotNullFields(task, taskToUpdate);
        log.info("try to update task with id: {}", task.getId());
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public Task updateStatus(long taskId, long userId, Status status) {
        Task task = getAndValidatePermissions(taskId, userId);
        task.setStatus(status);
        log.info("try to update status in task with id: {}", taskId);
        return taskRepository.save(task);

    }

    @Override
    public void deleteTask(long taskId, long userId) {
        Task task = getAndValidatePermissions(taskId, userId);
        log.info("try to delete task with id: {}", taskId);
        taskRepository.deleteById(taskId);
    }

    @Override
    public Task assignToAnotherUser(long taskId, long oldOwnerId, String newUserEmail) {
        Task task = getAndValidatePermissions(taskId, oldOwnerId);
        User user = userService.findByEmail(newUserEmail);
        task.setUser(user);
        log.info("try to set new user to task with id {}", task.getId());
        return taskRepository.save(task);
    }

    @Override
    public Task getById(long taskId) {
        log.info("try to get task by id: {}", taskId);
        return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
    }

    @Override
    public Page<Task> getAll(Pageable pageable) {
        log.info("in TaskService.getAll function");
        return taskRepository.findAll(pageable);
    }

    @Override
    public Page<Task> getAllByStatus(Status status, Pageable pageable) {
        log.info("try to get tasks with status {}", status);
        return taskRepository.findAllByStatus(status, pageable);
    }

    @Override
    public Page<Task> getAllSortedByUser(Sort sort, Pageable pageable) {
        log.info("try to get tasks sorted by user in order{}", sort.name());
        List<Task> taskList;
        if (sort.equals(Sort.DESC)) {
            taskList = taskRepository.getAllByUserDesc();
            return new PageImpl<>(taskList,
                    PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), taskList.size());
        }
        taskList = taskRepository.getAllByUserAsc();
        return new PageImpl<>(taskList,
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()), taskList.size());
    }

    private Task getAndValidatePermissions(long taskId, long userId) {
        Task task = getById(taskId);
        if (task.getUser().getId() != userId) {
            log.warn("user with id: {} cant acces to task with id{}", userId, taskId);
            throw new TaskPermissionDeniedException();
        }
        return task;
    }
}
