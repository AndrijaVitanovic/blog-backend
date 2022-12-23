package com.blog.data;

import com.blog.validation.annotation.Password;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Dto class for login of the user.
 */
public record LoginDto(@NotBlank(message = "validation.username.required") String username,
                       @Password String password) {
}
