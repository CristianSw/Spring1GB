package repository;

import model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    List<Product> products;

    @PostConstruct
    public void init(){
        products = new ArrayList<>();
        products.add(new Product(1L,"product1", 200f));
        products.add(new Product(2L,"product2", 1000f));
        products.add(new Product(3L,"product3", 5.99f));
        products.add(new Product(4L,"product4", 50.87f));
        products.add(new Product(5L,"product5", 2053.584f));
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id){
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
