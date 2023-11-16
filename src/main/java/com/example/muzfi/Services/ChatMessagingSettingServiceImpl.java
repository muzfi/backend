package com.example.muzfi.Services;

import com.example.muzfi.Model.ChatMessagingSetting;
import com.example.muzfi.Repository.ChatMessagingSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatMessagingSettingServiceImpl implements ChatMessagingSettingService {
    private final ChatMessagingSettingRepository chatMessagingSettingRepository;
    @Override
    public ChatMessagingSetting createChatMessagingSettings(ChatMessagingSetting settings) {
        return chatMessagingSettingRepository.save(settings);
    }

    @Override
    public Optional<ChatMessagingSetting> getChatMessagingSettingsByUserId(String userId) {
        return chatMessagingSettingRepository.findByUserId(userId);
    }

    @Override
    public Optional<ChatMessagingSetting> updateChatMessagingSettings(ChatMessagingSetting settings) {
        Optional<ChatMessagingSetting> chatMessagingSettingsOptional = chatMessagingSettingRepository.findByUserId(settings.getUserId());

        if (chatMessagingSettingsOptional.isPresent()) {
            ChatMessagingSetting existingSettings = chatMessagingSettingsOptional.get();
            existingSettings.setAllowPrivateMessages(settings.isAllowPrivateMessages());
            existingSettings.setMarkAllAsRead(settings.isMarkAllAsRead());

            ChatMessagingSetting updatedSettings = chatMessagingSettingRepository.save(existingSettings);
            return Optional.of(updatedSettings);
        } else {
            return Optional.empty();
        }
    }
}
