package ru.elmanov.hashing.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.elmanov.hashing.api.rq.CreateImageRqDTO;
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

    public List<Image> getAll() {
        return imageRepository.findAll();
    }

    public Image save(CreateImageRqDTO dto) throws IOException {
        final var image = new Image();
        image.setName(dto.getName());
        image.setContent(dto.getContent().getBytes());
        return imageRepository.save(image);
    }
}
