package com.testproject.tasktracker.model.service;

import com.testproject.tasktracker.model.domain.entity.Task;
import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.domain.enums.Sort;
import com.testproject.tasktracker.model.domain.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface TaskService {
    Task create(Task task, long userId);

    Task update(Task task, long userId);

    Task updateStatus(long taskId, long userId, Status status);

    void deleteTask(long taskId, long userId);

    Task assignToAnotherUser(long taskId, long oldOwnerId, String userEmail);

    Task getById(long id);

    Page<Task> getAll(Pageable pageable);

    Page<Task> getAllByStatus(Status status, Pageable pageable);

    Page<Task> getAllSortedByUser(Sort sort, Pageable pageable);
}
