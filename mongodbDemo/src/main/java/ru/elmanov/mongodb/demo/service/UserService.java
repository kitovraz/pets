package ru.elmanov.mongodb.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.elmanov.mongodb.demo.model.User;
import ru.elmanov.mongodb.demo.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final CarService carService;

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public User get(String id) {
        return repo.findById(id).orElseThrow(RuntimeException::new);
    }

    public User createUser(User user) {
        if (Boolean.TRUE.equals(repo.existsByEmail(user.getEmail()))) {
            var mess = "user exists with %s".formatted(user.getEmail());
            log.warn(mess);
            return null;
        }

//        if (Objects.nonNull(user.getCar())) {
//            var car = carService.createCar(user.getCar());
//            user.setCar(car);
//        }

        return repo.insert(user);
    }

    public Integer deleteUser(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return 1;
        }
        return 0;
    }

    public User updateUser(User user) {
        return repo.save(user);
    }
}
