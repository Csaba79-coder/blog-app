package com.example.blogapp.service;

import com.example.blogapp.dto.RegisterUserRequest;
import com.example.blogapp.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getByUsername(String username);

    String register(RegisterUserRequest user);
}
