package com.example.muzfi.Repository;

import com.example.muzfi.Model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
    // You can add custom query methods here if needed
}
