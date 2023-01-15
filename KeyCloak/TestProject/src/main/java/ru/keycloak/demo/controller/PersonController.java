package ru.keycloak.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.keycloak.demo.entity.Person;
import ru.keycloak.demo.service.PersonService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    @Autowired
    public final PersonService personService;

    @GetMapping
    @RolesAllowed("admin")
    public List<Person> getAllPersons() {
        return personService.getAll();
    }

    @GetMapping("/{personId}")
    @RolesAllowed("user")
    public Person getById(@PathVariable("personId") int id) {
        return personService.findById(id);
    }
}
