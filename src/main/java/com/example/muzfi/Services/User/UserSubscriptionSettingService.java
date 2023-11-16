package com.example.muzfi.Services.User;

import com.example.muzfi.Model.UserSubscriptionSetting;

import java.util.Optional;

public interface UserSubscriptionSettingService {
    UserSubscriptionSetting createUserSubscriptionSetting(UserSubscriptionSetting subscriptionSetting);

    Optional<UserSubscriptionSetting> getUserSubscriptionSettingByUserId(String userId);

    Optional<UserSubscriptionSetting> updateUserSubscriptionSetting(UserSubscriptionSetting subscriptionSetting);
}
