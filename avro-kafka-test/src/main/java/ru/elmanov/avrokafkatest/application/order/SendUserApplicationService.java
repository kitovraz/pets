package ru.elmanov.avrokafkatest.application.order;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.elmanov.avrokafkatest.adaper.out.kafka.KafkaAvroService;
import ru.elmanov.avrokafkatest.api.rq.avro.UserAvro;
import ru.elmanov.avrokafkatest.port.in.SendUserInfoUseCase;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SendUserApplicationService implements SendUserInfoUseCase {

    KafkaAvroService kafkaAvroService;

    @Override
    public void send(UserAvro user) {
        kafkaAvroService.sendUser(user);
    }
}
