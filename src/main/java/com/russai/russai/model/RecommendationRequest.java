package com.russai.russai.model;

import lombok.Data;

// This is what the bartender UI sends to the engine
// Just the name of the spirit that was ordered
@Data
public class RecommendationRequest {
    private String spiritName;
}