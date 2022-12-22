package com.blog.data;

import com.blog.validation.annotation.Password;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public record LoginDto(String username, @Password String password) {
}
