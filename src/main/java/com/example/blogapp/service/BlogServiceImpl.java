package com.example.blogapp.service;

import com.example.blogapp.model.Blog;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public String saveBlog(Blog blogToSave) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Blog blog = Blog.builder()
                .title(blogToSave.getTitle())
                .user(userRepository.findById(loggedUser).orElseThrow())
                .build();
        return "Saved, id: " + blogRepository.save(blog).getId();
    }
}
