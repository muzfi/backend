package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PollCreateDto;
import com.example.muzfi.Dto.PostDto.PollDetailsDto;
import com.example.muzfi.Dto.PostDto.PollUpdateDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;

import java.util.List;
import java.util.Optional;

public interface PollService {
    Optional<PostDetailsDto> createPoll(PollCreateDto pollDto);

    Optional<PollDetailsDto> getPollById(String pollId);

    Optional<List<PollDetailsDto>> getPollsByUserId(String userId);

    Optional<List<PollDetailsDto>> getAllPolls();

    Optional<String> vote(String pollOptionId, String userId);

    Optional<String> removeVote(String pollOptionId, String userId);

    Optional<PostDetailsDto> updatePoll(PollUpdateDto updateDto);

    Optional<PostDetailsDto> addPollOption(String optionText, String pollId);

    Optional<PostDetailsDto> removePollOption(String optionId, String pollId);
}
