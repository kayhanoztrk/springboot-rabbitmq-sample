package com.springbootrabbit.demo.producer;

import com.springbootrabbit.demo.model.Notification;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Service
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public NotificationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendToQueue(Notification notification) {
        System.out.println("Notification sent ID:" + notification.getNotificationId());
        rabbitTemplate.convertAndSend(notification);
    }
}
