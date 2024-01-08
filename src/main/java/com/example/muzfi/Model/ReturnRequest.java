package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "ReturnRequest")
public class ReturnRequest {
    @Id
    private Long id;
    private Long productId;
    private String reason;
    private String status; // e.g., "Requested", "Accepted", "Declined"
    private String buyerMessage;
    // other fields, getters, setters
}

