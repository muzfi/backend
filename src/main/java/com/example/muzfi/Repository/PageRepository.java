package com.example.muzfi.Repository;

import com.example.muzfi.Model.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends MongoRepository<Page, String> {
    List<Page> findByTitleContainingIgnoreCase(String query);
}
