package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    List<Topic> findAllByAuthorId(String userId);

    List<Topic> findByTitleContainingIgnoreCase(String name);
}
