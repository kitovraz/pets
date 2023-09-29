package ru.elmanov.avrokafkatest.application.order;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.elmanov.avrokafkatest.adaper.out.kafka.KafkaAvroService;
import ru.elmanov.avrokafkatest.api.rq.avro.OrderAvro;
import ru.elmanov.avrokafkatest.port.in.SendOrderUseCase;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SendOrderApplicationService implements SendOrderUseCase {

    KafkaAvroService kafkaAvroService;

    @Override
    public void send(OrderAvro order) {
        kafkaAvroService.send(order);
    }
}
