package com.testproject.tasktracker.model.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("No user was found with email " + email);
    }

    public UserNotFoundException(Long id) {
        super("No user was found with id " + id);
    }
}