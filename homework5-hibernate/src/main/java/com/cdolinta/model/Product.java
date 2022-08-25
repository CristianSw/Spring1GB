package com.cdolinta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 1024)
    private String description;
    @Column(nullable = false)
    private Integer price;

    public Product(String title, String description, Integer price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}