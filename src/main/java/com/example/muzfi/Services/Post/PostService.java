package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PostDetailsDto;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<List<PostDetailsDto>> getAllPosts();

    Optional<PostDetailsDto> getPostById(String id);

    Optional<List<PostDetailsDto>> getPostsByUserId(String userId);

    Optional<List<PostDetailsDto>> getDraftPostsByUserId(String userId);

    Optional<PostDetailsDto> publishDraftPost(String postId);

    void deletePost(String id);
}
