package com.cdolinta.services;

import com.cdolinta.model.Product;
import com.cdolinta.repository.ProductRepository;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    EntityManagerFactory entityManagerFactory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    EntityManager entityManager ;

    @Override
    public Product findById(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return product;
    }

    @Override
    public List<Product> findAll() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Product> products = entityManager.createQuery("select * from User u", Product.class)
                .getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return products;
    }

    @Override
    public void save(Product product) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void update(Long id, Product product) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Product productToChange = entityManager.find(Product.class, id);
        productToChange.setPrice(product.getPrice());
        productToChange.setDescription(product.getDescription());
        productToChange.setPrice(product.getPrice());
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void deleteById(Long id) {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Product productToDelete = entityManager.find(Product.class, id);
        entityManager.remove(productToDelete);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
