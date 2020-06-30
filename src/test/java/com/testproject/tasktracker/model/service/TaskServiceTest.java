package com.testproject.tasktracker.model.service;

import com.testproject.tasktracker.model.domain.entity.Task;
import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.domain.enums.Status;
import com.testproject.tasktracker.model.domain.exception.TaskPermissionDeniedException;
import com.testproject.tasktracker.model.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.swing.text.html.Option;

import java.util.Optional;

import static com.testproject.tasktracker.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class TaskServiceTest {
    @Autowired
    private TaskService taskService;
    @MockBean
    private UserService userService;
    @MockBean
    private TaskRepository taskRepository;
    private User user;
    private Task task;

    @BeforeEach
    public void initUser() {
        user = User.builder().firstName(TEST_NAME)
                .id(TEST_ID)
                .lastName(TEST_LAST_NAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .build();
    }

    @BeforeEach
    public void initTask() {
        task = Task.builder()
                .id(TEST_ID)
                .title(TEST_TASK_TITLE)
                .description(TEST_TASK_DESCRIPTION)
                .status(TEST_TASK_STATUS)
                .build();
    }

    @Test
    public void createTaskReturnTaskWhenTaskAddedToDataBase() {
        Mockito.doReturn(user)
                .when(userService)
                .findById(TEST_ID);
        Mockito.doReturn(task)
                .when(taskRepository)
                .save(ArgumentMatchers.any(Task.class));
        Task savedTask = taskService.create(task, TEST_ID);
        Mockito.verify(taskRepository, Mockito.times(1)).save(ArgumentMatchers.any(Task.class));
        Mockito.verify(userService, Mockito.times(1)).findById(TEST_ID);
        assertEquals(TEST_TASK_TITLE, savedTask.getTitle());
    }

    @Test
    public void whenUpdateStatusThenReturnTaskWithUpdatedStatus() {
        task.setUser(user);
        Mockito.doReturn(Optional.of(task))
                .when(taskRepository)
                .findById(TEST_ID);
        Mockito.doReturn(task)
                .when(taskRepository)
                .save(task);
        Task savedTask = taskService.updateStatus(TEST_ID, TEST_ID, TEST_TASK_STATUS);
        Mockito.verify(taskRepository, Mockito.times(1)).save(task);
        Mockito.verify(taskRepository, Mockito.times(1)).findById(TEST_ID);
        assertEquals(savedTask.getStatus(), TEST_TASK_STATUS);

    }

    @Test
    public void whenUpdateStatusWithAnotherUserIdThenThrowException() {
        user.setId(0);
        task.setUser(user);
        Mockito.doReturn(Optional.of(task))
                .when(taskRepository)
                .findById(TEST_ID);
        assertThrows(TaskPermissionDeniedException.class, () ->
                taskService.updateStatus(TEST_ID, TEST_ID, TEST_TASK_STATUS));
        Mockito.verify(taskRepository, Mockito.times(1)).findById(TEST_ID);

    }

}