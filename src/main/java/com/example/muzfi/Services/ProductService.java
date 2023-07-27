package com.example.muzfi.Services;

import com.example.muzfi.Model.Product;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> getAllProducts();

    Optional<Product> getProduct(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}