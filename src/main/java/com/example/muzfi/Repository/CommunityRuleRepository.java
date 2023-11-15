package com.example.muzfi.Repository;

import com.example.muzfi.Model.CommunityRule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommunityRuleRepository extends MongoRepository<CommunityRule, String> {
    List<CommunityRule> findByCommunityId(String id);
}
