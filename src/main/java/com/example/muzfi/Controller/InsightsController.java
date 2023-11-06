package com.example.muzfi.Controller;

import com.example.muzfi.Model.Insights;
import com.example.muzfi.Services.InsightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/insights")
@RequiredArgsConstructor
public class InsightsController {
    private final InsightsService insightsService;

    @GetMapping
    public List<Insights> getAllInsights(){
        return insightsService.getAllInsights();
    }
}
