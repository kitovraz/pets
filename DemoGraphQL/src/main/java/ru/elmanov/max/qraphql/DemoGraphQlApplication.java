package ru.elmanov.max.qraphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.elmanov.max.qraphql.model.Author;
import ru.elmanov.max.qraphql.model.Book;
import ru.elmanov.max.qraphql.repository.AuthorRepository;
import ru.elmanov.max.qraphql.repository.BookRepository;

import java.util.List;

@SpringBootApplication
public class DemoGraphQlApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoGraphQlApplication.class, args);
    }

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Author john = authorRepository.save(new Author(null, "John Longd"));
        Author mark = authorRepository.save(new Author(null, "Mark Heckler"));
        bookRepository.saveAll(List.of(
                new Book("Reactive Spring", "Publisher 1", john),
                new Book("Cloud Native Java", "Publisher 2", john),
                new Book("SpringBoot Up", "Publisher 3", mark)
        ));
    }
}
