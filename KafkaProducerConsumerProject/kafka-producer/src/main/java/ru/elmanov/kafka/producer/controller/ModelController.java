package ru.elmanov.kafka.producer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import ru.elmanov.kafka.producer.model.MessageInfo;
import ru.elmanov.kafka.producer.service.KafkaService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/kafka/producer")
@RequiredArgsConstructor
public class ModelController {

    private final KafkaService kafkaService;

    @GetMapping("test/{message}")
    public void getString(@PathVariable String message) {
        kafkaService.sendMessage(message);
    }

    @PostMapping("test/entity")
    public ResponseEntity<Object> getEntity(@RequestBody MessageInfo messageInfo) {
        try {
            kafkaService.sendMessage(messageInfo);
        } catch (ExecutionException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
        } catch (InterruptedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
        } catch (TimeoutException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getLocalizedMessage());
        }

        return ResponseEntity.ok("Success sent message");
    }
}
