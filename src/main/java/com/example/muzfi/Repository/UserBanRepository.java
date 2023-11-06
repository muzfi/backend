package com.example.muzfi.Repository;

import com.example.muzfi.Model.UserBan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserBanRepository extends MongoRepository<UserBan,String> {
    List<UserBan> findByModeratorId(String moderatorId);
}
