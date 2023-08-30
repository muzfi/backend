package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TopicRepository extends MongoRepository<Topic, String> {

    List<Topic> findByTitleContainingIgnoreCase(String name);
}
