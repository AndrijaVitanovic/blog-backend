package com.blog.service;

import java.util.Map;

public interface TemplateProcessor {

    String process(String template, Map<String, Object> model);

    String process(String template);
}
