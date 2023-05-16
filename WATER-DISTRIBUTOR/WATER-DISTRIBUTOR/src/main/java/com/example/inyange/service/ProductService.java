package com.example.inyange.service;

import com.example.inyange.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> displayProducts();
    Product findProductById(int code);
    Product updateProduct(Product product);
    void deleteProduct(int code);
}
