package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.LikedUserDto;
import com.example.muzfi.Model.Post.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    Optional<Like> createLike(String postId, String userId);

    Optional<String> removeLike(String postId, String userId);

    Optional<List<String>> getLikedUserIdsByPostId(String postId);

    Optional<List<LikedUserDto>> getLikedUsersByPostId(String postId);

    Optional<List<String>> getLikeIdsByPostId(String postId);

    void deleteLikesByIds(List<String> idList);
}
