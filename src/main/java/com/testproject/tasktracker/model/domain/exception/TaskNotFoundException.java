package com.testproject.tasktracker.model.domain.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("No task was found with id " + id);
    }
}
