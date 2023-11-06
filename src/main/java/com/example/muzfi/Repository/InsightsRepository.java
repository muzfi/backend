package com.example.muzfi.Repository;

import com.example.muzfi.Model.Insights;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InsightsRepository extends MongoRepository<Insights,String> {
}
