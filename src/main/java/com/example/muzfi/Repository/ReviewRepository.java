package com.example.muzfi.Repository;

import com.example.muzfi.Model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findAllByPostId(String postId);

    List<Review> findByTextContainingIgnoreCase(String name);
}
