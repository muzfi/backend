package com.example.muzfi.Repository;

import com.example.muzfi.Model.Brand;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BrandRepository extends MongoRepository<Brand, String> {
}
