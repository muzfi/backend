package com.example.muzfi.Repository;

import com.example.muzfi.Model.ReturnRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReturnRequestRepository extends MongoRepository<ReturnRequest, String> {
}