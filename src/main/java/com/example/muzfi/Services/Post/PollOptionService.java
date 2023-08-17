package com.example.muzfi.Services.Post;

import com.example.muzfi.Model.Post.PollOption;

import java.util.List;
import java.util.Optional;

public interface PollOptionService {
    Optional<List<PollOption>> getPollOptionsByIds(List<String> pollIds);
}
