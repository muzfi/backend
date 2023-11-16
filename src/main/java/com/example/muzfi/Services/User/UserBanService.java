package com.example.muzfi.Services.User;

import com.example.muzfi.Model.UserBan;

import java.time.LocalDate;
import java.util.List;

public interface UserBanService {
    List<UserBan> getBansForModerator(String moderatorId);
    public UserBan banUser(String bannedUserId, String moderatorId, String reason, LocalDate banEndDate);
    void editBan(String banId, String reason, LocalDate banEndDate);
    void unbanUser(String banId);
}
