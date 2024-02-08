package ru.elmanov.api.rs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorResponseDTO extends RepresentationModel<AuthorResponseDTO> {

    Integer id;
    String name;
}
