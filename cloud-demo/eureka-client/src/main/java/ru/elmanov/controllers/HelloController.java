package ru.elmanov.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/one")
@RefreshScope
public class HelloController {

    @Value("${micro.text}")
    private String text;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok(text);
    }
}
