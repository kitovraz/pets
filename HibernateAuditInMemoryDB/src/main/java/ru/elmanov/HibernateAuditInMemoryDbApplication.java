package ru.elmanov;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.elmanov.model.Resource;
import ru.elmanov.model.SupportProgram;
import ru.elmanov.model.User;
import ru.elmanov.repository.ResourceRepository;
import ru.elmanov.repository.SupportProgramRepository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
@Slf4j
public class HibernateAuditInMemoryDbApplication implements CommandLineRunner {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateAuditInMemoryDbApplication.class, args);
    }

    @Autowired
    EntityManager entityManager;
    @Autowired
    ResourceRepository resourceRepository;
    @Autowired
    SupportProgramRepository supportProgramRepository;

    public void insertDbValues() {
        User user = new User();
        user.setName("Maxn");
        SupportProgram supportProgram = new SupportProgram();
        supportProgram.setTitle("sp_1");
        Resource resource = new Resource();
        resource.setTitle("r_1");
        resource.setUser(user);
        resource.setSupportProgram(supportProgram);
        resourceRepository.save(resource);

        Optional<SupportProgram> supportProgramOptional = supportProgramRepository.findById(supportProgram.getId());
        supportProgramOptional.ifPresent(sp -> {
            supportProgram.setResource(resource);
            supportProgramRepository.save(supportProgram);
        });
    }

    @Override
    public void run(String... args) throws Exception {
        insertDbValues();
    }
}
