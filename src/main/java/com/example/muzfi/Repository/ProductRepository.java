package com.example.muzfi.Repository;

import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCondition(ProductCondition condition);
    List<Product> findByBrandId(String brandId);

}
