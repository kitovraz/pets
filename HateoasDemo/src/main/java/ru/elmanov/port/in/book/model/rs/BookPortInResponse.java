package ru.elmanov.port.in.book.model.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.elmanov.port.in.author.model.rs.AuthorPortInResponse;

@Data
@AllArgsConstructor
public class BookPortInResponse {

    Integer id;
    String name;
    AuthorPortInResponse author;
}
