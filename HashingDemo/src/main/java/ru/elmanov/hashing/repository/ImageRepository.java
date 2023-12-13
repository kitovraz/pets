package ru.elmanov.hashing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.elmanov.hashing.entity.Image;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
    boolean existsByHash(String hash);
}
