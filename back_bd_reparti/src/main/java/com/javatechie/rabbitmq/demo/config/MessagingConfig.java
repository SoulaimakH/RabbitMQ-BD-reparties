package com.javatechie.rabbitmq.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    public static final String QUEUE = "queue1";

    public static final String EXCHANGE = "direct-exchange";
   // public static final String EXCHANGE2 = "exchange2";
    public static final String ROUTING_KEY = "Bo1";
    public static final String QUEUE2 = "queue2";
    public static final String ROUTING_KEY2 = "Bo2";
    //public static final String EXCHANGE2 = "exchange2";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }
    @Bean
    public Queue queue2() {
        return new Queue(QUEUE2);
    }
    @Bean
    public DirectExchange  exchange() {
        return new DirectExchange (EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange  exchange) {
        return BindingBuilder.bind(queue()).to(exchange).with(ROUTING_KEY);
    }
    @Bean
    public Binding binding2(Queue queue2, DirectExchange  exchange) {
        return BindingBuilder.bind(queue2()).to(exchange).with(ROUTING_KEY2);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
