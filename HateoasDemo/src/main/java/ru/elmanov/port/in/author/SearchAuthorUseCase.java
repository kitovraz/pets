package ru.elmanov.port.in.author;

import ru.elmanov.port.in.author.model.rs.AuthorPortInResponse;
import ru.elmanov.port.in.common.model.SearchByIdRequest;

public interface SearchAuthorUseCase {

    AuthorPortInResponse search(SearchByIdRequest<Integer> request);
}
