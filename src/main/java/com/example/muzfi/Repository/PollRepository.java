package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends MongoRepository<Poll, String> {
    List<Poll> findAllByAuthorId(String userId);

    List<Poll> findByTitleContainingIgnoreCase(String name);
}
