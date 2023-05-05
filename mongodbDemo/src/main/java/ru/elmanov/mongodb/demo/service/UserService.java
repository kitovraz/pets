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

    private final UserRepository userRepository;
    private final CarService carService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User get(String id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public User createUser(User user) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(user.getEmail()))) {
            var mess = "user exists with %s".formatted(user.getEmail());
            log.warn(mess);
            return Objects.nonNull(user.getId()) ? userRepository.findById(user.getId()).orElse(null) : null;
        }

        if (Objects.nonNull(user.getCar())) {
            var car = carService.createCar(user.getCar());
            user.setCar(car);
        }

        return userRepository.insert(user);
    }

    public Integer deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
