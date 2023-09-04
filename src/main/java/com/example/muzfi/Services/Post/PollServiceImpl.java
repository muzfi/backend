package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.*;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Poll;
import com.example.muzfi.Model.Post.PollOption;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Repository.PollOptionRepository;
import com.example.muzfi.Repository.PollRepository;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PollServiceImpl implements PollService {

    private final PollRepository pollRepository;

    private final PostRepository postRepository;

    private final PollOptionRepository pollOptionRepository;

    private final UserService userService;

    private final PostManager postManager;

    @Autowired
    public PollServiceImpl(PollRepository pollRepository, PostRepository postRepository, PollOptionRepository pollOptionRepository, UserService userService, PostManager postManager) {
        this.pollRepository = pollRepository;
        this.postRepository = postRepository;
        this.pollOptionRepository = pollOptionRepository;
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
        List<String> pollOptionIds = new ArrayList<>();
        for (String optionText : pollDto.getOptions()) {
            PollOption option = new PollOption();
            option.setOptionText(optionText);

            PollOption optionCreated = pollOptionRepository.save(option);
            pollOptionIds.add(optionCreated.getId());
        }

        //create topic
        Poll newPoll = new Poll();
        newPoll.setAuthorId(pollDto.getAuthorId());
        newPoll.setTitle(pollDto.getTitle());
        newPoll.setText(pollDto.getDescription());
        newPoll.setPollOptionIds(pollOptionIds);
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
        PollDetailsDto pollDetailsDto = postManager.getPollDetailsDto(pollUpdated);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, pollDetailsDto, authorOptional.get());

        return Optional.ofNullable(postDetailsDto);
    }

    @Override
    public Optional<PollDetailsDto> getPollById(String pollId) {
        Optional<Poll> pollOptional = pollRepository.findById(pollId);

        if (pollOptional.isPresent()) {
            Poll poll = pollOptional.get();
            PollDetailsDto pollDetailsDto = postManager.getPollDetailsDto(poll);

            return Optional.of(pollDetailsDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<PollDetailsDto>> getPollsByUserId(String userId) {
        List<Poll> polls = pollRepository.findAllByAuthorId(userId);

        if (!polls.isEmpty()) {
            List<PollDetailsDto> pollDtoList = new ArrayList<>();

            for (Poll poll : polls) {
                PollDetailsDto pollDetailsDto = postManager.getPollDetailsDto(poll);
                pollDtoList.add(pollDetailsDto);
            }

            return Optional.of(pollDtoList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<PollDetailsDto>> getAllPolls() {
        List<Poll> polls = pollRepository.findAll();

        if (polls.isEmpty()) {
            return Optional.empty();
        }

        List<PollDetailsDto> pollDetailsDtoList = new ArrayList<>();
        for (Poll poll : polls) {
            PollDetailsDto pollDetailsDto = postManager.getPollDetailsDto(poll);
            pollDetailsDtoList.add(pollDetailsDto);
        }

        return Optional.of(pollDetailsDtoList);
    }

    @Override
    public Optional<String> vote(String pollOptionId, String userId) {
        Optional<PollOption> pollOptionOpt = pollOptionRepository.findById(pollOptionId);

        if (pollOptionOpt.isPresent()) {
            PollOption pollOption = pollOptionOpt.get();

            Set<String> votedUsers = new HashSet<>();
            if (pollOption.getVotedUserIds() != null) {
                votedUsers = pollOption.getVotedUserIds();
            }
            votedUsers.add(userId);

            pollOption.setVotedUserIds(votedUsers);

            pollOptionRepository.save(pollOption);

            return Optional.of("Voted");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> removeVote(String pollOptionId, String userId) {
        Optional<PollOption> pollOptionOpt = pollOptionRepository.findById(pollOptionId);

        if (pollOptionOpt.isPresent()) {
            PollOption pollOption = pollOptionOpt.get();

            Set<String> votedUsers = new HashSet<>();
            if (pollOption.getVotedUserIds() != null) {
                votedUsers = pollOption.getVotedUserIds();
            }
            votedUsers.remove(userId);

            pollOption.setVotedUserIds(votedUsers);

            pollOptionRepository.save(pollOption);

            return Optional.of("Vote removed");
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PostDetailsDto> updatePoll(PollUpdateDto updateDto) {
        Optional<Poll> pollOpt = pollRepository.findById(updateDto.getId());
        Optional<Post> postOpt = postRepository.findById(updateDto.getPostId());

        if (pollOpt.isEmpty() || postOpt.isEmpty()) {
            return Optional.empty();
        }

        Poll poll = pollOpt.get();
        Post post = postOpt.get();

        //update poll options
        if (!updateDto.getPollOptions().isEmpty()) {
            for (PollOption option : updateDto.getPollOptions()) {
                PollOption newOption = new PollOption();
                newOption.setOptionText(option.getOptionText());
                newOption.setId(option.getId());

                pollOptionRepository.save(newOption);
            }
        }

        poll.setTitle(updateDto.getTitle());
        poll.setText(updateDto.getText());
        poll.setPollDeadline(updateDto.getPollDeadline());
        poll.setTags(updateDto.getTags());
        poll.setPostCategories(updateDto.getPostCategories());
        poll.setUpdatedDateTime(LocalDateTime.now());

        post.setUpdatedDateTime(LocalDateTime.now());

        Post postUpdated = postRepository.save(post);
        Poll pollUpdated = pollRepository.save(poll);

        PollDetailsDto pollDetailsDto = postManager.getPollDetailsDto(pollUpdated);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, pollDetailsDto, authorOptional.get());

        return Optional.of(postDetailsDto);
    }

    //Add new poll option for existing poll
    @Override
    public Optional<PostDetailsDto> addPollOption(String optionText, String pollId) {
        Optional<Poll> pollOpt = pollRepository.findById(pollId);

        if (pollOpt.isPresent()) {
            Poll poll = pollOpt.get();

            //creat poll options
            List<String> pollOptionIds = new ArrayList<>();
            if (!poll.getPollOptionIds().isEmpty()) {
                pollOptionIds = poll.getPollOptionIds();
            }

            PollOption newOption = new PollOption();
            newOption.setOptionText(optionText);
            PollOption optionCreated = pollOptionRepository.save(newOption);

            pollOptionIds.add(optionCreated.getId());

            poll.setPollOptionIds(pollOptionIds);
            poll.setUpdatedDateTime(LocalDateTime.now());
            Poll pollUpdated = pollRepository.save(poll);

            Optional<Post> postOpt = postRepository.findById(poll.getPostId());
            Post post = postOpt.get();
            post.setUpdatedDateTime(LocalDateTime.now());
            Post postUpdated = postRepository.save(post);

            PollDetailsDto pollDetailsDto = postManager.getPollDetailsDto(pollUpdated);
            Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
            PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, pollDetailsDto, authorOptional.get());

            return Optional.ofNullable(postDetailsDto);
        }

        return Optional.empty();
    }

    //Remove poll option from existing poll
    @Override
    public Optional<PostDetailsDto> removePollOption(String optionId, String pollId) {
        Optional<Poll> pollOpt = pollRepository.findById(pollId);

        if (pollOpt.isPresent()) {
            Poll poll = pollOpt.get();

            if (poll.getPollOptionIds().isEmpty()) {
                return Optional.empty();
            }
            pollOptionRepository.deleteById(optionId);

            List<String> pollOptionIds = poll.getPollOptionIds();

            pollOptionIds.remove(optionId);

            poll.setPollOptionIds(pollOptionIds);
            poll.setUpdatedDateTime(LocalDateTime.now());
            Poll pollUpdated = pollRepository.save(poll);

            Optional<Post> postOpt = postRepository.findById(poll.getPostId());
            Post post = postOpt.get();
            post.setUpdatedDateTime(LocalDateTime.now());
            Post postUpdated = postRepository.save(post);

            PollDetailsDto pollDetailsDto = postManager.getPollDetailsDto(pollUpdated);
            Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
            PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, pollDetailsDto, authorOptional.get());

            return Optional.ofNullable(postDetailsDto);
        }

        return Optional.empty();
    }
}
