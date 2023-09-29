package ru.elmanov.avrokafkatest.adaper.out.kafka;


import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import ru.elmanov.avrokafkatest.api.rq.avro.OrderAvro;

import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class KafkaAvroService {

    @NonFinal
    @Value("${avro.topic.name}")
    String avroTopicName;

    KafkaTemplate<String, OrderAvro> kafkaTemplate;

    public void send(OrderAvro order) {
        final var record = new ProducerRecord<String, OrderAvro>(avroTopicName, order);
        ListenableFuture<SendResult<String, OrderAvro>> listenableFuture = kafkaTemplate.send(record);

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
