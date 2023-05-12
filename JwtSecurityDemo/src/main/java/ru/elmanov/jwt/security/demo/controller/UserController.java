package ru.elmanov.jwt.security.demo.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.elmanov.jwt.security.demo.dto.request.UserRequestDto;
import ru.elmanov.jwt.security.demo.dto.response.UserResponseDto;
import ru.elmanov.jwt.security.demo.model.User;
import ru.elmanov.jwt.security.demo.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {

    final UserService userService;

    @GetMapping
    public List<UserResponseDto> getUsers() {
//        return userService.serialise(userService.findAll(), UserResponseDto.class);
        List<User> users = userService.findAll();
        List<UserResponseDto> userResponseDtos = users.stream()
                .map(user -> {
                    UserResponseDto userResponseDto = new UserResponseDto();
                    userResponseDto.setUsername(user.getUsername());
                    userResponseDto.setPassword(user.getPassword());
                    userResponseDto.setRoles(user.getRoles());
                    return userResponseDto;
                })
                .collect(Collectors.toList());
        return userResponseDtos;
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
//        return userService.serialise(userService.findById(id), UserResponseDto.class);

        User user = userService.findById(id);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setRoles(user.getRoles());
        return userResponseDto;
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
