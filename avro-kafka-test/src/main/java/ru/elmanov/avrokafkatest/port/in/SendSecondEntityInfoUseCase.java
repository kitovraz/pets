package ru.elmanov.avrokafkatest.port.in;

import ru.elmanov.avrokafkatest.api.rq.avro.SecondEntityAvro;

public interface SendSecondEntityInfoUseCase {

    void send(SecondEntityAvro secondEntityDTO);
}
