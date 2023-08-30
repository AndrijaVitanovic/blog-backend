package com.blog.service;

import com.blog.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post save(Post post);

    Post update(Post post);

    Post findById(Long postId);

    void deleteById(Long postId);

    void upvote(Post post);

    void downvote(Post post);

    List<Post> findByCategoryId(Long categoryId);
}

