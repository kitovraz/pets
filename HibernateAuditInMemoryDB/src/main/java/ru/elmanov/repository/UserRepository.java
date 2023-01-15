package ru.elmanov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.elmanov.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
