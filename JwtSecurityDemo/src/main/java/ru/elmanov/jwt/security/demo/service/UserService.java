package ru.elmanov.jwt.security.demo.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.elmanov.jwt.security.demo.model.User;
import ru.elmanov.jwt.security.demo.repository.UserRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService extends BaseService<UserRepository, User> {

    public UserService(UserRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
