package com.russai.russai.service;

import com.russai.russai.model.RecommendationRequest;
import com.russai.russai.model.RecommendationResponse;
import com.russai.russai.model.RecommendationResponse.SpiritMatch;
import com.russai.russai.model.Spirit;
import com.russai.russai.repository.SpiritRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final SpiritRepository spiritRepository;

    public RecommendationService(SpiritRepository spiritRepository) {
        this.spiritRepository = spiritRepository;
    }

    public RecommendationResponse recommend(RecommendationRequest request) {

        // Step 1 — find the spirit the bartender ordered
        List<Spirit> allSpirits = spiritRepository.findAll();

        Spirit ordered = allSpirits.stream()
                .filter(s -> s.getName().equalsIgnoreCase(request.getSpiritName()))
                .findFirst()
                .orElse(null);

        // if the spirit doesn't exist in our database return empty response
        if (ordered == null) {
            RecommendationResponse response = new RecommendationResponse();
            response.setOrderedSpirit(request.getSpiritName());
            response.setRecommendations(new ArrayList<>());
            return response;
        }

        // Step 2 — score every other spirit for similarity
        Map<Spirit, Integer> scores = new HashMap<>();

        for (Spirit candidate : allSpirits) {

            // don't recommend the same spirit that was ordered
            if (candidate.getSpiritId().equals(ordered.getSpiritId())) continue;

            // only recommend spirits in the same category
            if (!candidate.getCategory().equalsIgnoreCase(ordered.getCategory())) continue;

            int score = 0;

            // same distillery = strongest signal — same house style
            if (candidate.getDistillery() != null && ordered.getDistillery() != null) {
                if (candidate.getDistillery().equalsIgnoreCase(ordered.getDistillery())) {
                    score += 3;
                }
            }

            // same mash bill = same grain DNA
            if (candidate.getMashBill() != null && ordered.getMashBill() != null) {
                if (candidate.getMashBill().equalsIgnoreCase(ordered.getMashBill())) {
                    score += 2;
                }
            }

            // flavor tag overlap — count how many tags match
            if (candidate.getFlavorTags() != null && ordered.getFlavorTags() != null) {
                List<String> orderedTags = Arrays.asList(
                        ordered.getFlavorTags().toLowerCase().split(",\\s*"));
                List<String> candidateTags = Arrays.asList(
                        candidate.getFlavorTags().toLowerCase().split(",\\s*"));

                long matchingTags = candidateTags.stream()
                        .filter(orderedTags::contains)
                        .count();

                score += (int) matchingTags;
            }

            // only include spirits with at least some similarity
            if (score > 0) {
                scores.put(candidate, score);
            }
        }

        // Step 3 — sort by score descending, take top 3
        List<SpiritMatch> recommendations = scores.entrySet().stream()
                .sorted(Map.Entry.<Spirit, Integer>comparingByValue().reversed())
                .limit(3)
                .map(entry -> {
                    Spirit s = entry.getKey();
                    SpiritMatch match = new SpiritMatch();
                    match.setName(s.getName());
                    match.setDistillery(s.getDistillery());
                    match.setFlavorTags(s.getFlavorTags());
                    match.setPricePour(s.getPricePour().doubleValue());
                    match.setProof(s.getProof() != null ? s.getProof().doubleValue() : 0);
                    match.setReason(buildReason(ordered, s, entry.getValue()));
                    return match;
                })
                .collect(Collectors.toList());

        // Step 4 — build and return the response
        RecommendationResponse response = new RecommendationResponse();
        response.setOrderedSpirit(ordered.getName());
        response.setRecommendations(recommendations);
        return response;
    }

    // builds a human readable reason string for the bartender
    private String buildReason(Spirit ordered, Spirit candidate, int score) {
        List<String> reasons = new ArrayList<>();

        if (candidate.getDistillery() != null &&
                candidate.getDistillery().equalsIgnoreCase(ordered.getDistillery())) {
            reasons.add("same distillery as " + ordered.getName());
        }

        if (candidate.getMashBill() != null &&
                candidate.getMashBill().equalsIgnoreCase(ordered.getMashBill())) {
            reasons.add("same grain recipe");
        }

        if (candidate.getAgeStatement() != null) {
            reasons.add("aged " + candidate.getAgeStatement() + " years");
        }

        if (reasons.isEmpty()) {
            reasons.add("similar flavor profile");
        }

        return String.join(", ", reasons);
    }
}