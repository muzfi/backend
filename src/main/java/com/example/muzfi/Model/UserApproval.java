package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class UserApproval {
    @Id
    private String id;
    private String moderatorId;
    private String approvedUserId;

    public UserApproval(String moderatorId, String approvedUserId) {
        this.moderatorId = moderatorId;
        this.approvedUserId = approvedUserId;
    }
}
