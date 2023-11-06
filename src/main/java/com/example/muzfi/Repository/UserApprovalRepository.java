package com.example.muzfi.Repository;

import com.example.muzfi.Model.UserApproval;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserApprovalRepository extends MongoRepository<UserApproval, String> {
    List<UserApproval> findByModeratorId(String moderatorId);
    List <UserApproval> findByApprovedUserId(String approvedUserId);
}
