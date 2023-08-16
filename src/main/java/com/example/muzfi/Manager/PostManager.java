package com.example.muzfi.Manager;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.User;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PostManager {

    private final LikeService likeService;

    private final AuthService authService;

    @Autowired
    public PostManager(LikeService likeService, AuthService authService) {
        this.likeService = likeService;
        this.authService = authService;
    }

    //Creates PostDetailsDto including post details and postType details
    public PostDetailsDto getPostDetailsDto(Post post, Object postTypeData, PostAuthorDto author) {
        PostDetailsDto postDetailsDto = new PostDetailsDto();

        postDetailsDto.setId(post.getId());
        postDetailsDto.setAuthor(author);
        postDetailsDto.setType(post.getPostType().toString());
        postDetailsDto.setPostContent(postTypeData);
        postDetailsDto.setShares(post.getShares());
        postDetailsDto.setCreatedDateTime(post.getCreatedDateTime());
        postDetailsDto.setUpdatedDateTime(post.getUpdatedDateTime());
        postDetailsDto.setIsDraft(post.getIsDraft());

        //set like count and isLiked
        Optional<List<String>> likedUserIdsOptional = likeService.getLikedUserIdsByPostId(post.getId());

        if (likedUserIdsOptional.isPresent()) {
            List<String> likedUserIds = likedUserIdsOptional.get();

            int likedUsersCount = likedUserIds.size();
            postDetailsDto.setLikes(likedUsersCount);
            User loggedInUser;

            try {
                loggedInUser = authService.getLoggedInUser();
            } catch (Exception ex) {
                loggedInUser = null;
            }

            if (loggedInUser != null) {
                for (String userid : likedUserIds) {
                    if (userid.equals(loggedInUser.getId())) {
                        postDetailsDto.setIsLiked(true);
                        break;
                    }
                }
            }
        }

        //TODO: Set comment count

        return postDetailsDto;
    }
}
