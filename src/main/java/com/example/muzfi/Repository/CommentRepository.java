package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByPostIdAndIsParentComment(String postId, boolean isParentComment);

    Optional<Integer> countAllByPostId(String postId);
}
