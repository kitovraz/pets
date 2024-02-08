package ru.elmanov.adapter.in.book.mapper;

import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.elmanov.adapter.in.author.AuthorController;
import ru.elmanov.adapter.in.book.BookController;
import ru.elmanov.api.rs.BookResponseDTO;
import ru.elmanov.port.in.book.model.rs.BookPortInResponse;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BookAssembler extends RepresentationModelAssemblerSupport<BookPortInResponse, BookResponseDTO> {

    BookMapper bookMapper;

    public BookAssembler(BookMapper bookMapper) {
        super(BookPortInResponse.class, BookResponseDTO.class);
        this.bookMapper = bookMapper;
    }

    @Override
    public BookResponseDTO toModel(BookPortInResponse entity) {
        final var dto = bookMapper.toDTO(entity);

        final var selfLink = linkTo(
                methodOn(BookController.class)
                        .searchBookById(entity.getId()))
                .withSelfRel();

        dto.add(selfLink);

        ofNullable(entity.getAuthor())
                .map(author -> linkTo(
                        methodOn(AuthorController.class)
                                .searchAuthorById(author.getId()))
                        .withRel("bookAuthor"))
                .ifPresent(dto::add);

        return dto;
    }
}
