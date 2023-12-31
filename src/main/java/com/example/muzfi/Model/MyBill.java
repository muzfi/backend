package com.example.muzfi.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "mybill")
public class MyBill {
    @Id
    private String id;
    private String userId;
    private Date billDate;
    private List<Product> product;
    private double amount;
    private boolean  billingInfoReviewed;

    public boolean isBillingInfoReviewed() {
        // Implement the logic to check if billing information has been reviewed
        return billingInfoReviewed;
    }

    public void setBillingInfoReviewed(boolean billingInfoReviewed) {
        this.billingInfoReviewed = billingInfoReviewed;
    }
}
