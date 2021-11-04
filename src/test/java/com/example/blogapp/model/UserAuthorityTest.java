package com.example.blogapp.model;

import com.example.blogapp.repository.UserAuthorityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserAuthorityTest {

    @Autowired
    UserAuthorityRepository userAuthorityRepository;

    @Test
    void createUserAuthorityTest() {
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setId(1L);
        userAuthority.setUsername("Csaba");
        userAuthority.setAuthority("ROLE_ADMIN");
        assertNotNull(userAuthorityRepository.findAll());
        userAuthorityRepository.save(userAuthority);
        assertEquals(1, userAuthorityRepository.findAll().size());
    }
}