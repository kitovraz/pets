package ru.elmanov.avrokafkatest.adaper.in.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.elmanov.avrokafkatest.api.rq.avro.UserAvro;

@Slf4j
@Service
public class AvroKafkaUserListener {

    @KafkaListener(topics = "${avro.topic.name}",
            containerFactory = "kafkaListenerContainerFactoryUser")
    public void listenUser(ConsumerRecord<String, UserAvro> record) {
        log.info("[USER] Message successfully received.\n key: {}, value: {}, offset: {}, timestamp: {}, timestampType: {}, serializedKeySize: {}",
                record.key(),
                record.value(),
                record.offset(),
                record.timestamp(),
                record.timestampType(),
                record.serializedKeySize());
    }

}
