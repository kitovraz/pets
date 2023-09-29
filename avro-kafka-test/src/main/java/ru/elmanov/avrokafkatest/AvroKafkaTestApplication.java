package ru.elmanov.avrokafkatest;

import lombok.SneakyThrows;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.elmanov.avrokafkatest.api.rq.OrderRequestDTO;

import java.io.FileWriter;

@SpringBootApplication
public class AvroKafkaTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvroKafkaTestApplication.class, args);
    }

    @SneakyThrows
    public static void main2(String[] args) {
        Schema schema = ReflectData.get().getSchema(OrderRequestDTO.class);
        FileWriter writer = new FileWriter("src/main/resources/order.avsc");
        writer.append(schema.toString(true));
        writer.close();
    }
}
