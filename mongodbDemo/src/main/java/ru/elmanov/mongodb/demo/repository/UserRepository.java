package ru.elmanov.mongodb.demo.repository;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.elmanov.mongodb.demo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @ExistsQuery("{id:'?0'}")
    boolean existsById(String id);

    @ExistsQuery
    Boolean existsByEmail(String email);
}
