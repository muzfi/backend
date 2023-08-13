package com.example.muzfi.Repository;

import com.example.muzfi.Model.Community;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommunityRepository extends MongoRepository<Community, String> {
    Community findByName(String name);
}
