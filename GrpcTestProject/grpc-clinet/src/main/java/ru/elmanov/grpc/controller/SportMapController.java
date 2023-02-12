package ru.elmanov.grpc.controller;

import com.google.protobuf.Descriptors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.elmanov.grpc.dto.PersonRequestDto;
import ru.elmanov.grpc.service.SportMapClientService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class SportMapController {

    final SportMapClientService sportMapService;

    @GetMapping("/sport/{id}")
    public List<Map<Descriptors.FieldDescriptor, Object>> getPersonsBySport(@PathVariable(value = "id") long sportId) {
        return sportMapService.getPersonsBySport(sportId);
    }

    @GetMapping("/persons")
    public Set<Map<Descriptors.FieldDescriptor, Object>> getSportsByPerson(PersonRequestDto dto) {
        try {
            return sportMapService.getSportsByPersonIds(dto.getPersonIds());
        } catch (InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CountDownLatch exception", e);
        }
    }
}
