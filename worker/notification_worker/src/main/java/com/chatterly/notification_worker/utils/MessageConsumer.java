package com.chatterly.notification_worker.utils;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.chatterly.notification_worker.dto.MessageDTO;
import com.chatterly.notification_worker.services.CustomMailSender;

@Configuration
public class MessageConsumer {

    private final CustomMailSender customMailSender;

    public MessageConsumer(CustomMailSender customMailSender) {
        this.customMailSender = customMailSender;
    }

    @RabbitListener(queues = "auth_queue.register")
    public void consumeMessage(MessageDTO messageDTO) {

        String verificationLink = "http://localhost:8080/verify?token=" + messageDTO.getToken() + "&email="
                + messageDTO.getEmail();
        try {
            customMailSender.sendEmail(messageDTO.getEmail(), verificationLink);
            System.out.println("Email sent to " + messageDTO.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}