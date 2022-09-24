package com.cdolinta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

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

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer price;

    public Product(Long id, String title, String description, Integer price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
