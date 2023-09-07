package com.example.muzfi.Repository;

import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCondition(ProductCondition condition);
    List<Product> findByBrandId(String brandId);

}
