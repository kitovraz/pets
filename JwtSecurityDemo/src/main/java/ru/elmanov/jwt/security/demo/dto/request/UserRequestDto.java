package ru.elmanov.jwt.security.demo.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto extends BaseRequestDto {

    String username;
    String password;
    List<RoleRequestDto> roles = new ArrayList<>();
}
