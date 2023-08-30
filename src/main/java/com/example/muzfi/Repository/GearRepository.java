package com.example.muzfi.Repository;

import com.example.muzfi.Model.Gear;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GearRepository extends MongoRepository<Gear, String> {
    List<Gear> findByNameContainingIgnoreCase(String name);
}
