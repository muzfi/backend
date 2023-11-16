package com.example.muzfi.Services;

import com.example.muzfi.Model.ChatMessagingSetting;

import java.util.Optional;

public interface ChatMessagingSettingService {
    ChatMessagingSetting createChatMessagingSettings(ChatMessagingSetting settings);

    Optional<ChatMessagingSetting> getChatMessagingSettingsByUserId(String userId);

    Optional<ChatMessagingSetting> updateChatMessagingSettings(ChatMessagingSetting settings);
}
