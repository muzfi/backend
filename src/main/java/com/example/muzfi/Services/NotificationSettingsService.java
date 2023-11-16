package com.example.muzfi.Services;

import com.example.muzfi.Model.NotificationSettings;

import java.util.Optional;

public interface NotificationSettingsService {
    NotificationSettings createNotificationSettings(NotificationSettings settings);

    Optional<NotificationSettings> getNotificationSettingsByUserId(String userId);

    Optional<NotificationSettings> updateNotificationSettings(NotificationSettings settings);
}

