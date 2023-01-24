package com.blog.service.impl;

import com.blog.entity.Tag;
import com.blog.repository.TagRepository;
import com.blog.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag findById(Long tagId) {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new NoSuchElementException("Tag with that ID not found"));
    }

    @Override
    public void deleteById(Long tagId) {
        tagRepository.deleteById(tagId);
    }
}
