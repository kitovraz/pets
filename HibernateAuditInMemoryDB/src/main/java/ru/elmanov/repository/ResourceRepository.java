package ru.elmanov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.elmanov.model.Resource;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
}
