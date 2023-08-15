package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<Topic, String> {
}
