package ru.elmanov.adapter.in.author;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elmanov.adapter.in.author.mapper.AuthorAssembler;
import ru.elmanov.api.rs.AuthorResponseDTO;
import ru.elmanov.port.in.author.SearchAuthorUseCase;
import ru.elmanov.port.in.common.model.SearchByIdRequest;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/authors")
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class AuthorController {

    AuthorAssembler authorAssembler;
    SearchAuthorUseCase searchAuthorUseCase;


    /**
     * TEST by curl localhost:8000/api/v1/authors/1 | json_pp
     */
    @GetMapping("{id}")
    public ResponseEntity<AuthorResponseDTO> searchAuthorById(@PathVariable Integer id) {
        final var authorPortInResponse = searchAuthorUseCase.search(SearchByIdRequest.of(id));
        return ResponseEntity.ok(authorAssembler.toModel(authorPortInResponse));
    }
}
