package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Dto.PostDto.TopicCreateDto;
import com.example.muzfi.Dto.PostDto.TopicUpdateDto;
import com.example.muzfi.Model.Post.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    Optional<PostDetailsDto> createTopic(TopicCreateDto topicDto);

    Optional<Topic> getTopicById(String topicId);

    Optional<List<Topic>> getTopicsByUserId(String userId);

    Optional<PostDetailsDto> updateTopic(TopicUpdateDto updateDto);

    Optional<List<Topic>> getAllTopics();
}
