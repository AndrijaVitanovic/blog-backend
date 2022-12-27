package com.blog.service.impl;

import com.blog.entity.Notification;
import com.blog.repository.NotificationRepository;
import com.blog.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final Clock clock;

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> findAll(Specification<Notification> specification, Pageable pageable) {
        return notificationRepository.findAll(specification, pageable).toList();
    }

    @Override
    public void markSeen(Long notificationId) {
        Notification notification = findById(notificationId);
        notification.setSeenTime(Instant.now(clock));
        notification.setSeen(true);
        notificationRepository.save(notification);
    }

    @Override
    public Notification findById(Long notificationId) {
        return notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NoSuchElementException("Notification can't be found!"));
    }
}
