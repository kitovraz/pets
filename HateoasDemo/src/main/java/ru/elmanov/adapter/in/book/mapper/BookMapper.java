package ru.elmanov.adapter.in.book.mapper;

import org.mapstruct.Mapper;
import ru.elmanov.api.rs.BookResponseDTO;
import ru.elmanov.port.in.book.model.rs.BookPortInResponse;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponseDTO toDTO(BookPortInResponse source);

    default Collection<BookResponseDTO> toDTOs(Collection<BookPortInResponse> sources) {
        return sources.stream()
                .map(this::toDTO)
                .toList();
    }
}
