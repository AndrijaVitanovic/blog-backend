package com.blog.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Getter
@Setter
@ConfigurationProperties("security")
public class SecurityProperties {

    List<String> allowedOrigins = List.of("*");
}
