package com.testproject.tasktracker.controller;

import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.security.jwt.JwtUser;
import com.testproject.tasktracker.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;
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

    @PostMapping("/update")
    public ResponseEntity<User> update(@AuthenticationPrincipal JwtUser userPrincipal, @RequestBody User user) {
        user.setId(userPrincipal.getId());
        return ResponseEntity.ok(userService.update(user));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal JwtUser userPrincipal) {
        SecurityContextHolder.clearContext();
        userService.deleteById(userPrincipal.getId());
        return new ResponseEntity(HttpStatus.OK);
    }


}
