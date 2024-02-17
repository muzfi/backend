package com.example.muzfi.Controller;

import com.example.muzfi.Dto.FeedbackDTO;
import com.example.muzfi.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/feedback/seller")
    public void submitSellerFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        ratingService.submitSellerFeedback(feedbackDTO);
    }

    @PostMapping("/feedback/buyer")
    public void submitBuyerFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        ratingService.submitBuyerFeedback(feedbackDTO);
    }
}
