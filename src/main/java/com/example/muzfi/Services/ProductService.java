package com.example.muzfi.Services;

import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Iterable<Product> getAllProducts();

    Optional<Product> getProduct(String id);

    List<Product> getProductsByCondition(ProductCondition condition);

    List<Product> getProductsByBrand(String brandId);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);

    List<String> getTopBrands();

    Product saveProduct(Product product);

    void deleteProduct(String id);
}