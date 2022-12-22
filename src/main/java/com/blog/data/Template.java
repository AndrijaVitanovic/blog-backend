package com.blog.data;

/**
 * Domain value class that represents a template name. It is used to
 * prevent confusing calling methods with template names rather than data
 * and vice versa.
 */
public class Template extends ValueObject<String> {

    public Template(String value) {
        super(value);
    }
}

