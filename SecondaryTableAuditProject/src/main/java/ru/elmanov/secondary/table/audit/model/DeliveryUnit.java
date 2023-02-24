package ru.elmanov.secondary.table.audit.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.elmanov.secondary.table.audit.model.listener.DeliveryUnitAudListener;
import ru.elmanov.secondary.table.audit.model.states.DeliveryUnitState;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_unit")
//@SecondaryTable(name = "delivery_unit_aud",
//        pkJoinColumns = @PrimaryKeyJoinColumn(name = "delivery_unit_id"))
//@SuperBuilder
@NoArgsConstructor
@ToString
@Getter
@Setter
@EntityListeners(DeliveryUnitAudListener.class)
public class DeliveryUnit extends BaseModel {

    private static final long serialVersionUID = 1L;

    @Column(name = "title")
    private String title;

//    @Builder.Default
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private DeliveryUnitState state = DeliveryUnitState.CREATED;

    @OneToMany(mappedBy = "deliveryUnit")
    private List<DeliveryUnitAud> deliveryUnitAuds = new ArrayList<>();
//    @Embedded
//    private List<DeliveryUnitAud> deliveryUnitAud = new ArrayList<>();

    public void setDeliveryUnitAuds(List<DeliveryUnitAud> deliveryUnitAuds) {
        this.deliveryUnitAuds.addAll(deliveryUnitAuds);
    }
}
