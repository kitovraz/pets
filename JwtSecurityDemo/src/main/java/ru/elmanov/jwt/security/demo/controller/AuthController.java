package ru.elmanov.jwt.security.demo.controller;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.elmanov.jwt.security.demo.dto.request.LoginRequest;
import ru.elmanov.jwt.security.demo.dto.response.JwtResponse;
import ru.elmanov.jwt.security.demo.model.security.UserDetailsImpl;
import ru.elmanov.jwt.security.demo.service.UserService;
import ru.elmanov.jwt.security.demo.utils.JwtUtils;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/login")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthController {

    final UserService userService;
    final JwtUtils jwtUtils;
    final AuthenticationManager authenticationManager;

    @PostMapping
    ResponseEntity<?> login(@NonNull @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(null)
                .roles(roles)
                .build());
    }
}
