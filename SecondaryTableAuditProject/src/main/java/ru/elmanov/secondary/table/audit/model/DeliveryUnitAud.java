package ru.elmanov.secondary.table.audit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.elmanov.secondary.table.audit.model.DeliveryUnit;
import ru.elmanov.secondary.table.audit.model.states.DeliveryUnitState;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_unit_aud")
@Getter
@Setter
//@SuperBuilder
@NoArgsConstructor
public class DeliveryUnitAud implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "delivery_unit_id")
    private DeliveryUnit deliveryUnit;
    @Column(name = "delivery_unit_id", insertable = false, updatable = false)
    private Long deliveryUnitId;

    @Column(name = "statel")//, table = "delivery_unit_aud")
    @Enumerated(EnumType.STRING)
    private DeliveryUnitState statel;

    @Column(name = "create_atl")//, table = "delivery_unit_aud")
    private LocalDateTime createAtl;

    @Column(name = "update_atl")//, table = "delivery_unit_aud")
    private LocalDateTime updateAtl;
}
