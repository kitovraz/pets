package ru.elmanov.secondary.table.audit.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "create_at")
    @CreationTimestamp
//    @Setter(AccessLevel.NONE)
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
//    @Setter(AccessLevel.NONE)
    private LocalDateTime updateAt;
}
