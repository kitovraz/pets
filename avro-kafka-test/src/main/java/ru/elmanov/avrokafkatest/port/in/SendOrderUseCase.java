package ru.elmanov.avrokafkatest.port.in;

import ru.elmanov.avrokafkatest.api.rq.avro.OrderAvro;

public interface SendOrderUseCase {

    void send(OrderAvro order);
}
