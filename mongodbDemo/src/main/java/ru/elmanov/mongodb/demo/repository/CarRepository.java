package ru.elmanov.mongodb.demo.repository;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.elmanov.mongodb.demo.model.Car;
import ru.elmanov.mongodb.demo.model.User;

@Repository
public interface CarRepository extends MongoRepository<Car, String> {
    @Query("{code:'?0'}")
    public Car findByCode(String code);
}
