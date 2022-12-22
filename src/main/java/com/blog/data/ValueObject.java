package com.blog.data;

import lombok.Getter;

@Getter
public abstract class ValueObject<T> {
    private final T value;

    protected ValueObject(T value) {
        this.value = value;
    }
}
