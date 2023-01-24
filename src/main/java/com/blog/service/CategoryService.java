package com.blog.service;

import com.blog.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category save(Category category);

    Category update(Category category);

    Category findById(Long categoryId);

    void deleteById(Long categoryId);
}
