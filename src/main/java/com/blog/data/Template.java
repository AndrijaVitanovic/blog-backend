package com.blog.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Domain value class that represents a template name. It is used to
 * prevent confusing calling methods with template names rather than data
 * and vice versa.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    private String name;
}
