package ru.elmanov.jwt.security.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PROTECTED)
@SuperBuilder
@NoArgsConstructor
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "created_at")
    @CreationTimestamp
    LocalDate createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    LocalDate updatedAt;
}
