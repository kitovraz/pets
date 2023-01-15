package ru.test.rabbitmq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class SimpleController {

    @Value("${exchange-fanout}")
    private String fanoutExchangeName;

    @Value("${exchange-direct}")
    private String directExchangeName;
    private final RabbitTemplate template;

    public SimpleController(RabbitTemplate template) {
        this.template = template;
    }

    @PostMapping("/emit")
    public ResponseEntity<String> emit(@RequestBody String message){
        log.info("mail to myQueue 1");
        template.convertAndSend("myQueue", message);
        return ResponseEntity.ok("Success send to queue");
    }

    @PostMapping("/emit2")
    public ResponseEntity<String> emitFanout(@RequestBody String message){ // message is not important
        log.info("mail to myQueue Fanout");
        template.setExchange(fanoutExchangeName);
        template.convertAndSend(message);
        return ResponseEntity.ok("Success send to queue");
    }

    @PostMapping("/emit3")
    public ResponseEntity<String> emitDirect(@RequestBody Map<String, String> map){ // message is not important
        log.info("mail to myQueue Direct");
        template.setExchange(directExchangeName);
        template.convertAndSend(map.get("key"), map.get("message"));
        return ResponseEntity.ok("Success send to queue");
    }
}
