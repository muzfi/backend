package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "feedbacks")
    public class Feedback {
        @Id
        private String id;
        private String sellerId;
        private String buyerId;
        private Integer qualityRating;
        private Integer reliabilityRating;
        private Integer expectationRating;

        // Constructors, getters, and setters
    }
