package com.blog.service.impl;

import com.blog.config.MailProperties;
import com.blog.data.Template;
import com.blog.entity.EmailVerificationRequest;
import com.blog.entity.User;
import com.blog.exception.EmailAlreadyVerifiedException;
import com.blog.exception.EmailVerificationAlreadyExistsException;
import com.blog.exception.EmailVerificationExpiredException;
import com.blog.repository.EmailVerificationRequestRepository;
import com.blog.service.EmailVerificationRequestService;
import com.blog.service.MailService;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Clock;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmailVerificationRequestServiceImpl implements EmailVerificationRequestService {

    private final EmailVerificationRequestRepository emailVerificationRequestRepository;
    private final MailProperties mailProperties;
    private final Clock clock;
    private final MailService mailService;

    @Override
    public EmailVerificationRequest verify(String token) {
        EmailVerificationRequest emailVerificationRequest =
                emailVerificationRequestRepository.findByToken(token)
                        .orElseThrow(() -> new NoSuchElementException("Email verification not found!"));
        if (emailVerificationRequest.isVerified())
            throw new EmailAlreadyVerifiedException();

        if (Instant.now(clock).isAfter(emailVerificationRequest.getValidUntil()))
            throw new EmailVerificationExpiredException();

        return setVerified(emailVerificationRequest);
    }

    @Override
    public EmailVerificationRequest createVerification(User user) {
        String email = user.getEmail();

        if (user.getEmailVerified())
            throw new EmailAlreadyVerifiedException();

        if (emailVerificationRequestRepository.existsByEmail(email))
            throw new EmailVerificationAlreadyExistsException();

        Instant validUntil = Instant
                .now(clock)
                .plusSeconds(mailProperties.getVerificationValiditySeconds());

        String token = generateToken(email, validUntil);

        EmailVerificationRequest emailVerificationRequest = new EmailVerificationRequest(
                email,
                token,
                validUntil
        );

        emailVerificationRequest = emailVerificationRequestRepository.save(emailVerificationRequest);
        mailService.sendMail(
                email,
                "Verification email",
                new Template("verification-mail"),
                Map.of(
                        "url", mailProperties.getVerificationEndpoint(),
                        "token", token,
                        "name", user.getFirstName()
                )
        );
        return emailVerificationRequest;
    }

    @SneakyThrows
    private String generateToken(String email, Instant validUntil) {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String token = email + validUntil.getEpochSecond();
        byte[] hash = messageDigest.digest(token.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    private EmailVerificationRequest setVerified(EmailVerificationRequest emailVerificationRequest) {
        emailVerificationRequest.setVerified(true);
        return emailVerificationRequestRepository.save(emailVerificationRequest);
    }
}
