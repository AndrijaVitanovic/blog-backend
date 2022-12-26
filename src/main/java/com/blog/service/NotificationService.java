package com.blog.service;

import com.blog.entity.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface NotificationService {

    List<Notification> findAll();

    List<Notification> findAll(Specification<Notification> specification, Pageable pageable);

    void markSeen(Long notificationId);

    Notification findById(Long notificationId);
}
