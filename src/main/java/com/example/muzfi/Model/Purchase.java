package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

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
    private Product product;
    private Double totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    private String orderStatus;
    //private Date purchaseDate;
}
