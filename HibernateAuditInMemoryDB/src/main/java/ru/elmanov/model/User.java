package ru.elmanov.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "users")
@AuditTable(value = "users_aud")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Audited( withModifiedFlag = true )
public class User extends BaseEntity {

    @Column(name = "name")
    private String name;

    @JsonBackReference(value = "resource-user")
    @OneToOne(mappedBy = "user")
    private Resource resource;
}
