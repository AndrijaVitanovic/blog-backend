package com.blog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "email_verification_request")
public class EmailVerificationRequest extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name="token")
    private String token;
    @Column(name="valid_until")
    private Instant validUntil;
    @Column(name="is_verified")
    private Boolean verified = false;

    public EmailVerificationRequest(String email, String token, Instant validUntil) {
        this.email = email;
        this.token = token;
        this.validUntil = validUntil;
    }
}
