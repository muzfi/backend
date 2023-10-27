package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@AllArgsConstructor
public class OrderDetails {
    @Id
    private String OrderId;
    private String OrderFullName;
    private String OrderFullAddress;
    private String orderContactNumber;
    private String orderAlternateContactNumber;
    private String orderStatus;
    private double orderAmount;
    @DBRef
    private Product product;
    @DBRef
    private User user;

    public OrderDetails(String orderFullName, String orderFullAddress, String orderContactNumber, String orderAlternateContactNumber, String orderPlaced, double v, Product product, User user) {
    }
}
