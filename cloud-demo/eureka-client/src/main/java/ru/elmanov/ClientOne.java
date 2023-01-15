package ru.elmanov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ClientOne {
    public static void main(String[] args) {
        SpringApplication.run(ClientOne.class);
    }
}