package ru.elmanov.avrokafkatest.adaper.in.rest;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.elmanov.avrokafkatest.api.rq.UserDTO;
import ru.elmanov.avrokafkatest.api.rq.avro.OrderAvro;
import ru.elmanov.avrokafkatest.api.rq.avro.StateAvro;
import ru.elmanov.avrokafkatest.api.rq.avro.UserAvro;
import ru.elmanov.avrokafkatest.port.in.SendUserInfoUseCase;

import java.util.stream.Collectors;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class UserController {

    SendUserInfoUseCase sendUserInfoUseCase;

    @PostMapping("/send/user")
    public void sendUserInfo(@RequestBody UserDTO userDTO) {
        final var order = UserAvro.newBuilder()
                .setId(userDTO.getId())
                .setName(userDTO.getName())
                .setOrders(userDTO.getOrders().stream()
                        .map(orderDTO -> OrderAvro.newBuilder()
                                .setId(orderDTO.getId())
                                .setName(orderDTO.getName())
                                .setPrice(orderDTO.getPrice())
                                .setDelivery(orderDTO.getDelivery())
                                .setTags(orderDTO.getTags())
                                .setState(StateAvro.valueOf(orderDTO.getState().name()))
                                .build())
                        .collect(Collectors.toList()))
                .build();
        sendUserInfoUseCase.send(order);
    }
}
