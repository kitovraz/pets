package ru.elmanov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import ru.elmanov.model.Resource;
import ru.elmanov.model.dto.ResourceResponseDto;
import ru.elmanov.repository.ResourceRepository;
import ru.elmanov.repository.SupportProgramRepository;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
@Slf4j
public class ResourceController {

    private final ResourceRepository resourceRepository;
    private final SupportProgramRepository supportProgramRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getResource(@PathVariable Long id){
        var resourceOpt = resourceRepository.findById(id);
        if (resourceOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(resourceOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<?> getResources(){
        var resources = IterableUtils.toList(resourceRepository.findAll());
        if (resources.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resources);
    }

    @PostMapping
    public ResponseEntity<?> createResource(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourceRepository.save(resource));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResource(@PathVariable Long id, @RequestBody Resource resourceDto) {
        var resourceOptional = resourceRepository.findById(id);
        if (resourceOptional.isPresent()) {
            var resource = resourceOptional.get();

            if (nonNull(resourceDto.getUser())) resource.setUser(resourceDto.getUser());
            if (Strings.isNotBlank(resourceDto.getTitle())) resource.setTitle(resourceDto.getTitle());
            if (nonNull(resourceDto.getSupportProgram())) {
                var supportProgramOptional = supportProgramRepository.findById(resourceDto.getSupportProgram().getId());
                if (supportProgramOptional.isPresent()) {
                    var supportProgram = supportProgramOptional.get();
                    resource.setSupportProgram(supportProgram);
                }
            }

            return ResponseEntity.ok(resourceRepository.save(resource));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable Long id) {
        var resourceOpt = resourceRepository.findById(id);
        if (resourceOpt.isPresent()) {
            resourceRepository.delete(resourceOpt.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/audit/{id}")
    public ResponseEntity<?> getRevisions(@PathVariable Long id) {
        var stopWatch = new StopWatch();
        stopWatch.start();
        var resources = AuditReaderFactory.get(entityManager).createQuery()
                .forRevisionsOfEntity(Resource.class, false, true)
                .add(AuditEntity.property("id").eq(id))
                .getResultList();
        stopWatch.stop();
        log.info("r1: {}", resources);
        log.info("time spent: {}", stopWatch.getTotalTimeNanos());

        return ResponseEntity.ok(convertListToDto(resources));
    }

    private List<ResourceResponseDto> convertListToDto(List<Object[]> list) {
        return list.stream()
                .map(e -> {
                    ResourceResponseDto responseDto = modelMapper.map(e[0], ResourceResponseDto.class);
                    responseDto.setType((RevisionType) e[2]);
                    return responseDto;
                })
                .collect(Collectors.toList());
    }

}
