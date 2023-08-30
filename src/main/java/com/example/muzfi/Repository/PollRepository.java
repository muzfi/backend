package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PollRepository extends MongoRepository<Poll, String> {
    List<Poll> findByTitleContainingIgnoreCase(String name);
}
