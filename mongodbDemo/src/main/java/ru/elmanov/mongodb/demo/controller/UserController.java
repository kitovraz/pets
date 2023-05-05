package ru.elmanov.mongodb.demo.controller;

import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ru.elmanov.mongodb.demo.model.Car;
import ru.elmanov.mongodb.demo.model.User;
import ru.elmanov.mongodb.demo.model.enums.Sex;
import ru.elmanov.mongodb.demo.repository.redis.UserRedisRepository;
import ru.elmanov.mongodb.demo.service.CarService;
import ru.elmanov.mongodb.demo.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CarService carService;
    private final UserRedisRepository userRedisRepository;

    @PostConstruct
    public void init() {
//        var car = new Car("Volvo");
//        var insertedCar = carService.createCar(car);
//        User user = new User();
//        user.setAge(12);
//        user.setName("Tom");
//        user.setEmail("asd@sdf.rt");
//        user.setSex(Sex.MALE);
//        user.setCar(insertedCar);
//        userService.createUser(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        var cachedUsers = userRedisRepository.findAll();
        if (!CollectionUtils.isEmpty(cachedUsers)) {
            return ResponseEntity.ok().body(cachedUsers);
        }
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        var cachedUser = userRedisRepository.find(id);
        if (Objects.nonNull(cachedUser)) {
            return ResponseEntity.ok().body(cachedUser);
        }
        User user = userService.get(id);
        userRedisRepository.add(user);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @NonNull User user) {
        var createdUser = userService.createUser(user);

        if (Objects.nonNull(createdUser)) {
            userRedisRepository.add(createdUser);
            return ResponseEntity.ok().body(createdUser);
        }

        return ResponseEntity.ok().body("Already exists");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable @NonNull String id) {
        var countDeleted = userService.deleteUser(id);
        if (Objects.nonNull(countDeleted)) {
            userRedisRepository.delete(id);
        }
        return ResponseEntity.ok().body(countDeleted);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody @NonNull User user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }
}
