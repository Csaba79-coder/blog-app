package com.example.blogapp.service;

import com.example.blogapp.dto.RegisterUserRequest;
import com.example.blogapp.model.User;
import com.example.blogapp.model.UserAuthority;
import com.example.blogapp.repository.UserAuthorityRepository;
import com.example.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserAuthorityRepository userAuthorityRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserAuthorityRepository userAuthorityRepository) {
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findById(username).orElseThrow();
    }

    @Override
    public String register(RegisterUserRequest user) {
        Optional<User> existingUser = userRepository.findById(user.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("username is already in use");
        }
        userRepository.save(User.builder()
                .username(user.getUsername())
                .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                .enabled(true)
                .build()
        );
        userAuthorityRepository.save(UserAuthority.builder()
                .username(user.getUsername())
                .authority("ROLE_USER")
                .build()
        );
        return "registered";
    }
}
