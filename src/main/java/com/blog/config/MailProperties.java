package com.blog.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix="spotlight")
public class MailProperties {

    @Positive
    private final long verificationValiditySeconds = 5 * 60;
    
    @NotBlank
    private String verificationEndpoint;
}
