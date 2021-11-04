package com.example.blogapp;

import com.example.blogapp.model.User;
import com.example.blogapp.model.UserAuthority;
import com.example.blogapp.repository.UserAuthorityRepository;
import com.example.blogapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BlogAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApplication.class, args);
    }
}
