package com.example.muzfi.Services.User;

import com.example.muzfi.Model.UserSetting;

import java.util.Optional;

public interface UserSettingService {
    UserSetting createUserSetting(UserSetting setting);

    Optional<UserSetting> getUserSettingByUserId(String userid);

    Optional<UserSetting> updateUserSetting(UserSetting setting);
}
