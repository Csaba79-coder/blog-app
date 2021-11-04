package com.example.blogapp.controller;

import com.example.blogapp.dto.RegisterUserRequest;
import com.example.blogapp.model.User;
import com.example.blogapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Object getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterUserRequest user) {
        return userService.register(user);
    }
}
