package ru.test.rabbitmq.config;

import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ConfigRabbitMQ {

    @Value("${exchange-fanout}")
    private String fanoutExchangeName;

    @Value("${exchange-direct}")
    private String directExchangeName;

//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return new CachingConnectionFactory("localhost");
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin(connectionFactory());
//    }

//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate(connectionFactory());
//    }

    @Bean
    public Queue myQueue(){
        return new Queue("myQueue");
    }

    @Bean
    public Queue myQueue1(){
        return new Queue("myQueue1");
    }

    @Bean
    public Queue myQueue2(){
        return new Queue("myQueue2");
    }
//Fanout
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(fanoutExchangeName);
//    }

//    @Bean
//    public Binding binding1(){
//        return BindingBuilder.bind(myQueue1()).to(fanoutExchange());
//    }
//
//    @Bean
//    public Binding binding2(){
//        return BindingBuilder.bind(myQueue2()).to(fanoutExchange());
//    }

    //Direct
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(directExchangeName);
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("info");
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("debug");
    }

    @Bean
    public Binding binding3(){
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("error");
    }

    //Manual Listener
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer(){
//        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
//        listenerContainer.setConnectionFactory(connectionFactory());
//        listenerContainer.setQueues(myQueue());
//        listenerContainer.setMessageListener(message -> log.info("Received from myQueue : {}", message.getBody()));
//        return listenerContainer;
//    }
}
