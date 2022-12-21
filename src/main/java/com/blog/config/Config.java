package com.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;
import java.util.Optional;

/**
 * Global configuration class for the application.
 * Try to differentiate between what goes into global configuration and what goes into
 * the specific configuration classes.
 */
@Configuration
public class Config {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return () -> Optional.of("system");
        } else {
            return () -> Optional.ofNullable(authentication.getName());
        }
    }

    // TODO: Maybe think about timezones and frontend/backend communication
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
