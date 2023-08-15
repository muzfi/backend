package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Dto.PostDto.TopicCreateDto;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.Post.Topic;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Repository.TopicRepository;
import com.example.muzfi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService{

    private final TopicRepository topicRepository;

    private final PostRepository postRepository;

    private final UserService userService;

    private final PostManager postManager;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, PostRepository postRepository, UserService userService, PostManager postManager) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.postManager = postManager;
    }

    @Override
    public Optional<PostDetailsDto> createTopic(TopicCreateDto topicDto) {
        //create post
        Post newPost = new Post();
        newPost.setAuthorId(topicDto.getAuthorId());
        newPost.setPostType(PostType.PROD_TOPIC);
        newPost.setIsEnablePostReplyNotification(topicDto.getIsEnablePostReplyNotification());
        newPost.setCreatedDateTime(topicDto.getCreatedDateTime());
        newPost.setUpdatedDateTime(topicDto.getCreatedDateTime());
        newPost.setIsDraft(topicDto.getIsDraft());

        //create topic
        Topic newTopic = new Topic();
        newTopic.setAuthorId(topicDto.getAuthorId());
        newTopic.setTitle(topicDto.getTitle());
        newTopic.setTopicType(topicDto.getTopicType());
        newTopic.setText(topicDto.getDescription());
        newTopic.setTopicCategory(topicDto.getTopicCategory());
        newTopic.setTags(topicDto.getTags());
        newTopic.setCreatedDateTime(topicDto.getCreatedDateTime());
        newTopic.setUpdatedDateTime(topicDto.getCreatedDateTime());


        //save post and topic
        Post post = postRepository.save(newPost);
        Topic topic = topicRepository.save(newTopic);

        //update post and topic with ids
        post.setPostTypeId(topic.getId());
        topic.setPostId(post.getId());

        Post postUpdated = postRepository.save(post);
        Topic topicUpdated = topicRepository.save(topic);

        //return created post
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, topicUpdated, authorOptional.get());

        return Optional.ofNullable(postDetailsDto);
    }

    @Override
    public Optional<Topic> getTopicById(String topicId) {
        Optional<Topic> topicOptional = topicRepository.findById(topicId);

        if (topicOptional.isPresent()) {
            Topic topic = topicOptional.get();

            return Optional.of(topic);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Topic>> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        if (topics.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(topics);
    }
}
