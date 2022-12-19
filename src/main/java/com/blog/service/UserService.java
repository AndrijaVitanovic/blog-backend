package com.blog.service;

import com.blog.data.RegisterUserDto;
import com.blog.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByEmail(String email);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

    void register(RegisterUserDto user);

}
