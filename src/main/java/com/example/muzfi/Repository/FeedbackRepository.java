package com.example.muzfi.Repository;

import com.example.muzfi.Model.Feedback;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    List<Feedback> findBySellerId(String sellerId);
    List<Feedback> findByBuyerId(String buyerId);
}
