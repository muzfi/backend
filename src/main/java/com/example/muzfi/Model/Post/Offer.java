package com.example.muzfi.Model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "offers")
public class Offer {
    @Id
    private String id;

    private String userId;

    private String userEmail;

    private String listingId;

    private BigDecimal offerAmount;

    private String comment;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
