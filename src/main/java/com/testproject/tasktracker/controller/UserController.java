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
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAll(@PageableDefault() Pageable pageable) {
        Page<User> pages = userService.getAll(pageable);
        return ResponseEntity.ok(pages.get().collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PostMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {

        return ResponseEntity.ok(userService.update(user));
    }


    @GetMapping("/find")
    public ResponseEntity<User> getByEmail(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
