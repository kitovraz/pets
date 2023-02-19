package ru.elmanov.kafka.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elmanov.kafka.producer.service.KafkaService;

@RestController
@RequestMapping("/kafka/producer")
@RequiredArgsConstructor
public class ModelController {

    private final KafkaService kafkaService;

    @GetMapping("test/{message}")
    public void getString(@PathVariable String message) {
        kafkaService.sendMessage(message);
    }
}
