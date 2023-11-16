package com.example.muzfi.Controller;

import com.example.muzfi.Model.UserApproval;
import com.example.muzfi.Services.User.UserApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-approval")
@RequiredArgsConstructor
public class UserApprovalController {
    private final UserApprovalService userApprovalService;

    @GetMapping("/moderator/{moderatorId}")
    public ResponseEntity<List<UserApproval>> getApprovalsForModerator(@PathVariable String moderatorId) {
        List<UserApproval> approvals = userApprovalService.getApprovalsForModerator(moderatorId);
        return new ResponseEntity<>(approvals, HttpStatus.OK);
    }

    @GetMapping("/approved/{approvedUserId}")
    public ResponseEntity<List<UserApproval>> getApprovedUsersForUser(@PathVariable String approvedUserId) {
        List<UserApproval> approvals = userApprovalService.getApprovedUsersForUser(approvedUserId);
        return new ResponseEntity<>(approvals, HttpStatus.OK);
    }

    @PostMapping("/approve")
    public ResponseEntity<UserApproval> approveUser(
            @RequestParam String moderatorId,
            @RequestParam String approvedUserId) {
        UserApproval userApproval = userApprovalService.approveUser(moderatorId, approvedUserId);
        return new ResponseEntity<>(userApproval, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{approvalId}")
    public ResponseEntity<Void> removeApproval(@PathVariable String approvalId) {
        userApprovalService.removeApproval(approvalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
