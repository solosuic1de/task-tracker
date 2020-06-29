package com.testproject.tasktracker.controller;

import com.testproject.tasktracker.model.domain.exception.UserExistException;
import com.testproject.tasktracker.model.domain.exception.UserNotFoundException;
import com.testproject.tasktracker.model.error.ApiError;
import com.testproject.tasktracker.model.error.JwtApiError;
import com.testproject.tasktracker.model.error.UserApiError;
import com.testproject.tasktracker.model.security.jwt.JwtAuthenticationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(JwtAuthenticationException.class)
    protected ResponseEntity<ApiError> handleJwtAuthError(
            JwtAuthenticationException ex) {
        return buildResponse(JwtApiError.builder()
                .status(HttpStatus.FORBIDDEN)
                .token(ex.getToken())
                .exception(ex.getMessage())
                .build());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<ApiError> handleValidationError(
            ConstraintViolationException ex) {
        List<String> stringList = new ArrayList<>();
        ex.getConstraintViolations().stream().forEach(x -> stringList.add(x.getMessage()));
        return buildResponse(new UserApiError(HttpStatus.BAD_REQUEST, stringList.toString()));
    }

    @ExceptionHandler({UserNotFoundException.class})
    protected ResponseEntity<ApiError> handleUserNotFound(
            UserNotFoundException ex) {
        return buildResponse(new UserApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler({UserExistException.class})
    protected ResponseEntity<ApiError> handleUserExistException(
            UserExistException ex) {
        return buildResponse(new UserApiError(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    private ResponseEntity<ApiError> buildResponse(ApiError errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
