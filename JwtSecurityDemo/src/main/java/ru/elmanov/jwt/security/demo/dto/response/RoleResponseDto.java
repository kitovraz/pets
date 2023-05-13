package ru.elmanov.jwt.security.demo.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.elmanov.jwt.security.demo.dto.request.BaseRequestDto;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponseDto extends BaseResponseDto {

    String name;
}
