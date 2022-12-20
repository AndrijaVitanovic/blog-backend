package com.blog.service;

import org.thymeleaf.context.Context;

public interface TemplateContextProvider {

    /**
     * Gets a configured {@link Context} instance.
     * @return a configured {@link Context} instance.
     */
    Context getContext();
}
