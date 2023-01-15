package ru.elmanov.max.qraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elmanov.max.qraphql.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
