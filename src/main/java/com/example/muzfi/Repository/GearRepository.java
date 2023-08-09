package com.example.muzfi.Repository;

import com.example.muzfi.Model.Gear;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GearRepository extends MongoRepository<Gear, String> {
    // You can define custom queries here if needed
}
