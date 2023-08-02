package com.example.muzfi.Controller;

import com.example.muzfi.Model.Review;
import com.example.muzfi.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        try {
            Optional<List<Review>> reviews = reviewService.getAllReviews();

            if (reviews.isPresent()) {
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Reviews Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getReviewsByPostId(@PathVariable("postId") String postId) {
        try {
            Optional<List<Review>> reviews = reviewService.getReviewsByPostId(postId);

            if (reviews.isPresent()) {
                return new ResponseEntity<>(reviews, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Reviews Available", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        try {
            Review createdReview = reviewService.createReview(review);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("reviewId") String reviewId, @RequestBody Review review) {
        try {
            Review updatedReview = reviewService.updateReview(reviewId, review);
            if (updatedReview != null) {
                return new ResponseEntity<>(updatedReview, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Review Found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable String reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return new ResponseEntity<>("an unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
