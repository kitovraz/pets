package ru.elmanov.avrokafkatest.api.rq;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestDTO {

    Integer id;
    String name;
    BigDecimal price;
    Boolean delivery;
    State state;
    List<String> tags;
}
