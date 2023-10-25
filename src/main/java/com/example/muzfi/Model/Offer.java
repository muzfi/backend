package com.ecommerce.shoppingmarket.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "offers")
public class Offer {
    @Id
    private String id;
    private String userId;
    private String productId;
    private double amount;

}
