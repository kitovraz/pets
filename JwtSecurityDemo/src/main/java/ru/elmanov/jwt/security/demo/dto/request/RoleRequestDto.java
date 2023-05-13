package ru.elmanov.jwt.security.demo.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.elmanov.jwt.security.demo.model.Role;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequestDto extends BaseRequestDto {

    String name;
}
