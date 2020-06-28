package com.testproject.tasktracker.controller;

import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        return ResponseEntity.of(userService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody User user) {
        userService.create(user);
        return ResponseEntity.ok("User with email: " + user.getEmail() + " was successfully created");
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity.ok("User with id: " + user.getId() + " was successfully updated");
    }

    @GetMapping("")
    public List<User> getAll(@PageableDefault() Pageable pageable) {
        Page<User> pages = userService.getAll(pageable);
        return pages.get().collect(Collectors.toList());
    }
}
