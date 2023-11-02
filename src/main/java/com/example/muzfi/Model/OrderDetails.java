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
    private String orderEmail;
    private double orderAmount;

    @DBRef
    private Product product;
    @DBRef
    private User user;

    public OrderDetails(String orderFullName, String orderFullAddress, String orderContactNumber, String orderAlternateContactNumber, String orderPlaced, String orderEmail, double orderAmount, Product product, User user) {
        this.OrderFullName = orderFullName;
        this.OrderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternateContactNumber = orderAlternateContactNumber;
        this.orderStatus = orderPlaced;
        this.orderEmail = orderEmail;
        this.orderAmount = orderAmount;
        this.product = product;
        this.user = user;
    }

}
