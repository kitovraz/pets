package ru.elmanov.hashing.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.elmanov.hashing.api.rq.CreateImageRqDTO;
import ru.elmanov.hashing.entity.Image;
import ru.elmanov.hashing.service.ImageService;

import java.io.IOException;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImageController {

    ImageService imageService;

    @GetMapping
    public ResponseEntity<List<Image>> getAll() {
        return ResponseEntity.ok().body(imageService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody CreateImageRqDTO dto) {
        try {
            return ResponseEntity.ok().body(imageService.save(dto));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }
}
