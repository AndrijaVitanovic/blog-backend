package com.blog.service;

import com.blog.entity.EmailVerificationRequest;
import com.blog.entity.User;

public interface EmailVerificationRequestService {

    /**
     * This method is used to verify users email address.
     *
     * @param token Generated token for the verification request.
     */
    EmailVerificationRequest verify(String token);

    /**
     * Creates a verification request for the given email address.
     *
     * @param user User account to create verification for.
     */
    void createVerification(User user);
}
