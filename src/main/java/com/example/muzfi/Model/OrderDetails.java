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

    public OrderDetails(String orderFullName, String orderFullAddress, String orderContactNumber, String orderAlternateContactNumber, String orderStatus, double orderAmount, Product product, User user) {
        OrderFullName = orderFullName;
        OrderFullAddress = orderFullAddress;
        this.orderContactNumber = orderContactNumber;
        this.orderAlternateContactNumber = orderAlternateContactNumber;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.product = product;
        this.user = user;
    }
}
