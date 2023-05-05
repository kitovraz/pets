package ru.elmanov.mongodb.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.elmanov.mongodb.demo.model.enums.Sex;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"car"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    public static final long serialVersionUID = -1738225644933183211L;

    @Id
    String id;
    @Indexed(unique = true)
    String email;
    String name;
    int age;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    Sex sex;
    Car car;
}









