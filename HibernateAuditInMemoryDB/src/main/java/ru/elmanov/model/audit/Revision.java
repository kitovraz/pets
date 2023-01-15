package ru.elmanov.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import ru.elmanov.listener.MyRevisionListener;
import ru.elmanov.model.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@org.hibernate.envers.RevisionEntity(MyRevisionListener.class)
public class Revision extends DefaultRevisionEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
//    @Column(name = "user_id", insertable = false, updatable = false)
//    private Long userId;

}
