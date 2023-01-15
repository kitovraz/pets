package ru.elmanov.max.qraphql.model.input;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.elmanov.max.qraphql.model.Author;

import javax.persistence.*;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class InputBook {

    String title;
    String publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    Long authorId;

}
