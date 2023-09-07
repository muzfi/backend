package com.example.muzfi.Repository;

import com.example.muzfi.Model.UserSetting;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSettingRepository extends MongoRepository<UserSetting, String> {

    Optional<UserSetting> findByUserId(String userId);
}
