package com.example.inyange.serviceImplementation;

import com.example.inyange.model.Product;
import com.example.inyange.repository.ProductRepository;
import com.example.inyange.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {
    @Autowired
    ProductRepository repo;
    @Override
    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    @Override
    public List<Product> displayProducts() {
        return repo.findAll();
    }

    @Override
    public Product findProductById(int code) {
        return repo.findById(code).orElse(null);
    }

    @Override
    public Product updateProduct(Product product) {
        Product savedProduct = repo.findById(product.getId()).orElse(null);
        if (savedProduct!=null){
            Product updatedProduct = new Product();
            updatedProduct.setProductName(product.getProductName());
            updatedProduct.setProductType(product.getProductType());
            updatedProduct.setQuantity(product.getQuantity());
            updatedProduct.setPrice_per_unit(product.getPrice_per_unit());
            updatedProduct.setTotalPrice(product.getTotalPrice());

            return repo.save(updatedProduct);
        }
        return repo.save(product);
    }

    @Override
    public void deleteProduct(int code) {
        Product savedProduct = repo.findById(code).orElse(null);
        if (savedProduct!=null){
            repo.delete(savedProduct);
        }

    }
}
