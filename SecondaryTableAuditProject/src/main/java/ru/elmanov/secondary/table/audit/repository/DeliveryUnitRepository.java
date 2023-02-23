package ru.elmanov.secondary.table.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.elmanov.secondary.table.audit.model.DeliveryUnit;

@Repository
public interface DeliveryUnitRepository extends JpaRepository<DeliveryUnit, Long> {
}
