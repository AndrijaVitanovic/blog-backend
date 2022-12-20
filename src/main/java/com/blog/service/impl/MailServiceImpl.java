package com.blog.service.impl;

import com.blog.data.Template;
import com.blog.service.MailService;
import com.blog.service.TemplateProcessor;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;
    private final TemplateProcessor templateProcessor;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendMail(String to, String subject, String content) {
        javaMailSender.send(createMimeMessage(to, subject, content));
    }

    @Override
    public void sendMail(String to, String subject, Template template, Map<String, Object> data) {
        sendMail(to, subject, templateProcessor.process(template.getName(), data));
    }

    @Override
    public void sendMail(String to, String subject, Template template) {
        sendMail(to, subject, template, Map.of());
    }

    /**
     * Creates mime message for sending mail.
     * @param to - recipient of the mail.
     * @param subject - subject of the mail.
     * @param content - body of the mail.
     * @return - mime message.
     */
    @SneakyThrows
    private MimeMessage createMimeMessage(String to, String subject, String content) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        helper.setFrom(sender);
        return mimeMessage;
    }
}
