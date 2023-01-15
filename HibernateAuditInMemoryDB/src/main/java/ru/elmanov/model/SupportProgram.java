package ru.elmanov.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "support_programs")
@AuditTable(value = "support_programs_aud")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited( withModifiedFlag = true )
public class SupportProgram extends BaseEntity{
    @Column(name = "title")
    private String title;

    @JsonBackReference(value = "resource-support_programs")
    @OneToOne(mappedBy = "supportProgram")
    private Resource resource;
}
