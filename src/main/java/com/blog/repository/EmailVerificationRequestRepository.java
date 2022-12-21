package com.blog.repository;

import com.blog.entity.EmailVerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRequestRepository extends JpaRepository<EmailVerificationRequest, Long> {

    Optional<EmailVerificationRequest> findByToken(String token);

    Boolean existsByEmail(String email);
}
