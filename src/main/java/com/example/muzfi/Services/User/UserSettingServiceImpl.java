package com.example.muzfi.Services.User;

import com.example.muzfi.Model.UserSetting;
import com.example.muzfi.Repository.UserSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserSettingServiceImpl implements UserSettingService {

    private final UserSettingRepository userSettingRepository;

    @Autowired
    public UserSettingServiceImpl(UserSettingRepository userSettingRepository) {
        this.userSettingRepository = userSettingRepository;
    }

    @Override
    public UserSetting createUserSetting(UserSetting setting) {
        return userSettingRepository.save(setting);
    }

    @Override
    public Optional<UserSetting> getUserSettingByUserId(String userid) {
        return userSettingRepository.findByUserId(userid);
    }

    @Override
    public Optional<UserSetting> updateUserSetting(UserSetting setting) {
        Optional<UserSetting> userSettingOptional = userSettingRepository.findById(setting.getId());

        if (userSettingOptional.isPresent()) {
            UserSetting existingUserSetting = userSettingOptional.get();
            existingUserSetting.setDisplayLang(setting.getDisplayLang());
            existingUserSetting.setContentLang(setting.getContentLang());
            existingUserSetting.setIsAllowToFollow(setting.getIsAllowToFollow());
            existingUserSetting.setIsContentVisible(setting.getIsContentVisible());
            existingUserSetting.setIsActiveInCommunityVisible(setting.getIsActiveInCommunityVisible());
            existingUserSetting.setIsShowsUpInSearchResults(setting.getIsShowsUpInSearchResults());
            existingUserSetting.setLastUpdatedDateTime(LocalDateTime.now());

            UserSetting updatedSetting = userSettingRepository.save(existingUserSetting);

            return Optional.of(updatedSetting);

        } else {
            return Optional.empty();
        }
    }
}
