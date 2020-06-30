package com.testproject.tasktracker.controller;

import com.testproject.tasktracker.model.domain.dto.AuthRequest;
import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.security.jwt.JwtUser;
import com.testproject.tasktracker.model.service.UserService;
import com.testproject.tasktracker.model.security.jwt.JwtTokenProvider;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ApiOperation(value = "Login with existing user")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequestDto) {
        try {
            String email = authRequestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, authRequestDto.getPassword()));
            String token = jwtTokenProvider.createToken(email);

            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException ex) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @ApiOperation(value = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @ApiOperation(value = "Get user info", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal JwtUser userPrincipal) {
        return ResponseEntity.ok(userService.findById(userPrincipal.getId()));
    }
}
