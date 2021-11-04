package com.example.blogapp.model;

import com.example.blogapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void createUserTest() {
        assertNotNull(userRepository.findAll().get(0));
        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void createNewUserTest() {
        User user = new User();
        user.setUsername("Csaba");
        user.setPassword("1234");
        user.setEnabled(true);
        assertNotNull(userRepository.findAll());
        userRepository.save(user);
        assertEquals(2, userRepository.findAll().size());
    }
}