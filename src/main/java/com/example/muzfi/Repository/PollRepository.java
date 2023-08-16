package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PollRepository extends MongoRepository<Poll, String> {
}
