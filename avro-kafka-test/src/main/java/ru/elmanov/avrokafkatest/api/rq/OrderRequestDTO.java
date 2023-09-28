package ru.elmanov.avrokafkatest.api.rq;

import java.math.BigDecimal;
import java.util.Collection;

public class OrderRequestDTO {

    Integer id;
    String name;
    BigDecimal price;
    Boolean delivery;
    State state;
    Collection<String> tags;

    public OrderRequestDTO(Integer id, String name, BigDecimal price, Boolean delivery, State state, Collection<String> tags) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.delivery = delivery;
        this.state = state;
        this.tags = tags;
    }

    //    @SneakyThrows
//    public static void main(String[] args) {
//        Schema schema = ReflectData.get().getSchema(OrderRequestDTO.class);
//        FileWriter writer = new FileWriter("src/main/resources/order.avsc");
//        writer.append(schema.toString(true));
//        writer.close();
//    }
}
