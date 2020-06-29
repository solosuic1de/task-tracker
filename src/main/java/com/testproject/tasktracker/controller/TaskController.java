package com.testproject.tasktracker.controller;

import com.testproject.tasktracker.model.domain.dto.AssignToUserRequest;
import com.testproject.tasktracker.model.domain.dto.UpdateTaskRequest;
import com.testproject.tasktracker.model.domain.dto.UpdateTaskStatusRequests;
import com.testproject.tasktracker.model.domain.entity.Task;
import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.domain.enums.Sort;
import com.testproject.tasktracker.model.domain.enums.Status;
import com.testproject.tasktracker.model.security.jwt.JwtUser;
import com.testproject.tasktracker.model.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private TaskService taskService;


    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ApiOperation(value = "Get all tasks", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks(@PageableDefault() Pageable pageable) {
        Page<Task> pages = taskService.getAll(pageable);
        return ResponseEntity.ok(pages.get().collect(Collectors.toList()));
    }

    @ApiOperation(value = "Get list of tasks filtered by status", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/filter/status")
    public ResponseEntity<List<Task>> getAllTasksByStatus(@PageableDefault() Pageable pageable,
                                                          @RequestParam Status status) {
        Page<Task> pages = taskService.getAllByStatus(status, pageable);
        return ResponseEntity.ok(pages.get().collect(Collectors.toList()));
    }

    @ApiOperation(value = "Get list of tasks, sorted by userId", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/sort/user")
    public ResponseEntity<List<Task>> getAllTaskSortedByUser(@PageableDefault() Pageable pageable,
                                                             @RequestParam Sort order) {
        Page<Task> pages = taskService.getAllSortedByUser(order, pageable);
        return ResponseEntity.ok(pages.get().collect(Collectors.toList()));
    }

    @ApiOperation(value = "Change task user", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/update/reassign")
    public ResponseEntity<Task> assignToAnotherUser(@AuthenticationPrincipal JwtUser userPrincipal,
                                                    @RequestBody AssignToUserRequest assignDto) {
        return ResponseEntity.ok(taskService.assignToAnotherUser(assignDto.getTaskId(), userPrincipal.getId(), assignDto.getEmail()));
    }

    @ApiOperation(value = "Update task status", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/update/status")
    public ResponseEntity<Task> updateStatus(@AuthenticationPrincipal JwtUser userPrincipal,
                                             @RequestBody UpdateTaskStatusRequests updateTaskStatusRequests) {
        return ResponseEntity.ok(taskService.updateStatus(updateTaskStatusRequests.getTaskId(),
                userPrincipal.getId(), updateTaskStatusRequests.getStatus()));
    }

    @ApiOperation(value = "Create new task", authorizations = {@Authorization(value = "Bearer")})
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@AuthenticationPrincipal JwtUser userPrincipal,
                                           @RequestBody Task task) {
        return ResponseEntity.ok(taskService.create(task, userPrincipal.getId()));
    }

    @ApiOperation(value = "Update task", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@AuthenticationPrincipal JwtUser userPrincipal,
                                           @RequestBody UpdateTaskRequest updateTaskRequest) {
        Task task = Task.builder()
                .description(updateTaskRequest.getDescription())
                .title(updateTaskRequest.getTitle())
                .id(updateTaskRequest.getId())
                .build();
        return ResponseEntity.ok(taskService.update(task, userPrincipal.getId()));
    }

    @ApiOperation(value = "Delete task", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTask(@AuthenticationPrincipal JwtUser userPrincipal,
                                        @RequestParam long id) {
        taskService.deleteTask(id, userPrincipal.getId());
        return new ResponseEntity("deleted", HttpStatus.OK);
    }

}
