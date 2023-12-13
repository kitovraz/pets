package ru.elmanov.hashing.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Data
@Entity
@FieldDefaults(level = PRIVATE)
public class Image {
    @Id
    @Setter(value = PRIVATE)
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    UUID id;

    @Column(name = "name", nullable = false)
    String name;

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "BLOB")
    byte[] content;
}
