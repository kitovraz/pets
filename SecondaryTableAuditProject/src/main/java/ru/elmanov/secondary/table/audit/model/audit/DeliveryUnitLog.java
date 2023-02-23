package ru.elmanov.secondary.table.audit.model.audit;

import lombok.Getter;
import lombok.Setter;
import ru.elmanov.secondary.table.audit.model.states.DeliveryUnitState;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class DeliveryUnitLog {

    @Column(name = "state", table = "delivery_unit_log")
    @Enumerated(EnumType.STRING)
    private DeliveryUnitState state;

    @Column(name = "update_at", table = "delivery_unit_log")
    private LocalDateTime updateAt;
}
