package com.blog.service.impl;

import com.blog.service.TemplateContextProvider;
import com.blog.service.TemplateProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ThymeleafTemplateProcessor implements TemplateProcessor {

    private final SpringTemplateEngine thymeleafTemplateEngine;
    private final TemplateContextProvider templateContextProvider;

    @Override
    public String process(String template, Map<String, Object> model) {
        Context context = templateContextProvider.getContext();
        context.setVariables(model);
        return thymeleafTemplateEngine.process(template, context);
    }

    @Override
    public String process(String template) {
        return process(template, null);
    }
}
