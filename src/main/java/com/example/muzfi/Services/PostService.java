package com.example.muzfi.Services;

import com.example.muzfi.Model.Post;

import java.util.Optional;

public interface PostService {
    Iterable<Post> getAllPosts();

    Optional<Post> getPost(String id);

    Post savePost(Post post);

    void deletePost(String id);
}
