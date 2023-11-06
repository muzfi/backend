package com.example.muzfi.Services.User;

import com.example.muzfi.Model.User;
import com.example.muzfi.Model.UserBan;
import com.example.muzfi.Repository.UserBanRepository;
import com.example.muzfi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBanServiceImpl implements UserBanService{
    private final UserBanRepository userBanRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserBan> getBansForModerator(String moderatorId) {
        return userBanRepository.findByModeratorId(moderatorId);
    }

    @Override
    public UserBan banUser(String bannedUserId, String moderatorId, String reason, LocalDate banEndDate) {
        UserBan userBan = new UserBan(bannedUserId, moderatorId, reason, LocalDate.now(), banEndDate);
        userBanRepository.save(userBan);

        User bannedUser = userRepository.findById(bannedUserId).orElse(null);
        if (bannedUser != null) {
            bannedUser.setBanned(true);
            bannedUser.setBanReason(reason);
            bannedUser.setBanStartDate(LocalDate.now());
            bannedUser.setBanEndDate(banEndDate);
            userRepository.save(bannedUser);
        }
        return userBan;
    }

    @Override
    public void editBan(String banId, String reason, LocalDate banEndDate) {
        UserBan userBan = userBanRepository.findById(banId).orElse(null);
        if (userBan != null) {
            userBan.setReason(reason);
            userBan.setBanEndDate(banEndDate);
            userBanRepository.save(userBan);
        }
    }

    @Override
    public void unbanUser(String banId) {
        UserBan userBan = userBanRepository.findById(banId).orElse(null);
        if (userBan != null) {
            userBanRepository.delete(userBan);

            User bannedUser = userRepository.findById(userBan.getBannedUserId()).orElse(null);
            if (bannedUser != null) {
                bannedUser.setBanned(false);
                bannedUser.setBanReason(null);
                bannedUser.setBanStartDate(null);
                bannedUser.setBanEndDate(null);
                userRepository.save(bannedUser);
            }
        }
    }

}
