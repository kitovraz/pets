package ru.elmanov.secondary.table.audit.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.elmanov.secondary.table.audit.model.audit.DeliveryUnitLog;
import ru.elmanov.secondary.table.audit.model.states.DeliveryUnitState;

import javax.persistence.*;

@Entity
@Table(name = "delivery_unit")
@SecondaryTable(name = "delivery_unit_log",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "delivery_unit_id"))
@SuperBuilder
@NoArgsConstructor
@ToString
@Getter
@Setter
public class DeliveryUnit extends BaseModel{

    private static final long serialVersionUID = 1L;

    private String title;

    @Builder.Default
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private DeliveryUnitState state = DeliveryUnitState.CREATED;

    @Embedded
    private DeliveryUnitLog deliveryUnitLog;
}
