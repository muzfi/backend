package com.example.muzfi.Services;

import com.example.muzfi.Model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<List<Review>> getAllReviews();

    Optional<List<Review>> getReviewsByPostId(String postId);

    Review createReview(Review review);

    Review updateReview(String reviewId, Review review);

    void deleteReview(String reviewId);
}
