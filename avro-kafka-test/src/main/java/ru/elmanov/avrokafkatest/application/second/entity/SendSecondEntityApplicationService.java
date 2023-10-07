package ru.elmanov.avrokafkatest.application.second.entity;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.elmanov.avrokafkatest.adaper.out.kafka.KafkaAvroService;
import ru.elmanov.avrokafkatest.api.rq.avro.SecondEntityAvro;
import ru.elmanov.avrokafkatest.port.in.SendSecondEntityInfoUseCase;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SendSecondEntityApplicationService implements SendSecondEntityInfoUseCase {

    KafkaAvroService kafkaAvroService;

    @Override
    public void send(SecondEntityAvro secondEntityAvro) {
        kafkaAvroService.sendUser(secondEntityAvro);
    }
}
