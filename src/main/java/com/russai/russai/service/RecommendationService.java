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

            // proof tier matching — keep upsells in a similar intensity band
            // so a 90 proof sipper isn't pushed to a 130 proof barrel monster
            if (candidate.getProof() != null && ordered.getProof() != null) {
                String orderedTier = proofTier(ordered.getProof().doubleValue());
                String candidateTier = proofTier(candidate.getProof().doubleValue());
                if (orderedTier.equals(candidateTier)) {
                    score += 2;
                }
            }

            // price ladder — reward a sensible upsell step (1.2x to 2x the price)
            // too cheap = no upsell value, too expensive = not a realistic upgrade
            if (candidate.getPricePour() != null && ordered.getPricePour() != null) {
                double orderedPrice = ordered.getPricePour().doubleValue();
                double candidatePrice = candidate.getPricePour().doubleValue();
                if (orderedPrice > 0) {
                    double ratio = candidatePrice / orderedPrice;
                    if (ratio >= 1.2 && ratio <= 2.0) {
                        score += 3; // ideal upsell window
                    } else if (ratio > 1.0 && ratio < 1.2) {
                        score += 1; // slight step up, still works
                    }
                    // ratio <= 1.0 (cheaper) or > 2.0 (too pricey) gets no bonus
                }
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

    // buckets a proof number into an intensity tier
    private String proofTier(double proof) {
        if (proof < 95) return "easy";
        if (proof < 110) return "mid";
        if (proof < 120) return "high";
        return "barrel";
    }

    // builds a human readable reason string for the bartender
    private String buildReason(Spirit ordered, Spirit candidate, int score) {
        List<String> reasons = new ArrayList<>();

        // distillery connection
        if (candidate.getDistillery() != null &&
                candidate.getDistillery().equalsIgnoreCase(ordered.getDistillery())) {
            reasons.add("same distillery as " + ordered.getName());
        }

        // grain recipe connection
        if (candidate.getMashBill() != null &&
                candidate.getMashBill().equalsIgnoreCase(ordered.getMashBill())) {
            reasons.add("same grain recipe");
        }

        // proof relationship — describe the intensity step
        if (candidate.getProof() != null && ordered.getProof() != null) {
            double orderedProof = ordered.getProof().doubleValue();
            double candidateProof = candidate.getProof().doubleValue();
            if (candidateProof > orderedProof + 5) {
                reasons.add("bigger, bolder pour at " + candidateProof + " proof");
            } else if (Math.abs(candidateProof - orderedProof) <= 5) {
                reasons.add("similar easy-drinking strength");
            }
        }

        // age adds complexity
        if (candidate.getAgeStatement() != null) {
            reasons.add("aged " + candidate.getAgeStatement() + " years for more depth");
        }

        // price step — frame the upsell
        if (candidate.getPricePour() != null && ordered.getPricePour() != null) {
            double diff = candidate.getPricePour().doubleValue()
                    - ordered.getPricePour().doubleValue();
            if (diff > 0) {
                reasons.add(String.format("only $%.0f more", diff));
            }
        }

        if (reasons.isEmpty()) {
            reasons.add("similar flavor profile");
        }

        return String.join(", ", reasons);
    }
}