package ru.elmanov.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Table(name = "resources")
@AuditTable(value = "resources_aud")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited( withModifiedFlag = true )
public class Resource extends BaseEntity{

    @Column(name = "title")
    private String title;

    @JsonManagedReference(value = "resource-support_programs")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "support_program_id")
    private SupportProgram supportProgram;
    @Column(name = "support_program_id", insertable = false, updatable = false)
    private Long supportProgramId;

    @JsonManagedReference(value = "resource-user")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;
}
