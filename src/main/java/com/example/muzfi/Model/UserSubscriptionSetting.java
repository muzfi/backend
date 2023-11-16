package com.example.muzfi.Model;

import com.example.muzfi.Enums.SubscriptionPlan;
import com.example.muzfi.Enums.SubscriptionType;
import com.example.muzfi.Enums.UserSubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "user_subscription_setting")
public class UserSubscriptionSetting {
    @Id
    private String id;

    private String userId;

    private SubscriptionPlan subscriptionPlan;

    private SubscriptionType subscriptionType;

    private UserSubscriptionStatus subscriptionStatus;
    private LocalDateTime lastUpdateTime;
}
