package ru.elmanov.hashing.controller;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.elmanov.hashing.entity.Image;
import ru.elmanov.hashing.service.ImageService;

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

    @PostMapping(value = "/{imageName}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> save(@PathVariable String imageName,
                                  @RequestParam MultipartFile content) {
        try {
            return ResponseEntity.ok().body(imageService.save(content, imageName));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getLocalizedMessage());
        }
    }
}
