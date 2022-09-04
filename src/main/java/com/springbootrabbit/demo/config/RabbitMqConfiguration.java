package com.springbootrabbit.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kayhan Öztürk
 * @version 0.1
 * @since 0.1
 */
@Configuration
public class RabbitMqConfiguration {

    @Value("${spring.rabbitmq.queue.name}")
    public String queueName;

    @Value("${spring.rabbitmq.routing.name}")
    private String routingKey;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Bean
    public Queue firstStepQueue() {
        return new Queue(queueName, false);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue firstStepQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(firstStepQueue).to(directExchange).with(routingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
