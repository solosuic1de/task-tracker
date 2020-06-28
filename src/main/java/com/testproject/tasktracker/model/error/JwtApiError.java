package com.testproject.tasktracker.model.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class JwtApiError implements ApiError {
    private String token;
    private HttpStatus status;
    private String exception;
}
