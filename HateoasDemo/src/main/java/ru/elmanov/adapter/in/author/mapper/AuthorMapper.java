package ru.elmanov.adapter.in.author.mapper;

import org.mapstruct.Mapper;
import ru.elmanov.api.rs.AuthorResponseDTO;
import ru.elmanov.port.in.author.model.rs.AuthorPortInResponse;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorResponseDTO toDTO(AuthorPortInResponse source);

    default Collection<AuthorResponseDTO> toDTOs(Collection<AuthorPortInResponse> sources) {
        return sources.stream()
                .map(this::toDTO)
                .toList();
    }
}
