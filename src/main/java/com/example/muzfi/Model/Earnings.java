package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "earnings")
public class Earnings {
    @Id
    private String id;
    private String userId;
    private String orderId;
    private double amount;
    private Date earningDate;
    private String paymentMethod;
    private String status;
}
