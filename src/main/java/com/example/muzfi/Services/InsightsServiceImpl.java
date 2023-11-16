package com.example.muzfi.Services;

import com.example.muzfi.Model.Insights;
import com.example.muzfi.Repository.InsightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsightsServiceImpl implements InsightsService{
    private final InsightsRepository insightsRepository;

    public List<Insights> getAllInsights(){
        return insightsRepository.findAll();
    }
}
