package com.example.blogapp.controller;

import com.example.blogapp.model.Blog;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BlogControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void getBlogs() {
        // given
        String newBlogUser = "admin";
        String newBloTitle = "title1";
        blogRepository.save(Blog.builder()
                .user(userRepository.getById(newBlogUser))
                .title(newBloTitle)
                .build()
        );
        HttpEntity<String> requestWithCredentials = new HttpEntity<>(AuthUtil.getBasicAuthHeaders());

        // when
        ResponseEntity<Blog[]> response = restTemplate.exchange("/blogs", HttpMethod.GET, requestWithCredentials, Blog[].class);

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Blog[] responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        Assertions.assertEquals(1, responseBody.length);
        Assertions.assertEquals(newBloTitle, responseBody[0].getTitle());
        Assertions.assertEquals(newBlogUser, responseBody[0].getUser().getUsername());

        // clean up
        blogRepository.deleteAll();
    }

    @Test
    void saveBlog() {
        // given
        String newBlogTitle = "title1";
        Blog newBlog = Blog.builder()
                .title(newBlogTitle)
                .build();
        HttpEntity<Blog> requestWithCredentials = new HttpEntity<>(newBlog, AuthUtil.getBasicAuthHeaders());

        // when
        ResponseEntity<String> response = restTemplate.exchange("/blogs", HttpMethod.POST, requestWithCredentials, String.class);

        // then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        String responseMessage = response.getBody();
        Assertions.assertNotNull(responseMessage);
        Assertions.assertTrue(responseMessage.startsWith("Saved"));
        Assertions.assertEquals(newBlogTitle, blogRepository.findAll().get(0).getTitle());

        // clean up
        blogRepository.deleteAll();
    }
}
