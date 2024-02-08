package ru.elmanov.api.rs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import ru.elmanov.port.in.author.model.rs.AuthorPortInResponse;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookResponseDTO extends RepresentationModel<BookResponseDTO> {

    Integer id;
    String name;
    AuthorPortInResponse author;
}
