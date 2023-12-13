package ru.elmanov.hashing.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.elmanov.hashing.entity.Image;
import ru.elmanov.hashing.repository.ImageRepository;

import java.io.IOException;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ImageService {

    ImageRepository imageRepository;
    HashService hashService;

    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public Object save(MultipartFile content, String name) throws IOException {
        final var hash = hashService.hash(content.getBytes());
        if (imageRepository.existsByHash(hash)) {
            return "image already exists";
        }

        final var image = new Image();
        image.setName(name);
        image.setContent(content.getBytes());
        image.setHash(hash);
        return imageRepository.save(image);
    }
}
