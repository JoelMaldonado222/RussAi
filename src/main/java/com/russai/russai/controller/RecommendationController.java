package com.russai.russai.controller;

import com.russai.russai.model.RecommendationRequest;
import com.russai.russai.model.RecommendationResponse;
import com.russai.russai.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// This controller handles the recommendation engine endpoint
@RestController
@RequestMapping("/api/recommend")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    // POST /api/recommend — accepts a spirit name, returns upsell suggestions
    @PostMapping
    public ResponseEntity<RecommendationResponse> recommend(
            @RequestBody RecommendationRequest request) {
        RecommendationResponse response = recommendationService.recommend(request);
        return ResponseEntity.ok(response);
    }
}