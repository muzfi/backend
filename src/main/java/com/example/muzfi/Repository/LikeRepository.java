package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends MongoRepository<Like, String> {

    Optional<Like> findByPostIdAndUserId(String postId, String userId);

    List<Like> findAllByPostId(String postId);
}
