package ru.elmanov.mongodb.demo.repository.redis;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisRepository<T> {

    List<T> findAll();

    void add(T movie);

    void delete(String id);

    T find(String id);

}
