package com.blog.service;

import com.blog.entity.Post;
import com.blog.entity.Tag;

import java.util.List;

public interface TagService {

    List<Tag> findAll();

    Tag save(Tag tag);

    Tag update(Tag tag);

    Tag findById(Long tagId);

    void deleteById(Long tagId);

    List<Post> findPostsByTagId(Long tagId);
}
