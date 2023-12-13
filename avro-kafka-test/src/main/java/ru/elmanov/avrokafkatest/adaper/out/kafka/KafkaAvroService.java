package ru.elmanov.avrokafkatest.adaper.out.kafka;


import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.elmanov.avrokafkatest.api.rq.avro.SecondEntityAvro;
import ru.elmanov.avrokafkatest.api.rq.avro.UserAvro;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class KafkaAvroService {

    @NonFinal
    @Value("${avro.topic.name}")
    String avroTopicName;

    KafkaTemplate<String, SpecificRecord> userKafkaTemplate;

    public void sendUser(SpecificRecord specificRecord) {
        Integer id = null;
        if (specificRecord instanceof UserAvro) {
            id = ((UserAvro) specificRecord).getId();
        } else if (specificRecord instanceof SecondEntityAvro) {
            id = ((SecondEntityAvro) specificRecord).getId();
        }

        final var record = new ProducerRecord<String, SpecificRecord>(avroTopicName, String.valueOf(id), specificRecord);
        ListenableFuture<SendResult<String, SpecificRecord>> listenableFuture = userKafkaTemplate.send(record);

        listenableFuture.addCallback(new ListenableFutureCallback() {

            @Override
            public void onSuccess(Object result) {
                log.info("Message has successfully sent. {}", result.toString());
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Could not sent message. {}", ex);
            }
        });
    }
}