package com.russai.russai.controller;

import com.russai.russai.model.RecommendationRequest;
import com.russai.russai.model.RecommendationResponse;
import com.russai.russai.model.ScriptResponse;
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

    // POST /api/recommend/script — same scoring as above, but the top two
    // picks also come back with a real, ready-to-say bartender line. This
    // is the only endpoint in the app that calls out to an LLM for text
    // generation, kept separate from the plain endpoint above on purpose,
    // so the fast, free, deterministic scoring path stays untouched and
    // this opt-in one carries the (small) OpenAI cost only when it's
    // actually wanted.
    @PostMapping("/script")
    public ResponseEntity<ScriptResponse> recommendWithScript(
            @RequestBody RecommendationRequest request) {
        ScriptResponse response = recommendationService.recommendWithScript(request);
        return ResponseEntity.ok(response);
    }
}