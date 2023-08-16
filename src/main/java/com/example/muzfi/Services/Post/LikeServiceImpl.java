package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.LikedUserDto;
import com.example.muzfi.Model.Post.Like;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.LikeRepository;
import com.example.muzfi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;

    private final UserService userService;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, UserService userService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Like> createLike(String postId, String userId) {
        Like newLike = new Like();

        newLike.setPostId(postId);
        newLike.setUserId(userId);

        return Optional.of(likeRepository.save(newLike));
    }

    @Override
    public Optional<String> removeLike(String postId, String userId) {
        Optional<Like> likeOptional = likeRepository.findByPostIdAndUserId(postId, userId);

        if (likeOptional.isPresent()) {
            likeRepository.delete(likeOptional.get());
            return Optional.of("Like removed from post");
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<String>> getLikedUserIdsByPostId(String postId) {
        List<Like> postLikes = likeRepository.findAllByPostId(postId);
        List<String> likeList = new ArrayList<>();

        if (postLikes.isEmpty()) {
            return Optional.empty();
        }

        for (Like like : postLikes) {
            likeList.add(like.getUserId());
        }

        return Optional.of(likeList);
    }

    @Override
    public Optional<List<LikedUserDto>> getLikedUsersByPostId(String postId) {
        Optional<List<String>> likedUserIdsOptional = getLikedUserIdsByPostId(postId);

        if (likedUserIdsOptional.isEmpty()) {
            return Optional.empty();
        }

        List<LikedUserDto> likedUsersList = new ArrayList<>();

        for (String userId : likedUserIdsOptional.get()) {
            Optional<User> userOptional = userService.getUserById(userId);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                LikedUserDto likedUser = new LikedUserDto();

                likedUser.setUserId(user.getId());
                likedUser.setUserName(user.getUserName());
                likedUser.setName(user.getFirstName() + " " + user.getLastName());
                likedUser.setAvatar(user.getProfilePicUri());

                likedUsersList.add(likedUser);
            }
        }

        return Optional.of(likedUsersList);
    }
}
