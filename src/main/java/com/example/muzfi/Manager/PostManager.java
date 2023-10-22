package com.example.muzfi.Manager;

import com.example.muzfi.Dto.PostDto.PollDetailsDto;
import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Poll;
import com.example.muzfi.Model.Post.PollOption;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.User;
import com.example.muzfi.Services.AuthService;
import com.example.muzfi.Services.Post.CommentService;
import com.example.muzfi.Services.Post.LikeService;
import com.example.muzfi.Services.Post.PollOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostManager {

    private final LikeService likeService;

    private final CommentService commentService;

    private final PollOptionService pollOptionService;

    private final AuthService authService;

    @Autowired
    public PostManager(LikeService likeService, CommentService commentService, PollOptionService pollOptionService, AuthService authService) {
        this.likeService = likeService;
        this.commentService = commentService;
        this.pollOptionService = pollOptionService;
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
        postDetailsDto.setLikes(0);

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

        //Set comment count
        Optional<Integer> commentCountOpt = commentService.getPostCommentCount(post.getId());
        commentCountOpt.ifPresent(postDetailsDto::setComments);

        return postDetailsDto;
    }

    public PollDetailsDto getPollDetailsDto(Poll poll) {
        PollDetailsDto pollDetailsDto = new PollDetailsDto();

        //Set pollOptions
        List<PollOption> pollOptions = new ArrayList<>();
        if (!poll.getPollOptionIds().isEmpty()) {
            Optional<List<PollOption>> pollOptionsOpt = pollOptionService.getPollOptionsByIds(poll.getPollOptionIds());

            if (pollOptionsOpt.isPresent()) {
                pollOptions = pollOptionsOpt.get();
            }
        }

        //Set isVoted
        User loggedInUser;
        try {
            loggedInUser = authService.getLoggedInUser();
        } catch (Exception ex) {
            loggedInUser = null;
        }

        boolean isVoted = false;
        if (!pollOptions.isEmpty() && loggedInUser != null) {
            Set<String> idList = new HashSet<>();

            for (PollOption option : pollOptions) {
                if (option.getVotedUserIds() != null) {
                    idList.addAll(option.getVotedUserIds());
                }
            }

            if (!idList.isEmpty()) {
                for (String id : idList) {
                    if (id.equals(loggedInUser.getId())) {
                        isVoted = true;
                        break;
                    }
                }
            }
        }

        pollDetailsDto.setId(poll.getId());
        pollDetailsDto.setPostId(poll.getPostId());
        pollDetailsDto.setAuthorId(poll.getAuthorId());
        pollDetailsDto.setTitle(poll.getTitle());
        pollDetailsDto.setText(poll.getText());
        pollDetailsDto.setPollOptions(pollOptions);
        pollDetailsDto.setPollDeadline(poll.getPollDeadline());
        pollDetailsDto.setPostCategories(poll.getPostCategories());
        pollDetailsDto.setTags(poll.getTags());
        pollDetailsDto.setCreatedDateTime(poll.getCreatedDateTime());
        pollDetailsDto.setUpdatedDateTime(poll.getUpdatedDateTime());
        pollDetailsDto.setIsVoted(isVoted);

        return pollDetailsDto;
    }
}
