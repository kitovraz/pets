package ru.elmanov.secondary.table.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.elmanov.secondary.table.audit.model.DeliveryUnit;
import ru.elmanov.secondary.table.audit.model.DeliveryUnitAud;
import ru.elmanov.secondary.table.audit.model.states.DeliveryUnitState;
import ru.elmanov.secondary.table.audit.repository.DeliveryUnitRepository;

import java.util.List;

@SpringBootApplication
public class SecondaryTableAuditProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecondaryTableAuditProjectApplication.class, args);
    }

    @Autowired
    private DeliveryUnitRepository deliveryUnitRepository;
//    @PersistenceUnit
//    private EntityManagerFactory emf;

    @Override
    public void run(String... args) throws Exception {
//        var macbook = DeliveryUnit.builder()
//                .title("macbook air pro")
//                .state(DeliveryUnitState.CREATED)
//                .deliveryUnitAud(DeliveryUnitAud.builder().build())
//                .build();

        var macbook = new DeliveryUnit();
        macbook.setTitle("macbook air pro");
        macbook.setState(DeliveryUnitState.CREATED);
        macbook.setDeliveryUnitAuds(List.of(new DeliveryUnitAud()));
        deliveryUnitRepository.save(macbook);

//        var savedMacbook = deliveryUnitRepository.getByTitle("macbook air pro");
//        savedMacbook.setState(DeliveryUnitState.PROCESSED);
//        savedMacbook.setDeliveryUnitAuds(List.of(new DeliveryUnitAud()));
//        deliveryUnitRepository.save(savedMacbook);
//
//        var bicycle = new DeliveryUnit();
//        macbook.setTitle("Stels 2500");
//        macbook.setState(DeliveryUnitState.SENT);
//
//        deliveryUnitRepository.save(bicycle);

    }
}
