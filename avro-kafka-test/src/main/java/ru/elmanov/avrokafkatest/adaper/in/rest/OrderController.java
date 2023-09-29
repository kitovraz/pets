package ru.elmanov.avrokafkatest.adaper.in.rest;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.elmanov.avrokafkatest.api.rq.OrderRequestDTO;
import ru.elmanov.avrokafkatest.api.rq.avro.OrderAvro;
import ru.elmanov.avrokafkatest.api.rq.avro.StateAvro;
import ru.elmanov.avrokafkatest.port.in.SendOrderUseCase;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class OrderController {

    SendOrderUseCase sendOrderUseCase;

    @PostMapping("/send/order")
    public void sendOrder(@RequestBody OrderRequestDTO body) {
        final var order = OrderAvro.newBuilder()
                .setId(body.getId())
                .setName(body.getName())
                .setPrice(body.getPrice())
                .setDelivery(body.getDelivery())
                .setTags(body.getTags())
                .setState(StateAvro.valueOf(body.getState().name()))
                .build();
        sendOrderUseCase.send(order);
    }
}
