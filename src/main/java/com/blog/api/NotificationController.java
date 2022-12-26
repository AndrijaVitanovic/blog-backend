package com.blog.api;

import com.blog.entity.Notification;
import com.blog.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @SubscribeMapping("/user/{email}/notifications")
    @Operation(hidden = true)
    public List<Notification> getAllNotifications(@DestinationVariable String email) {
        Specification<Notification> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get("email"), email);
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);
        return notificationService.findAll(specification, pageable);
    }

    @PutMapping("/{notificationId}")
    public ResponseEntity<Void> seenNotification(@PathVariable Long notificationId) {
        notificationService.markSeen(notificationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {
        return ResponseEntity.ok(notificationService.findAll());
    }
}
