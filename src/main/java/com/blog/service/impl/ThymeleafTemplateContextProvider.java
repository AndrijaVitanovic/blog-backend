package com.blog.service.impl;

import com.blog.service.TemplateContextProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.expression.ThymeleafEvaluationContext;

@Service
@RequiredArgsConstructor
public class ThymeleafTemplateContextProvider implements TemplateContextProvider {

    private final ApplicationContext applicationContext;
    private final ConversionService conversionService;


    @Override
    public Context getContext() {
        Context context = new Context(LocaleContextHolder.getLocale(), null);
        context.setVariable(
                ThymeleafEvaluationContext.THYMELEAF_EVALUATION_CONTEXT_CONTEXT_VARIABLE_NAME,
                new ThymeleafEvaluationContext(applicationContext, conversionService)
        );

        return context;
    }
}
