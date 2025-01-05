package com.chatterly.auth_service.utils;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.chatterly.auth_service.config.RabbitMQConfig;
import com.chatterly.auth_service.dto.MessageDTO;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDTO message) {
        message.setEmail(message.getEmail());
        message.setToken(message.getToken());
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE, message);
    }

}
