package com.example.inyange.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_Table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, name = "product_name",nullable = false)
    private String productName;
    @Column(name = "product_type", nullable = false)
    private String productType;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private float price_per_unit;
    @Column(name = "total_price", nullable = false)
    private float totalPrice;
}
