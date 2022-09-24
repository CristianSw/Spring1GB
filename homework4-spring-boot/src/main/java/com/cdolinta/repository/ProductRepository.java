package com.cdolinta.repository;

import com.cdolinta.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {
    @Query(value = """
            select * from products p 
            """,
            countQuery = """
            select count (*) from products p 
            """, nativeQuery = true)
    Page<Product> findProductsPageable(Pageable pageable);
}
