package ru.elmanov.kafka.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerListener {

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void listen(@Payload String message) {
        log.info("Received Message: " + message);
    }

}
