package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class OrderInput {
    private String OrderFullName;
    private String OrderFullAddress;
    private String orderContactNumber;
    private String orderAlternateContactNumber;
    private String orderEmail;
    private List<OrderProductQuantity> orderProductQuantityList;
}
