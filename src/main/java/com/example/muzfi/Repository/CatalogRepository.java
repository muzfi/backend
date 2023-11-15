package com.example.muzfi.Repository;

import com.example.muzfi.Model.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {
    List<Catalog> findByTitleContainingIgnoreCase(String query);
}
