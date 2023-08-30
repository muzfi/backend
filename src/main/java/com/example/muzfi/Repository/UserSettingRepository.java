package com.example.muzfi.Repository;

import com.example.muzfi.Model.UserSetting;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserSettingRepository extends MongoRepository<UserSetting, String> {

    Optional<UserSetting> findByUserId(String userId);
}
