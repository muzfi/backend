package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}