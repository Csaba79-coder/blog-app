package com.example.blogapp.model;

import com.example.blogapp.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BlogTest {

    @Autowired
    BlogRepository blogRepository;

    @Test
    void creatBlogTest() {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("First blog");
        blog.setUser(User.builder().build());
        assertNotNull(blogRepository.findAll());
    }

}