package ru.elmanov.jwt.security.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {

    String accessToken;
    String refreshToken;
    List<String> roles;
}
