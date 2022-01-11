package com.macys.macysordermessageconsumer.configuration;

import com.macys.macysordermessageconsumer.utils.AMQPConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfig {

    @Bean
    Queue jsonQueue() {
        return new Queue(AMQPConstants.JSON_QUEUE, true);
    }

    @Bean
    Queue xmlQueue() {
        return new Queue(AMQPConstants.XML_QUEUE, true);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(AMQPConstants.EXCHANGE);
    }

    @Bean
    Binding binding1(DirectExchange exchange) {
        return BindingBuilder.bind(jsonQueue()).to(exchange).with(jsonQueue().getName());
    }

    @Bean
    Binding binding2(DirectExchange exchange) {
        return BindingBuilder.bind(xmlQueue()).to(exchange).with(xmlQueue().getName());
    }

    @Bean
    public AmqpTemplate jsonAmqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(AMQPConstants.ROUTING_KEY);
        rabbitTemplate.setDefaultReceiveQueue(jsonQueue().getName());
        return rabbitTemplate;
    }

    @Bean
    public AmqpTemplate xmlAmqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setRoutingKey(AMQPConstants.ROUTING_KEY);
        rabbitTemplate.setDefaultReceiveQueue(xmlQueue().getName());
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}