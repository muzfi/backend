package com.example.muzfi.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBan {
    @Id
    private String id;
    private String bannedUserId;
    private String moderatorId;
    private String reason;
    private LocalDate banStartDate;
    private LocalDate banEndDate;

    public UserBan(String bannedUserId, String moderatorId, String reason, LocalDate banStartDate, LocalDate banEndDate) {
        this.bannedUserId = bannedUserId;
        this.moderatorId = moderatorId;
        this.reason = reason;
        this.banStartDate = banStartDate;
        this.banEndDate = banEndDate;
    }
}
