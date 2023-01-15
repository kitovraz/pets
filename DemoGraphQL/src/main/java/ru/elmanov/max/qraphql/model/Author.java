package ru.elmanov.max.qraphql.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue
    Long id;
    String name;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();

}
