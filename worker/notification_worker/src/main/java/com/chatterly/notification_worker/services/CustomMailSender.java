package com.chatterly.notification_worker.services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class CustomMailSender {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public CustomMailSender(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmail(String to, String verificationLink) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        Context context = new Context();
        context.setVariable("name", to);
        context.setVariable("verificationLink", verificationLink);

        String htmlContent = templateEngine.process("verificationEmail", context);

        helper.setTo(to);
        helper.setSubject("Verify your email - Chatterly");
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);

    }

}
