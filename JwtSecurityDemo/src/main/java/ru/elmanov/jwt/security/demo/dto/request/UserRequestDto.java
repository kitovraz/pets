package ru.elmanov.jwt.security.demo.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.elmanov.jwt.security.demo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequestDto extends BaseRequestDto {

    String username;
    String password;
    List<Role> roles = new ArrayList<>();
}
