package ru.elmanov.secondary.table.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.elmanov.secondary.table.audit.model.DeliveryUnit;

@Repository
public interface DeliveryUnitRepository extends JpaRepository<DeliveryUnit, Long> {
    @Query(value = "select * from delivery_unit du left join delivery_unit_aud dua on du.id = dua.delivery_unit_id " +
            "where du.title = :title",
            nativeQuery = true)
    DeliveryUnit getByTitle(String title);
}
