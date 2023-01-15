package ru.keycloak.demo.service;

import org.springframework.stereotype.Service;
import ru.keycloak.demo.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private List<Person> persons = new ArrayList<>(){{
        add(new Person(1, "Tom"));
        add(new Person(2, "Jerry"));
        add(new Person(3, "Babka"));
    }};

    public List<Person> getAll() {
        return persons;
    }

    public Person findById(int id) {
        return persons.stream()
                .filter(p -> p.getId() == id)
                .findAny()
                .orElse(null);
    }
}
