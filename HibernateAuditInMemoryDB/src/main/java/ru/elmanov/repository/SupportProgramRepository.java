package ru.elmanov.repository;

import org.springframework.data.repository.CrudRepository;
import ru.elmanov.model.SupportProgram;

public interface SupportProgramRepository extends CrudRepository<SupportProgram, Long> {
}
