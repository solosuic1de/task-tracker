package com.testproject.tasktracker.model.domain.exception;

public class UserExistException extends RuntimeException {
    public UserExistException(String email) {
        super("User with email " + email + " already exist");
    }
}