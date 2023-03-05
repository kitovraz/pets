package ru.elmanov.kafka.consumer.config;

import lombok.val;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.elmanov.kafka.consumer.model.MessageInfo;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.consumer-group}")
    private String consumerGroup;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
//        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return map;
    }

    @Bean
    public ConsumerFactory<String, MessageInfo> consumerFactory() {
//        JsonDeserializer<MessageInfo> deserializer = new JsonDeserializer<>(MessageInfo.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("ru.elmanov.kafka.consumer.model");
//        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(MessageInfo.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageInfo> kafkaListenerContainerFactory() {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, MessageInfo>();
        factory.setConsumerFactory(consumerFactory());
//        factory.setMessageConverter(recordMessageConverter());
        return factory;
    }

//    @Bean
//    public RecordMessageConverter recordMessageConverter(){
//        var stringJsonMessageConverter = new StringJsonMessageConverter();
//        var defaultJackson2JavaTypeMapper = new DefaultJackson2JavaTypeMapper();
//        defaultJackson2JavaTypeMapper.addTrustedPackages("ru.elmanov.kafka.consumer.model.*",
//                "ru.elmanov.kafka.consumer.model",
//                "ru.elmanov.kafka.consumer.model.MessageInfo");
//        stringJsonMessageConverter.setTypeMapper(defaultJackson2JavaTypeMapper);
//        return stringJsonMessageConverter;
//    }
}
