package ru.elmanov.mongodb.demo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    String id;
    @Indexed(unique = true)
    String email;
    String name;
    int age;
    Sex sex;
//    Car car;

    public enum Sex {
        MALE, FEMALE
    }
}
