package ru.elmanov.application.book;

import org.springframework.stereotype.Service;
import ru.elmanov.port.in.author.model.rs.AuthorPortInResponse;
import ru.elmanov.port.in.book.SearchBookUseCase;
import ru.elmanov.port.in.book.model.rs.BookPortInResponse;
import ru.elmanov.port.in.common.model.SearchByIdRequest;

@Service
public class SearchBookApplicationService implements SearchBookUseCase {
    @Override
    public BookPortInResponse search(SearchByIdRequest<Integer> request) {
        final AuthorPortInResponse authorPortInResponse = new AuthorPortInResponse(1, "Lev Tolstoy");
        return new BookPortInResponse(1, "War and Piece", authorPortInResponse);
    }
}
