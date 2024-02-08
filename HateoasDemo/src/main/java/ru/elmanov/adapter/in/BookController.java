package ru.elmanov.adapter.in;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;
import ru.elmanov.api.rs.BookResponseDTO;
import ru.elmanov.port.in.book.SearchBookUseCase;
import ru.elmanov.port.in.common.model.SearchByIdRequest;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class BookController {

    SearchBookUseCase searchBookUseCase;

    @GetMapping("{id}")
    public EntityResponse<BookResponseDTO> searchBookById(@PathVariable Integer id) {
        return searchBookUseCase.search(SearchByIdRequest.of(id));
    }
}
