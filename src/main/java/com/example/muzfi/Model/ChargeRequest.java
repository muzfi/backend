package com.example.muzfi.Model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class ChargeRequest {
    @Id
    private String id;
    private String description;
    private int amount;
    private String currency;
    private String stripeEmail;
    private String stripeToken;
}
