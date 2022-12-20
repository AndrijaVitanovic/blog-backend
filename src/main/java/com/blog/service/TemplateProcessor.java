package com.blog.service;

import java.util.Map;

public interface TemplateProcessor {

    /**
     * Renders the given template with the given model.
     *
     * @param template name of the template
     * @param model    model data to be rendered
     * @return rendered template
     */
    String process(String template, Map<String, Object> model);

    /**
     * Renders the given template.
     *
     * @param template name of the template
     * @return rendered template
     */
    String process(String template);
}
