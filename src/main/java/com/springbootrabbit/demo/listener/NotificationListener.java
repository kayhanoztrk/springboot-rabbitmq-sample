package com.springbootrabbit.demo.listener;

import com.springbootrabbit.demo.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Service
public class NotificationListener {

    @RabbitListener(queues = "testQueue")
    public void readQueue(Notification notification){
        System.out.println("Received:" + notification.toString());
    }
}
