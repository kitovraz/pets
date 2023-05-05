package ru.elmanov.mongodb.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@Builder
public class Car {

    @Id
    String id;
    String mark;
    @Builder.Default
    LocalDate created = LocalDate.now();

    public Car(String mark) {
        this.mark = mark;
    }
}
