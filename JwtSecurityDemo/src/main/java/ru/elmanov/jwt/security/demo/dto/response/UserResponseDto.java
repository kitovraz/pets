package ru.elmanov.jwt.security.demo.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto extends BaseResponseDto {

    String username;
    String password;
    List<RoleResponseDto> roles = new ArrayList<>();
}
