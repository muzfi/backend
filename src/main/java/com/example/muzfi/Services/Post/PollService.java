package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PollCreateDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Model.Post.Poll;

import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<PostDetailsDto> createPoll(PollCreateDto pollDto);

    Optional<Poll> getPollById(String pollId);

    Optional<List<Poll>> getAllPolls();
}
