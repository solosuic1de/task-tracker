package com.testproject.tasktracker.model.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserApiError implements ApiError {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private UserApiError() {
        timestamp = LocalDateTime.now();
    }

    public UserApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public UserApiError(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }
}
