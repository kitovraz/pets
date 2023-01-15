package ru.elmanov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import ru.elmanov.model.Resource;
import ru.elmanov.repository.ResourceRepository;
import ru.elmanov.repository.SupportProgramRepository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
@Slf4j
public class ResourceController {

    private final ResourceRepository resourceRepository;
    private final SupportProgramRepository supportProgramRepository;
    private final EntityManager entityManager;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getResource(@PathVariable Long id){
        Optional<Resource> resourceOpt = resourceRepository.findById(id);
        if (resourceOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(resourceOpt.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<?> getResources(){
        List<Resource> resources = IterableUtils.toList(resourceRepository.findAll());
        if (resources.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(resources);
    }

//    @PutMapping
//    public ResponseEntity<?> updateResource(@RequestBody Resource resource) {
//        return ResponseEntity.ok(resourceRepository.save(resource));
//    }

    @PostMapping
    public ResponseEntity<?> createResource(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourceRepository.save(resource));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResource(@PathVariable Long id) {
        Optional<Resource> resourceOpt = resourceRepository.findById(id);
        if (resourceOpt.isPresent()) {
            resourceRepository.delete(resourceOpt.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/audit/{id}")
    public ResponseEntity<?> getRevisions(@PathVariable Long id) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object resource = AuditReaderFactory.get(entityManager).createQuery()
                .forRevisionsOfEntity(Resource.class, true, true)
                .add(AuditEntity.property("id").eq(id))
                .getSingleResult();
        stopWatch.stop();
        log.info("r1: {}", resource);
        log.info("time spent: {}", stopWatch.getTotalTimeNanos());

        stopWatch.start();
        Resource resource2 = AuditReaderFactory.get(entityManager).find(Resource.class, id, Date.valueOf(LocalDate.now().plusDays(1)));
        stopWatch.stop();
        log.info("r2: {}", resource2);
        log.info("time spent: {}", stopWatch.getTotalTimeNanos());

        return ResponseEntity.ok(resource2);
    }

}
