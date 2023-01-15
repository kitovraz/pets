package ru.elmanov.max.qraphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elmanov.max.qraphql.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
