package com.blog.service;

import com.blog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post save(Post post);

    Post update(Post post);

    Post findById(Long postId);

    void deleteById(Long postId);
}

