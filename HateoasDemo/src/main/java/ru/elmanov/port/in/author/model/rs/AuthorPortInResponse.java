package ru.elmanov.port.in.author.model.rs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorPortInResponse {

    Integer id;
    String name;
}
