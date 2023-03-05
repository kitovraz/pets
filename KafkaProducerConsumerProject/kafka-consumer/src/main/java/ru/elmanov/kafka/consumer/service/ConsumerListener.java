package ru.elmanov.kafka.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.elmanov.kafka.consumer.model.MessageInfo;

@Slf4j
@Service
public class ConsumerListener {

//    @KafkaListener(topics = "${spring.kafka.topic}",
//            groupId = "${spring.kafka.consumer-group}",
//            containerFactory = "kafkaListenerContainerFactory")
//    public void listen(@Payload String message) {
//        log.info("Received Message: " + message);
//    }
    @KafkaListener(topics = "${spring.kafka.topic-entity}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenEntity(@Headers MessageHeaders headers,
            @Payload MessageInfo messageInfo) {
        log.info("Received Message: " + messageInfo.getMessage() +
                " - Commands: " + messageInfo.getCommands());
        headers.keySet().forEach(key -> {
            log.info("{}: {}", key, headers.get(key));
        });
    }

}
