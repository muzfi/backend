package com.example.muzfi.Repository;

import com.example.muzfi.Model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    List<Group> findByTitleContainingIgnoreCase(String query);
}
