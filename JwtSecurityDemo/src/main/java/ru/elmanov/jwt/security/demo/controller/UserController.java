package ru.elmanov.jwt.security.demo.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.elmanov.jwt.security.demo.dto.UserRequestDto;
import ru.elmanov.jwt.security.demo.model.User;
import ru.elmanov.jwt.security.demo.service.UserService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody UserRequestDto dto) {
        var user = userService.deserialize(dto, User.class);
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        var userToDelete = userService.findById(id);
        if (Objects.nonNull(userToDelete)) {
            userService.delete(userToDelete);
            return ResponseEntity.ok("user successfully deleted");
        }
        return ResponseEntity.ok("user not found");
    }
}
