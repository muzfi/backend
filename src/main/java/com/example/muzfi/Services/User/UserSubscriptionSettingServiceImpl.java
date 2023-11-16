package com.example.muzfi.Services.User;

import com.example.muzfi.Model.UserSubscriptionSetting;
import com.example.muzfi.Repository.UserSubscriptionSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSubscriptionSettingServiceImpl implements UserSubscriptionSettingService {
    private final UserSubscriptionSettingRepository userSubscriptionSettingRepository;

    @Override
    public UserSubscriptionSetting createUserSubscriptionSetting(UserSubscriptionSetting subscriptionSetting) {
        return userSubscriptionSettingRepository.save(subscriptionSetting);
    }

    @Override
    public Optional<UserSubscriptionSetting> getUserSubscriptionSettingByUserId(String userId) {
        return userSubscriptionSettingRepository.findByUserId(userId);
    }

    @Override
    public Optional<UserSubscriptionSetting> updateUserSubscriptionSetting(UserSubscriptionSetting subscriptionSetting) {
        Optional<UserSubscriptionSetting> existingSubscriptionSetting = userSubscriptionSettingRepository.findById(subscriptionSetting.getId());

        if (existingSubscriptionSetting.isPresent()) {
            UserSubscriptionSetting existingSetting = existingSubscriptionSetting.get();
            existingSetting.setSubscriptionPlan(subscriptionSetting.getSubscriptionPlan());
            existingSetting.setSubscriptionType(subscriptionSetting.getSubscriptionType());
            existingSetting.setSubscriptionStatus(subscriptionSetting.getSubscriptionStatus());
            existingSetting.setLastUpdateTime(LocalDateTime.now());

            UserSubscriptionSetting updatedSetting = userSubscriptionSettingRepository.save(existingSetting);

            return Optional.of(updatedSetting);
        } else {
            return Optional.empty();
        }
    }
}
