package ru.elmanov.jwt.security.demo.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.elmanov.jwt.security.demo.dto.BaseRequestDto;
import ru.elmanov.jwt.security.demo.model.BaseModel;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public abstract class BaseService<R extends CrudRepository<E, Long>, E extends BaseModel> {

    final protected R repository;
    final protected ModelMapper modelMapper;

    public List<E> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    public E findById(Long id) {
        return (E) repository.findById(id).orElse(null);
    }

    public E save(E entity) {
        return (E) repository.save(entity);
    }

    public void delete(E entity) {
        repository.delete(entity);
    }

    public <D extends BaseRequestDto> E deserialize(D dto, Class<E> clazz) {
        return modelMapper.map(dto, clazz);
    }
}
