package ru.elmanov.jwt.security.demo.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.IterableUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.elmanov.jwt.security.demo.dto.request.BaseRequestDto;
import ru.elmanov.jwt.security.demo.dto.response.BaseResponseDto;
import ru.elmanov.jwt.security.demo.model.BaseModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public abstract class BaseService<R extends CrudRepository<E, Long>, E extends BaseModel> {

    final protected R repository;
    final protected ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<E> findAll() {
        return IterableUtils.toList(repository.findAll());
    }

    @Transactional(readOnly = true)
    public E findById(Long id) {
        return (E) repository.findById(id).orElse(null);
    }

    @Transactional
    public E save(E entity) {
        return (E) repository.save(entity);
    }

    @Transactional
    public void delete(E entity) {
        repository.delete(entity);
    }

    public <D extends BaseRequestDto> E deserialize(D dto, Class<E> clazz) {
        return modelMapper.map(dto, clazz);
    }

    public <D extends BaseResponseDto> D serialise(E entity, Class<D> clazz) {
        return modelMapper.map(entity, clazz);
    }

    public <D extends BaseResponseDto> List<D> serialise(List<E> entities, Class<D> clazz) {
        return entities.stream().map(entity -> serialise(entity, clazz)).collect(Collectors.toList());
    }
}
