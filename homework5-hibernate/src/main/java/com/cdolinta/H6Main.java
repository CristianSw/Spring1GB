package com.cdolinta;

import com.cdolinta.model.User;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class H6Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();


        List<User> users = entityManager.createQuery(
                     "select u from User u where (u.username like :usernameFilter)" +
                        " and u.password like :passwordFilter"
                        , User.class)
                .setParameter("usernameFilter", "%U%")
                .setParameter("passwordFilter", "%pass%")
                .getResultList();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        Join<Object, Object> contacts = root.join("contacts");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.like(root.get("username"), "%U%"));
        predicates.add(cb.like(root.get("password"), "%pass%"));

        List<User> resultList = entityManager.createQuery(query
                        .select(root)
                        .where(predicates.toArray(new Predicate[0])))
                .getResultList();

        System.out.println();

        entityManager.close();
        entityManagerFactory.close();
    }
}
