package ru.elmanov.kafka.consumer.config;

import lombok.SneakyThrows;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.config.internals.BrokerSecurityConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.ResourceUtils;
import ru.elmanov.kafka.consumer.model.MessageInfo;

import java.io.File;
import java.io.IOException;
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

    @SneakyThrows
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        if (sslEnabled) {
            properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
            properties.put(SslConfigs.SSL_PROTOCOL_CONFIG, "TLSv1.3");
            properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "C:\\Users\\elman\\IdeaProjects\\pets\\KafkaProducerConsumerProject\\kafka-consumer\\src\\main\\resources\\ssl\\kafka.client.keystore.jks");
            properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "123456");
//            properties.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "PKCS12");

            properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "123456");
            properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "C:\\Users\\elman\\IdeaProjects\\pets\\KafkaProducerConsumerProject\\kafka-consumer\\src\\main\\resources\\ssl\\kafka.client.truststore.jks");
            properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "123456");
//            properties.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, "PKCS12");

            properties.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
            properties.put(BrokerSecurityConfigs.SSL_CLIENT_AUTH_CONFIG, "true");
        }

        return properties;
    }

    public static void main(String[] args) throws IOException {
        File file = new ClassPathResource("ssl_folder/client.keystore.p12").getFile();
        System.out.println(file.getAbsoluteFile());
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
//    public NewTopic entityTopic(){
//        return TopicBuilder.name(topicEntity)
//                .replicas(1)
//                .partitions(1)
//                .build();
//    }

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
