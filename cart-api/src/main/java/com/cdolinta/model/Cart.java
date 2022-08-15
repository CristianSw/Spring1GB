package com.cdolinta.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public List<Product> getCartProduct(){
        return Collections.unmodifiableList(products);
    }
    public void add(Product product){
        products.add(product);
    }
    public void removeById(final Long id){
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }
}
