package com.testproject.tasktracker.controller;

import com.testproject.tasktracker.model.domain.dto.AuthRequest;
import com.testproject.tasktracker.security.jwt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private JwtUtils utils;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(JwtUtils utils, AuthenticationManager authenticationManager) {
        this.utils = utils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping()
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        return ResponseEntity.ok(utils.generateToken(authRequest.getEmail()));
    }
}
