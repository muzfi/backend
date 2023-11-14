package com.example.muzfi.Repository;

import com.example.muzfi.Model.UserSubscriptionSetting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserSubscriptionSettingRepository extends MongoRepository<UserSubscriptionSetting,String> {
    Optional<UserSubscriptionSetting> findByUserId(String userId);
}
