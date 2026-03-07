package ru.kpfu.itis.group400.stashkov.repository;

import ru.kpfu.itis.group400.stashkov.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepositoryHiber {

    private final SessionFactory sessionFactory;

    public UserRepositoryHiber(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User", User.class)
                .list();
    }
}
