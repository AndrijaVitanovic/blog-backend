package com.blog.service;

import com.blog.entity.EmailVerificationRequest;
import com.blog.entity.User;

public interface EmailVerificationRequestService {

    EmailVerificationRequest verify(String token);

    EmailVerificationRequest createVerification(User user);
}
