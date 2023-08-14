package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<List<PostDetailsDto>> getAllPosts();

    Optional<PostDetailsDto> getPostById(String id);

    Post savePost(Post post);

    void deletePost(String id);
}
