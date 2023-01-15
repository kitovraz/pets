package ru.elmanov.listener;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.RevisionListener;
import ru.elmanov.model.User;
import ru.elmanov.model.audit.Revision;

@Slf4j
public class MyRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        Revision revision = (Revision) revisionEntity;
        revision.setUser(getUser());
    }

    private User getUser() {
        User user = new User();
        user.setName("system-user");
        return user;
    }

}
