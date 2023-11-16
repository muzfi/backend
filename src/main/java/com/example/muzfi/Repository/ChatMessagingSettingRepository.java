package com.example.muzfi.Repository;

import com.example.muzfi.Model.ChatMessagingSetting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ChatMessagingSettingRepository extends MongoRepository<ChatMessagingSetting, String> {
    Optional<ChatMessagingSetting> findByUserId(String userId);
}
