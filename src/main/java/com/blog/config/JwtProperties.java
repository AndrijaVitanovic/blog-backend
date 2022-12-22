package com.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(String secret, Long expirationTime) {
}
