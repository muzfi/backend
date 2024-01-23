package com.example.muzfi.Services;

import com.example.muzfi.Model.NotificationSettings;
import com.example.muzfi.Repository.NotificationSettingsRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailNotification.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationSettingsServiceImpl implements NotificationSettingsService{
    private final NotificationSettingsRepository notificationSettingsRepository;

    @Override
    public NotificationSettings createNotificationSettings(NotificationSettings settings) {
        EmailNotificationService emailNotificationService = new EmailNotificationService();
        emailNotificationService.sendUncheckedNotificationsAlert(settings.getUserId(),1);
        return notificationSettingsRepository.save(settings);

    }

    @Override
    public Optional<NotificationSettings> getNotificationSettingsByUserId(String userId) {
        return notificationSettingsRepository.findByUserId(userId);
    }

    @Override
    public Optional<NotificationSettings> updateNotificationSettings(NotificationSettings settings) {
        Optional<NotificationSettings> notificationSettingsOptional = notificationSettingsRepository.findByUserId(settings.getUserId());

        if (notificationSettingsOptional.isPresent()) {
            NotificationSettings existingSettings = notificationSettingsOptional.get();
            existingSettings.setInboxMessages(settings.isInboxMessages());
            existingSettings.setChatMessages(settings.isChatMessages());
            existingSettings.setActivity(settings.isActivity());
            existingSettings.setMentions(settings.isMentions());
            existingSettings.setCommentsOnPosts(settings.isCommentsOnPosts());
            existingSettings.setUpvoteOnPosts(settings.isUpvoteOnPosts());
            existingSettings.setUpvoteOnComments(settings.isUpvoteOnComments());
            existingSettings.setRepliesToComments(settings.isRepliesToComments());
            existingSettings.setActivityOnComments(settings.isActivityOnComments());
            existingSettings.setActivityOnThreads(settings.isActivityOnThreads());
            existingSettings.setActivityOnChatPosts(settings.isActivityOnChatPosts());
            existingSettings.setOffersMadeAccepted(settings.isOffersMadeAccepted());
            existingSettings.setOrders(settings.isOrders());

            NotificationSettings updatedSettings = notificationSettingsRepository.save(existingSettings);
            return Optional.of(updatedSettings);
        } else {
            return Optional.empty();
        }
    }
}
