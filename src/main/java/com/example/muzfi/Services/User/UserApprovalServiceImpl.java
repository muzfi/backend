package com.example.muzfi.Services.User;

import com.example.muzfi.Model.User;
import com.example.muzfi.Model.UserApproval;
import com.example.muzfi.Repository.UserApprovalRepository;
import com.example.muzfi.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserApprovalServiceImpl implements UserApprovalService{
   private final UserApprovalRepository userApprovalRepository;
   private final UserRepository userRepository;
    @Override
    public List<UserApproval> getApprovalsForModerator(String moderatorId) {
        return userApprovalRepository.findByModeratorId(moderatorId);
    }

    @Override
    public List<UserApproval> getApprovedUsersForUser(String approvedUserId) {
        return userApprovalRepository.findByApprovedUserId(approvedUserId);
    }

    @Override
    public UserApproval approveUser(String moderatorId, String approvedUserId) {
        UserApproval userApproval = new UserApproval(moderatorId, approvedUserId);
        userApprovalRepository.save(userApproval);

        User approvedUser = userRepository.findById(approvedUserId).orElse(null);
        if (approvedUser != null) {
            approvedUser.setApproved(true);
            userRepository.save(approvedUser);
        }

        return userApproval;
    }

    @Override
    public void removeApproval(String approvalId) {
        UserApproval userApproval = userApprovalRepository.findById(approvalId).orElse(null);
        if (userApproval != null) {
            userApprovalRepository.delete(userApproval);

            User approvedUser = userRepository.findById(userApproval.getApprovedUserId()).orElse(null);
            if (approvedUser != null) {
                approvedUser.setApproved(false);
                userRepository.save(approvedUser);
            }
        }
    }
}
