package com.blog.service;

import com.blog.data.Template;

import java.util.Map;

public interface MailService {

    /**
     * Sends a simple email to the given address using the default email sender.
     * @param to email address of the recipient.
     * @param subject subject of the email.
     * @param content HTML content of the email.
     */
    void sendMail(String to, String subject, String content);

    /**
     * Sends the email that is rendered from the given template with the given data.
     *
     * @param to email address of the recipient
     * @param subject subject of the email
     * @param template name of the template
     * @param data model data to be rendered
     */
    void sendMail(String to, String subject, Template template, Map<String, Object> data);

    /**
     * Sends the email that is rendered from the given template.
     *
     * @param to email address of the recipient
     * @param subject subject of the email
     * @param template name of the template
     */
    void sendMail(String to, String subject, Template template);

}
