package ru.elmanov.port.in.book;

import ru.elmanov.port.in.book.model.rs.BookPortInResponse;
import ru.elmanov.port.in.common.model.SearchByIdRequest;

public interface SearchBookUseCase {

    BookPortInResponse search(SearchByIdRequest<Integer> request);
}
