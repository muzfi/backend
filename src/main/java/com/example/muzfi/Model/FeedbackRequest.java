package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackRequest {
    private String purchaseId;
    private String feedbackMessage;
}
