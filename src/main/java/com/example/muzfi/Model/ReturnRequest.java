package com.example.muzfi.Model;

import com.example.muzfi.Enums.ReturnStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "return_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReturnRequest {
    @Id
    private String requestId;
    private String itemId;
    private String userId; // Buyer's user ID
    private String reason;
    private String comment;
    private List<String> photos;
    private ReturnResponse response;
    private String trackingNumber;
    private ReturnStatus status;
}
