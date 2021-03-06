package com.testproject.tasktracker.controller;

import com.testproject.tasktracker.model.domain.entity.User;
import com.testproject.tasktracker.model.security.jwt.JwtUser;
import com.testproject.tasktracker.model.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @ApiOperation(value = "Returns list of all users in database", authorizations = {@Authorization(value = "Bearer")})
    @GetMapping("/all")

    public ResponseEntity<List<User>> getAll(@PageableDefault() Pageable pageable) {
        Page<User> pages = userService.getAll(pageable);
        return ResponseEntity.ok(pages.get().collect(Collectors.toList()));
    }

    @ApiOperation(value = "Update logged user. RELOGIN REQUIRED IF EMAILS WAS CHANGED", authorizations = {@Authorization(value = "Bearer")})
    @PutMapping("/update")
    public ResponseEntity<User> update(@AuthenticationPrincipal JwtUser userPrincipal, @RequestBody User user) {
        user.setId(userPrincipal.getId());
        return ResponseEntity.ok(userService.update(user));
    }

    @ApiOperation(value = "Delete logged user. RELOGIN REQUIRED IF USER WAS DELETED", authorizations = {@Authorization(value = "Bearer")})
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@AuthenticationPrincipal JwtUser userPrincipal) {
        SecurityContextHolder.clearContext();
        userService.deleteById(userPrincipal.getId());
        return new ResponseEntity(HttpStatus.OK);
    }


}
