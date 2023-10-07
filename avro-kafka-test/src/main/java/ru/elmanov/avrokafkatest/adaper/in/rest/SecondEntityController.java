package ru.elmanov.avrokafkatest.adaper.in.rest;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.elmanov.avrokafkatest.api.rq.SecondEntityDTO;
import ru.elmanov.avrokafkatest.api.rq.avro.SecondEntityAvro;
import ru.elmanov.avrokafkatest.port.in.SendSecondEntityInfoUseCase;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class SecondEntityController {

    SendSecondEntityInfoUseCase sendSecondEntityInfoUseCase;

    @PostMapping("/send/second-entity")
    public void sendSecondEntityInfo(@RequestBody SecondEntityDTO secondEntityDTO) {
        final var secondEntityAvro = SecondEntityAvro.newBuilder()
                .setId(secondEntityDTO.getId())
                .setName(secondEntityDTO.getName())
                .build();
        sendSecondEntityInfoUseCase.send(secondEntityAvro);
    }
}
