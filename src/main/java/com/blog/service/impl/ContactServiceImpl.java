package com.blog.service.impl;

import com.blog.entity.Contact;
import com.blog.repository.ContactRepository;
import com.blog.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact findById(Long contactId) {
        return contactRepository.findById(contactId)
                .orElseThrow(() -> new NoSuchElementException("Contact with that ID not found"));
    }

    @Override
    public void deleteById(Long contactId) {
        contactRepository.deleteById(contactId);
    }
}
