package com.testproject.tasktracker.model.security.jwt;

public class JwtAuthenticationException extends RuntimeException {
    private String token;

    public JwtAuthenticationException(String msg, Throwable t, String token) {
        super(msg, t);
        this.token = token;
    }

    public JwtAuthenticationException(String msg, String token) {
        super(msg);
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
