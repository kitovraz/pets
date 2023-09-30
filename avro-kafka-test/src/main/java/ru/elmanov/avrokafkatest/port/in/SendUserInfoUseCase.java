package ru.elmanov.avrokafkatest.port.in;

import ru.elmanov.avrokafkatest.api.rq.avro.UserAvro;

public interface SendUserInfoUseCase {

    void send(UserAvro user);
}
