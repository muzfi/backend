package com.example.muzfi.Services;

import com.example.muzfi.Dto.FeedbackDTO;

public interface RatingService {
    void submitSellerFeedback(FeedbackDTO feedbackDTO);

    void submitBuyerFeedback(FeedbackDTO feedbackDTO);

    void updateSellerRating(String sellerId);

    void updateBuyerRating(String buyerId);
}
