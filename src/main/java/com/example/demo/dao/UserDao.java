package com.example.demo.dao;


import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    public List<User> allUsers() {
        TypedQuery<User> typedQuery = entityManager.createQuery("from User", User.class);
        return typedQuery.getResultList();
    }


    public void add(User user) {
        entityManager.persist(user);
    }


    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }


    public void edit(User user) {
        entityManager.merge(user);
    }


    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

}
