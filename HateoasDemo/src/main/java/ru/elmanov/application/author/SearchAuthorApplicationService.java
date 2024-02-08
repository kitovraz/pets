package ru.elmanov.application.author;

import org.springframework.stereotype.Service;
import ru.elmanov.port.in.author.SearchAuthorUseCase;
import ru.elmanov.port.in.author.model.rs.AuthorPortInResponse;
import ru.elmanov.port.in.common.model.SearchByIdRequest;

@Service
public class SearchAuthorApplicationService implements SearchAuthorUseCase {
    @Override
    public AuthorPortInResponse search(SearchByIdRequest<Integer> request) {
        return new AuthorPortInResponse(1, "Lev Tolstoy");
    }
}
