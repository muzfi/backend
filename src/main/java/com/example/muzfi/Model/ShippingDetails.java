package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@AllArgsConstructor
public class ShippingDetails {
    @Id
    private String shippingId;
    private String trackingNumber;
    private String carrier;
    private String status; // e.g., "Shipped", "In Transit", "Delivered"

    public ShippingDetails() {

    }

    // Constructors, getters, and setters
}
