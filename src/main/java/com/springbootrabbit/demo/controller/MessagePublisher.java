package com.springbootrabbit.demo.controller;

import com.springbootrabbit.demo.model.Notification;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.logging.Logger;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@RestController
public class MessagePublisher {

    private final Logger logger = Logger.getLogger(String.valueOf(MessagePublisher.class));
    private final AmqpTemplate rabbitTemplate;
    private final DirectExchange exchange;

    @Value("${spring.rabbitmq.routing.name}")
    private String routingKey;

    public MessagePublisher(AmqpTemplate rabbitTemplate,
                            DirectExchange directExchange){
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = directExchange;
    }

    @PostMapping("/publish")
    public String publishMessage(@RequestBody Notification notification){
        notification.setNotificationId(3L);
        notification.setCreatedAt(new Date());
        logger.info("NotificationObkect:" + notification);
        rabbitTemplate.convertAndSend(exchange.getName(),routingKey,
                notification);
        return "message has been published!";
    }

}
