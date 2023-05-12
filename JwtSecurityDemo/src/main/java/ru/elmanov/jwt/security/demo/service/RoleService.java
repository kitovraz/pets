package ru.elmanov.jwt.security.demo.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.elmanov.jwt.security.demo.model.Role;
import ru.elmanov.jwt.security.demo.repository.RoleRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleService extends BaseService<RoleRepository, Role> {

    public RoleService(RoleRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }
}
