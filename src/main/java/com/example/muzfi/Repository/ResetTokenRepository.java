package com.example.muzfi.Repository;

import com.example.muzfi.Model.ResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResetTokenRepository extends MongoRepository<ResetToken, String> {
    ResetToken findByToken(String token);
    void deleteByToken(String token);
}

