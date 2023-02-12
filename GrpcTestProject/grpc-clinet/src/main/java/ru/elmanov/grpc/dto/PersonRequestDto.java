package ru.elmanov.grpc.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class PersonRequestDto {

    Set<Long> personIds = new HashSet<>();
}
