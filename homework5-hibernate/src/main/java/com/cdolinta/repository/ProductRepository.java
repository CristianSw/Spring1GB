package com.cdolinta.repository;

import com.cdolinta.model.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAll();
    void save(Product product);
    void update(Long id, Product product);
    void deleteById(Long id);
}
