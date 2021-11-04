package com.example.blogapp.controller;

import com.example.blogapp.model.Blog;
import com.example.blogapp.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public List<Blog> getBlogs() {
        return blogService.getAll();
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/blogs")
    public String register(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }
}
