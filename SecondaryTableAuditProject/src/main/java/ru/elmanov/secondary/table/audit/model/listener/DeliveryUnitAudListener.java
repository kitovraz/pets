package ru.elmanov.secondary.table.audit.model.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.elmanov.secondary.table.audit.model.DeliveryUnit;
import ru.elmanov.secondary.table.audit.repository.DeliveryUnitAudRepository;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DeliveryUnitAudListener {

//    @Autowired
//    private final DeliveryUnitAudRepository deliveryUnitAudRepository;

//    @PostPersist
    public void setCreateAtL(DeliveryUnit deliveryUnit) {
        if (Objects.nonNull(deliveryUnit) &&
                Objects.nonNull(deliveryUnit.getDeliveryUnitAuds()) &&
                deliveryUnit.getDeliveryUnitAuds().size() > 0) {
            var deliveryUnitAudSet = deliveryUnit.getDeliveryUnitAuds();
            var deliveryUnitAud = deliveryUnitAudSet.get(deliveryUnitAudSet.size() - 1);
            deliveryUnitAud.setCreateAtl(deliveryUnit.getCreateAt());
            deliveryUnitAud.setStatel(deliveryUnit.getState());
            deliveryUnitAud.setDeliveryUnit(deliveryUnit);
//            deliveryUnitAudRepository.save(deliveryUnitAud);
        }
    }

//    @PostUpdate
    public void setUpdateAtL(DeliveryUnit deliveryUnit) {
        if (Objects.nonNull(deliveryUnit) &&
                Objects.nonNull(deliveryUnit.getDeliveryUnitAuds()) &&
                deliveryUnit.getDeliveryUnitAuds().size() > 0) {
            var deliveryUnitAudSet = deliveryUnit.getDeliveryUnitAuds();
            var deliveryUnitAud = deliveryUnitAudSet.get(deliveryUnitAudSet.size() - 1);
            deliveryUnitAud.setUpdateAtl(deliveryUnit.getUpdateAt());
            deliveryUnitAud.setStatel(deliveryUnit.getState());
            deliveryUnitAud.setDeliveryUnit(deliveryUnit);
//            deliveryUnitAudRepository.save(deliveryUnitAud);
        }
    }
}
