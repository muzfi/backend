package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.time.LocalDateTime;
import java.util.List;

@Document (collection = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Purchase {
    @Id
    private String purchaseId;
    @DocumentReference(collection = "users")
    private User user;
    @DocumentReference(collection = "products")
    private List<Product> products;
    private Double totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    private String orderStatus;
    private LocalDateTime purchaseDate;
    private long trackingNumber;
    private String feedBack;
    private Double payouts;
}
