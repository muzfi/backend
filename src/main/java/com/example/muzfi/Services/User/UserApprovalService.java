package com.example.muzfi.Services.User;

import com.example.muzfi.Model.UserApproval;

import java.util.List;

public interface UserApprovalService {
    List<UserApproval> getApprovalsForModerator(String moderatorId);
    List<UserApproval> getApprovedUsersForUser(String approvedUserId);
    UserApproval approveUser(String moderatorId, String approvedUserId);
    void removeApproval(String approvalId);
}
