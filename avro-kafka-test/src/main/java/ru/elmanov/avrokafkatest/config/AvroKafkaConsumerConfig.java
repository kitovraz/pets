package ru.elmanov.avrokafkatest.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import ru.elmanov.avrokafkatest.api.rq.avro.UserAvro;

@Configuration
public class AvroKafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, UserAvro> consumerFactoryUser(KafkaProperties properties) {
        return new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, UserAvro>> kafkaListenerContainerFactoryUser(
            KafkaProperties properties) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, UserAvro>();
        factory.setConsumerFactory(consumerFactoryUser(properties));
        return factory;
    }
}
