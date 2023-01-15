package ru.elmanov.max.qraphql.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ru.elmanov.max.qraphql.model.Author;
import ru.elmanov.max.qraphql.model.Book;
import ru.elmanov.max.qraphql.model.input.InputBook;
import ru.elmanov.max.qraphql.repository.AuthorRepository;
import ru.elmanov.max.qraphql.repository.BookRepository;

import java.util.Optional;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthorController {

    final AuthorRepository authorRepository;
    final BookRepository bookRepository;

    @QueryMapping
    Iterable<Author> authors() {
        return authorRepository.findAll();
    }

    @QueryMapping
    Author authorById(@Argument Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @MutationMapping
    Book addBook(@Argument InputBook book){
        Author author = authorRepository.findById(book.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("book.getAuthorId() must not be null"));
        return bookRepository.save(new Book(book.getTitle(), book.getPublisher(), author));
    }

    @MutationMapping
    Book updateBook(@Argument Long id, @Argument String title, @Argument String publisher, @Argument Long authorId){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("book.getAuthorId() must not be null"));
        book.setTitle(title);
        book.setPublisher(publisher);

        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("authorId must not be null"));;
        book.setAuthor(author);
        return bookRepository.save(book);
    }

    @MutationMapping
    Boolean deleteBookByID(@Argument Long id){
        bookRepository.deleteById(id);
        return true;
    }


}
