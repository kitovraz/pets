package ru.elmanov.avrokafkatest.adaper.in.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AvroKafkaUserListener {

    @KafkaListener(topics = "${avro.topic.name}",
            containerFactory = "kafkaListenerContainerFactoryUser")
    public void listen(ConsumerRecord<String, SpecificRecord> record) {
        log.info("Message successfully received.\n key: {}, value: {}, offset: {}, timestamp: {}, timestampType: {}, serializedKeySize: {}",
                record.key(),
                record.value(),
                record.offset(),
                record.timestamp(),
                record.timestampType(),
                record.serializedKeySize());
    }

}
