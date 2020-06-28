package com.testproject.tasktracker.model.error;

import org.springframework.http.HttpStatus;

public interface ApiError {
    HttpStatus getStatus();
}
