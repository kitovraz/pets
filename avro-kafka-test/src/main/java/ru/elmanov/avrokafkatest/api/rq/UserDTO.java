package ru.elmanov.avrokafkatest.api.rq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {

    Integer id;
    String name;
    List<OrderDTO> orders;
}
