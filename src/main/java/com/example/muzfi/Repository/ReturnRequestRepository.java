package com.example.muzfi.Repository;

import com.example.muzfi.Model.ReturnRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReturnRequestRepository extends MongoRepository<ReturnRequest, String> {
    Optional<Object> findById(Long requestId);
    // Custom database queries if needed
}
