package ru.elmanov.adapter.in.book;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elmanov.adapter.in.book.mapper.BookAssembler;
import ru.elmanov.api.rs.BookResponseDTO;
import ru.elmanov.port.in.book.SearchBookUseCase;
import ru.elmanov.port.in.common.model.SearchByIdRequest;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BookController {

    /**
     * TEST by curl localhost:8000/api/v1/books/1 | json_pp
     */
    BookAssembler bookAssembler;
    SearchBookUseCase searchBookUseCase;

    @GetMapping("{id}")
    public ResponseEntity<BookResponseDTO> searchBookById(@PathVariable Integer id) {
        final var bookPortInResponse = searchBookUseCase.search(SearchByIdRequest.of(id));
        return ResponseEntity.ok(bookAssembler.toModel(bookPortInResponse));
    }
}
