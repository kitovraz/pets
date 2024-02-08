package ru.elmanov.adapter.in.author.mapper;

import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.elmanov.adapter.in.author.AuthorController;
import ru.elmanov.api.rs.AuthorResponseDTO;
import ru.elmanov.port.in.author.model.rs.AuthorPortInResponse;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AuthorAssembler extends RepresentationModelAssemblerSupport<AuthorPortInResponse, AuthorResponseDTO> {

    AuthorMapper AuthorMapper;

    public AuthorAssembler(AuthorMapper AuthorMapper) {
        super(AuthorPortInResponse.class, AuthorResponseDTO.class);
        this.AuthorMapper = AuthorMapper;
    }

    @Override
    public AuthorResponseDTO toModel(AuthorPortInResponse entity) {
        final var dto = AuthorMapper.toDTO(entity);

        final var selfLink = linkTo(
                methodOn(AuthorController.class)
                        .searchAuthorById(entity.getId()))
                .withSelfRel();

        dto.add(selfLink);

        return dto;
    }
}
