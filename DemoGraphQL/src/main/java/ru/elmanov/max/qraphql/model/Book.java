package ru.elmanov.max.qraphql.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue
    Long id;

    String title;
    String publisher;

    public Book(String title, String publisher, Author author) {
        this.title = title;
        this.publisher = publisher;
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    Author author;

}
