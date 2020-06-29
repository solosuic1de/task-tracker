package com.testproject.tasktracker.model.domain.exception;

public class TaskPermissionDeniedException extends RuntimeException {
    public TaskPermissionDeniedException() {
        super("You cannot edit/delete someone else's task");
    }
}
