package ru.elmanov.jwt.security.demo.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.elmanov.jwt.security.demo.dto.response.RoleResponseDto;
import ru.elmanov.jwt.security.demo.model.Role;
import ru.elmanov.jwt.security.demo.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RoleController {

    final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public List<RoleResponseDto> getRoles() {
        return roleService.serialise(roleService.findAll(), RoleResponseDto.class);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public RoleResponseDto getRole(@PathVariable Long id) {
        return roleService.serialise(roleService.findById(id), RoleResponseDto.class);
    }

}
