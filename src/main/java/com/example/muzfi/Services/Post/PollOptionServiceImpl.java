package com.example.muzfi.Services.Post;

import com.example.muzfi.Model.Post.PollOption;
import com.example.muzfi.Repository.PollOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PollOptionServiceImpl implements PollOptionService {

    private final PollOptionRepository pollOptionRepository;

    @Autowired
    public PollOptionServiceImpl(PollOptionRepository pollOptionRepository) {
        this.pollOptionRepository = pollOptionRepository;
    }

    @Override
    public Optional<List<PollOption>> getPollOptionsByIds(List<String> pollIds) {
        List<PollOption> pollOptions = pollOptionRepository.findAllById(pollIds);

        if (pollOptions.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(pollOptions);
        }
    }
}
