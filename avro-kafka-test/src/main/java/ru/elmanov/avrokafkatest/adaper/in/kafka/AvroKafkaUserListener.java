package ru.elmanov.avrokafkatest.adaper.in.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.elmanov.avrokafkatest.api.rq.avro.SecondEntityAvro;
import ru.elmanov.avrokafkatest.api.rq.avro.UserAvro;

@Slf4j
@Service
public class AvroKafkaUserListener {

    @KafkaListener(topics = "${avro.topic.name}",
            containerFactory = "kafkaListenerContainerFactoryUser")
    public void listen(ConsumerRecord<String, SpecificRecord> record) {
        final var value = record.value();
        if (value instanceof UserAvro) {
            log.info("[UserAvro]");
        }
        else if (value instanceof SecondEntityAvro) {
            log.info("[SecondEntityAvro]");
        }

        log.info("Message successfully received.\n key: {}, value: {}, offset: {}, timestamp: {}, timestampType: {}, serializedKeySize: {}",
                record.key(),
                record.value(),
                record.offset(),
                record.timestamp(),
                record.timestampType(),
                record.serializedKeySize());
    }

}
