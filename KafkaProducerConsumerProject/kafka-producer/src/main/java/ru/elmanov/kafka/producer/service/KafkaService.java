package ru.elmanov.kafka.producer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.elmanov.kafka.producer.model.MessageInfo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, MessageInfo> kafkaTemplateEntity;

    @Value("${spring.kafka.topic}")
    private String topic;
    @Value("${spring.kafka.topic-entity}")
    private String topicEntity;

    public void sendMessage(String message) {
        var listenableFuture = kafkaTemplate.send(topic, message);

        listenableFuture.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + message + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }

    public void sendMessage(MessageInfo messageInfo) throws ExecutionException, InterruptedException, TimeoutException {

        var message = MessageBuilder.withPayload(messageInfo)
                .setHeader(KafkaHeaders.TOPIC, topicEntity)
                .build();

        var listenableFuture = kafkaTemplateEntity.send(message);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, MessageInfo>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=[" + messageInfo +
                        "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MessageInfo> result) {
                log.info("Sent message=[" + result.getProducerRecord().value() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]" +
                        " topic: " + result.getProducerRecord().topic());
            }
        });
    }
}
