package com.russai.russai.model;

import lombok.Data;
import java.util.List;

// This is what the engine sends back — the ordered spirit plus upsell suggestions
@Data
public class RecommendationResponse {

    private String orderedSpirit;
    private List<SpiritMatch> recommendations;

    // Each individual recommendation — what to suggest and why
    @Data
    public static class SpiritMatch {
        private String name;
        private String distillery;
        private String flavorTags;
        private double pricePour;
        private double proof;
        private String reason;
    }
}