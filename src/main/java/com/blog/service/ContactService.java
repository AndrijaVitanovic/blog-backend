package com.blog.service;

import com.blog.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    Contact save(Contact contact);

    Contact update(Contact contact);

    Contact findById(Long contactId);

    void deleteById(Long contactId);
}

