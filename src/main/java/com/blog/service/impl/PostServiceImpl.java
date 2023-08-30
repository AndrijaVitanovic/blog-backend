package com.blog.service.impl;

import com.blog.entity.Post;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new NoSuchElementException("Post with that ID not found"));
    }

    @Override
    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public void upvote(Post post) {
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    @Override
    public void downvote(Post post) {
        post.setLikes(post.getLikes() - 1);
        postRepository.save(post);
    }

    @Override
    public List<Post> findByCategoryId(Long categoryId) {
        return postRepository.findAllByCategory_Id(categoryId);
    }
}
