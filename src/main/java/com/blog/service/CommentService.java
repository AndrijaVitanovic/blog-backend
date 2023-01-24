package com.blog.service;

import com.blog.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment save(Comment comment);

    Comment update(Comment comment);

    Comment findById(Long commentId);

    void deleteById(Long commentId);
}
