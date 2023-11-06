package com.example.muzfi.Controller;

import com.example.muzfi.Model.UserBan;
import com.example.muzfi.Services.User.UserBanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/user-ban")
@RequiredArgsConstructor
public class UserBanController {
    private final UserBanService userBanService;

    @GetMapping("/{moderatorId}")
    public ResponseEntity<List<UserBan>> getBansForModerator(@PathVariable String moderatorId) {
        List<UserBan> bans = userBanService.getBansForModerator(moderatorId);
        return new ResponseEntity<>(bans, HttpStatus.OK);
    }

    @PostMapping("/ban")
    public ResponseEntity<UserBan> banUser(
            @RequestParam String moderatorId,
            @RequestParam String bannedUserId,
            @RequestParam String reason,
            @RequestParam String banEndDate) {
        LocalDate endDate = LocalDate.parse(banEndDate);
        UserBan userBan = userBanService.banUser(moderatorId, bannedUserId, reason, endDate);
        return new ResponseEntity<>(userBan, HttpStatus.OK);
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editBan(
            @RequestParam String banId,
            @RequestParam String reason,
            @RequestParam String banEndDate) {
        LocalDate endDate = LocalDate.parse(banEndDate);
        userBanService.editBan(banId, reason, endDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/unban/{banId}")
    public ResponseEntity<Void> unbanUser(@PathVariable String banId) {
        userBanService.unbanUser(banId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
