package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PollCreateDto;
import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Poll;
import com.example.muzfi.Model.Post.PollOption;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Repository.PollRepository;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PollServiceImpl implements PollService{

    private final PollRepository pollRepository;

    private final PostRepository postRepository;

    private final UserService userService;

    private final PostManager postManager;

    @Autowired
    public PollServiceImpl(PollRepository pollRepository, PostRepository postRepository, UserService userService, PostManager postManager) {
        this.pollRepository = pollRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.postManager = postManager;
    }

    @Override
    public Optional<PostDetailsDto> createPoll(PollCreateDto pollDto) {
        //create post
        Post newPost = new Post();
        newPost.setAuthorId(pollDto.getAuthorId());
        newPost.setPostType(PostType.PROD_POLL);
        newPost.setIsEnablePostReplyNotification(pollDto.getIsEnablePostReplyNotification());
        newPost.setCreatedDateTime(pollDto.getCreatedDateTime());
        newPost.setUpdatedDateTime(pollDto.getCreatedDateTime());
        newPost.setIsDraft(pollDto.getIsDraft());

        //creat poll options
        List<PollOption> pollOptions = new ArrayList<>();
        for (String optionText: pollDto.getOptions()) {
            PollOption option = new PollOption();
            option.setOptionText(optionText);
            pollOptions.add(option);
        }

        //create topic
        Poll newPoll = new Poll();
        newPoll.setAuthorId(pollDto.getAuthorId());
        newPoll.setTitle(pollDto.getTitle());
        newPoll.setText(pollDto.getDescription());
        newPoll.setPollOptions(pollOptions);
        newPoll.setPollDeadline(pollDto.getDeadline());
        newPoll.setPostCategories(pollDto.getPostCategories());
        newPoll.setTags(pollDto.getTags());
        newPoll.setCreatedDateTime(pollDto.getCreatedDateTime());
        newPoll.setUpdatedDateTime(pollDto.getCreatedDateTime());


        //save post and topic
        Post post = postRepository.save(newPost);
        Poll poll = pollRepository.save(newPoll);

        //update post and topic with ids
        post.setPostTypeId(poll.getId());
        poll.setPostId(post.getId());

        Post postUpdated = postRepository.save(post);
        Poll pollUpdated = pollRepository.save(poll);

        //return created post
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, pollUpdated, authorOptional.get());

        return Optional.ofNullable(postDetailsDto);
    }

    @Override
    public Optional<Poll> getPollById(String pollId) {
        Optional<Poll> pollOptional = pollRepository.findById(pollId);

        if (pollOptional.isPresent()) {
            Poll poll = pollOptional.get();

            return Optional.of(poll);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Poll>> getAllPolls() {
        List<Poll> polls = pollRepository.findAll();

        if (polls.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(polls);
    }
}
