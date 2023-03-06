package ru.elmanov.kafka.consumer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
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

    @Value("${spring.kafka.ssl.enabled}")
    private boolean sslEnabled;

    @Value("${spring.kafka.topic-entity}")
    private String topicEntity;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        map.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        if (sslEnabled) {
            map.put(SslConfigs.SSL_PROTOCOL_CONFIG, "SSL");
            map.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "CERTS/clinet.truststore.jks");
            map.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "123456");
            map.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "JKS");

            map.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "123456");
            map.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "CERTS/server.keystore.p12");
            map.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "123456");
            map.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, "PKCS12");
        }

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

    @Bean
    public NewTopic entityTopic(){
        return TopicBuilder.name(topicEntity)
                .replicas(1)
                .partitions(1)
                .build();
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
