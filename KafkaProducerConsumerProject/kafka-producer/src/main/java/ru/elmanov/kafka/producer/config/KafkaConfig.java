package ru.elmanov.kafka.producer.config;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.config.internals.BrokerSecurityConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.ResourceUtils;
import ru.elmanov.kafka.producer.model.MessageInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${spring.kafka.ssl.enabled}")
    private boolean sslEnabled;

//    @Autowired
//    private ProducerFactory<Integer, String> producerFactory;

    @Bean
    public ProducerFactory<String, String> producerFactoryString() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @SneakyThrows
    @Bean
    public ProducerFactory<String, MessageInfo> producerFactoryEntity() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        properties.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, "false");

        if (sslEnabled) {
            properties.put(SslConfigs.SSL_PROTOCOL_CONFIG, "SSL");
            properties.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "C:\\Users\\elman\\IdeaProjects\\pets\\KafkaProducerConsumerProject\\kafka-consumer\\src\\main\\resources\\ssl_folder\\client.keystore.p12");
            properties.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "123456");
            properties.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "JKS");

            properties.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "123456");
            properties.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "C:\\Users\\elman\\IdeaProjects\\pets\\KafkaProducerConsumerProject\\kafka-consumer\\src\\main\\resources\\ssl_folder\\clinet.truststore.jks");
            properties.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "123456");
            properties.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, "PKCS12");

            properties.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, "");
            properties.put(BrokerSecurityConfigs.SSL_CLIENT_AUTH_CONFIG, "true");
        }

        return new DefaultKafkaProducerFactory<>(properties);
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactoryString());
    }

    @Bean
    public KafkaTemplate<String, MessageInfo> kafkaTemplateEntity(){
        return new KafkaTemplate<>(producerFactoryEntity());
    }
}
