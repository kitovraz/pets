package ru.elmanov.avrokafkatest;

import lombok.SneakyThrows;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import ru.elmanov.avrokafkatest.api.rq.UserDTO;

import java.io.FileWriter;

public class Main {

    @SneakyThrows
    public static void main2(String[] args) {
        Schema schema = ReflectData.get().getSchema(UserDTO.class);
        FileWriter writer = new FileWriter("src/main/resources/user.avsc");
        writer.append(schema.toString(true));
        writer.close();
    }
}
