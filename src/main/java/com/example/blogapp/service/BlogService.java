package com.example.blogapp.service;

import com.example.blogapp.model.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAll();

    String saveBlog(Blog blog);
}
