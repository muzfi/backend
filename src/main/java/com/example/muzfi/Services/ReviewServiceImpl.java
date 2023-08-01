package com.example.muzfi.Services;

import com.example.muzfi.Model.Review;
import com.example.muzfi.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<List<Review>> getAllReviews() {
        return Optional.of(reviewRepository.findAll());
    }

    @Override
    public Optional<List<Review>> getReviewsByPostId(String postId) {
        return Optional.ofNullable(reviewRepository.findAllByPostId(postId));
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(String reviewId, Review updatedReview) {
        Review existingReview = reviewRepository.findById(reviewId).orElse(null);
        if (existingReview != null) {
            existingReview.setText(updatedReview.getText());

            //TODO: update with user date and time
            existingReview.setUpdatedDateTime(LocalDateTime.now());
            return reviewRepository.save(existingReview);
        }
        return null;
    }

    @Override
    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
