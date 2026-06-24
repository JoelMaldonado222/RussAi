package com.russai.russai.service;

import com.russai.russai.model.RecommendationRequest;
import com.russai.russai.model.RecommendationResponse;
import com.russai.russai.model.RecommendationResponse.SpiritMatch;
import com.russai.russai.model.ScriptResponse;
import com.russai.russai.model.Spirit;
import com.russai.russai.repository.SpiritRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final SpiritRepository spiritRepository;
    private final ScriptGenerationService scriptGenerationService;

    public RecommendationService(SpiritRepository spiritRepository,
                                  ScriptGenerationService scriptGenerationService) {
        this.spiritRepository = spiritRepository;
        this.scriptGenerationService = scriptGenerationService;
    }

    // ============================================================
    // SCORING POINT HIERARCHY — locked in deliberately, not arbitrary.
    // ------------------------------------------------------------
    // Ranked from the strongest real reason a guest says yes to the
    // next pour, down to the weakest supporting nudge:
    //   1. Flavor match (the embedding)  — up to 6 points, tapered by rank
    //   2. Same distillery                — 2 points
    //   3. Same mash bill (grain family)  — 2 points
    //   4. Proof tier compatibility       — 2 points (a guardrail, not a pitch)
    //   5. Exact flavor tag overlap       — 1 point per matching tag
    //   6. Has an age statement           — 1 point
    //   7. Same category                  — 1 point
    //
    // Distillery and mash bill were originally 4 and 3. Trimmed to 2 and 2
    // after real testing showed a same-distillery sibling started with a
    // built-in 7-point floor before flavor was even judged, which made it
    // nearly impossible for a genuine cross-distillery flavor twin to win,
    // even when it was a closer vector match. At 2 and 2, lineage is still
    // a real, deserved edge, just not an automatic one.
    //
    // Price is NOT in this point stack — it's two gates, not a score:
    //   - Cheaper than the order  -> comparisonOnly = true  (a downsell,
    //     shown only as a talking point, never a real upsell)
    //   - More than ASPIRATIONAL_PRICE_RATIO_CEILING times pricier ->
    //     aspirational = true (too big a jump to be a normal next-pour
    //     suggestion; shown only as a "worth mentioning" aside, not a
    //     practical upsell)
    // Anything in between those two gates is a genuine, normal upsell.
    // ============================================================
    private static final int MAX_VECTOR_BONUS = 6;
    private static final int DISTILLERY_MATCH_POINTS = 2;
    private static final int MASH_BILL_MATCH_POINTS = 2;
    private static final int PROOF_TIER_MATCH_POINTS = 2;
    private static final int AGE_STATEMENT_POINTS = 1;
    private static final int SAME_CATEGORY_POINTS = 1;

    // Anything priced more than this many times the ordered spirit is too
    // big a leap to present as a normal upsell (e.g. $1,000 against an $18
    // order). It still gets flagged and demoted rather than thrown away,
    // since a real bartender would still mention a trophy bottle sometimes,
    // just not in the same breath as a realistic next pour.
    private static final double ASPIRATIONAL_PRICE_RATIO_CEILING = 3.0;

    // How many of the nearest flavor-vector neighbors to pull in and score.
    // Generous on purpose — most of these won't end up scoring high enough
    // to matter, but we don't want to miss a genuine cross-category match
    // (like a Rye sibling) just because it sits a little further down the
    // raw nearest-neighbor list.
    private static final int VECTOR_NEIGHBORS_TO_CONSIDER = 15;

    // How many recommendations to actually return. Was 3, bumped to 5 —
    // more room for a real upsell, a cross-distillery surprise, and an
    // occasional comparison or aspirational mention to all coexist without
    // crowding each other out.
    private static final int MAX_RECOMMENDATIONS = 5;

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

        // Step 1.5 — pull the flavor-vector neighbors for the ordered spirit,
        // if it has an embedding yet. This is the strongest signal in the
        // whole hierarchy above: it reads the spirit's whole flavor profile
        // instead of matching on exact tag text, and it's what lets a
        // genuinely close cross-category match (a Rye sibling of a Bourbon)
        // compete at all. Rank matters more than raw distance here, so each
        // neighbor gets a bonus based on how close it placed: nearest gets
        // the full MAX_VECTOR_BONUS, it tapers off, and anything outside the
        // top results gets nothing.
        Map<UUID, Integer> vectorBonus = new HashMap<>();
        String queryVector = spiritRepository.getEmbeddingAsString(ordered.getSpiritId());
        if (queryVector != null) {
            List<Spirit> nearestByFlavor = spiritRepository.findSimilarByEmbedding(
                    ordered.getSpiritId(), queryVector, VECTOR_NEIGHBORS_TO_CONSIDER);
            for (int i = 0; i < nearestByFlavor.size(); i++) {
                int bonus = Math.max(MAX_VECTOR_BONUS - i, 0);
                if (bonus > 0) {
                    vectorBonus.put(nearestByFlavor.get(i).getSpiritId(), bonus);
                }
            }
        }
        // If the ordered spirit has no embedding yet (backfill hasn't run for
        // it), vectorBonus just stays empty and scoring falls back to the
        // remaining rules below.

        // Step 2 — score every other spirit using the hierarchy above
        List<ScoredCandidate> scored = new ArrayList<>();

        for (Spirit candidate : allSpirits) {

            // don't recommend the same spirit that was ordered
            if (candidate.getSpiritId().equals(ordered.getSpiritId())) continue;

            // staying in the same category is the weakest positive signal in
            // the hierarchy, and it is no longer a hard requirement — the
            // flavor vector is allowed to pull in a genuinely close
            // cross-category match (e.g. a Rye sibling of a Bourbon), the
            // same way a real bartender would say "I know this is a Rye,
            // but it tastes close enough to try."
            boolean sameCategory = candidate.getCategory() != null
                    && candidate.getCategory().equalsIgnoreCase(ordered.getCategory());

            int score = 0;

            if (sameCategory) {
                score += SAME_CATEGORY_POINTS;
            }

            // #2 in the hierarchy — same distillery = strongest non-flavor
            // signal, an instant trust pitch ("same hands that made what
            // you're already drinking")
            if (candidate.getDistillery() != null && ordered.getDistillery() != null
                    && candidate.getDistillery().equalsIgnoreCase(ordered.getDistillery())) {
                score += DISTILLERY_MATCH_POINTS;
            }

            // #3 — same mash bill = same grain DNA, the technical "why" behind
            // the flavor (high rye drinks spicier, wheated drinks softer)
            if (candidate.getMashBill() != null && ordered.getMashBill() != null
                    && candidate.getMashBill().equalsIgnoreCase(ordered.getMashBill())) {
                score += MASH_BILL_MATCH_POINTS;
            }

            // #5 — exact flavor tag overlap. Now a minor supporting signal
            // underneath the embedding rather than the main event, since a
            // single mismatched word here (e.g. "vanilla" vs "rich vanilla")
            // shouldn't be trusted the way Lonnie warned against.
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

            // #4 — proof tier compatibility. A guardrail, not a sales pitch:
            // nobody picks a pour because of its proof number, but jumping
            // someone from a gentle 90 proof to a 140 proof barrel monster
            // is how you lose the sale, not win it.
            if (candidate.getProof() != null && ordered.getProof() != null) {
                String orderedTier = proofTier(ordered.getProof().doubleValue());
                String candidateTier = proofTier(candidate.getProof().doubleValue());
                if (orderedTier.equals(candidateTier)) {
                    score += PROOF_TIER_MATCH_POINTS;
                }
            }

            // #6 — having an age statement is a minor value-add talking point
            // ("aged twelve years, noticeably smoother"), not a real driver
            if (candidate.getAgeStatement() != null) {
                score += AGE_STATEMENT_POINTS;
            }

            // #1 — the flavor-vector signal, applied last so it's easy to see
            // it's additive on top of everything above, not a replacement
            score += vectorBonus.getOrDefault(candidate.getSpiritId(), 0);

            // Price — two gates, never a stacked point. Below the order's
            // price, this is a downsell, flagged comparisonOnly. More than
            // ASPIRATIONAL_PRICE_RATIO_CEILING times pricier, this is too
            // big a leap to be a normal next-pour suggestion, flagged
            // aspirational instead. Either flag demotes the candidate below
            // genuine, realistically-priced upsells in sorting — neither
            // one ever earns or loses score points here.
            boolean isComparisonOnly = false;
            boolean isAspirational = false;
            if (candidate.getPricePour() != null && ordered.getPricePour() != null) {
                double orderedPrice = ordered.getPricePour().doubleValue();
                double candidatePrice = candidate.getPricePour().doubleValue();
                if (orderedPrice > 0) {
                    double ratio = candidatePrice / orderedPrice;
                    if (ratio < 1.0) {
                        isComparisonOnly = true;
                    } else if (ratio > ASPIRATIONAL_PRICE_RATIO_CEILING) {
                        isAspirational = true;
                    }
                    // ratio between 1.0 and the ceiling, inclusive, is a
                    // normal upsell — no flag, no bonus, just eligible
                }
            }

            // only include spirits with at least some similarity
            if (score > 0) {
                scored.add(new ScoredCandidate(candidate, score, sameCategory, isComparisonOnly, isAspirational));
            }
        }

        // Step 3 — rank genuine, realistically-priced upsells first, then
        // aspirational trophy mentions, then comparison-only downsells
        // last, and within each of those three groups, best score first.
        // This means a $1,000 bottle can still get mentioned and a $12
        // bottle can still get name-dropped for comparison, but neither
        // one will ever bump a real, normal upsell out of the way.
        scored.sort(Comparator
                .comparingInt((ScoredCandidate sc) -> sc.isComparisonOnly() ? 2 : sc.isAspirational() ? 1 : 0)
                .thenComparing(Comparator.comparingInt(ScoredCandidate::score).reversed()));

        List<SpiritMatch> recommendations = scored.stream()
                .limit(MAX_RECOMMENDATIONS)
                .map(sc -> {
                    Spirit s = sc.spirit();
                    SpiritMatch match = new SpiritMatch();
                    match.setName(s.getName());
                    match.setDistillery(s.getDistillery());
                    match.setFlavorTags(s.getFlavorTags());
                    match.setMashBill(s.getMashBill());
                    match.setPricePour(s.getPricePour().doubleValue());
                    match.setProof(s.getProof() != null ? s.getProof().doubleValue() : 0);
                    match.setComparisonOnly(sc.isComparisonOnly());
                    match.setAspirational(sc.isAspirational());
                    match.setReason(buildReason(ordered, sc));
                    return match;
                })
                .collect(Collectors.toList());

        // Step 4 — build and return the response
        RecommendationResponse response = new RecommendationResponse();
        response.setOrderedSpirit(ordered.getName());
        response.setOrderedSpiritFlavorTags(ordered.getFlavorTags());
        response.setOrderedSpiritProof(ordered.getProof() != null ? ordered.getProof().doubleValue() : 0);
        response.setOrderedSpiritMashBill(ordered.getMashBill());
        response.setRecommendations(recommendations);
        return response;
    }

    // How many of the top recommendations get a real, generated bartender
    // line. The rest are passed through as plain data — a bartender will
    // naturally work those into the conversation on their own if it goes
    // that far, there's no need to script every option, just the strongest
    // one or two.
    private static final int SCRIPTED_PICKS = 2;

    // Same scoring as recommend() above, but the top picks also get a
    // real, ready-to-say line generated from the facts already computed —
    // see ScriptGenerationService for how that line gets written.
    public ScriptResponse recommendWithScript(RecommendationRequest request) {

        // Reuse the exact same, already-tested scoring logic above rather
        // than duplicating it — this method only adds the script step on
        // top of whatever recommend() already decided.
        RecommendationResponse scored = recommend(request);

        ScriptResponse response = new ScriptResponse();
        response.setOrderedSpirit(scored.getOrderedSpirit());
        response.setOrderedSpiritFlavorTags(scored.getOrderedSpiritFlavorTags());
        response.setOrderedSpiritProof(scored.getOrderedSpiritProof());
        response.setOrderedSpiritMashBill(scored.getOrderedSpiritMashBill());

        List<SpiritMatch> all = scored.getRecommendations();
        if (all.isEmpty()) {
            response.setTopPicks(new ArrayList<>());
            response.setOtherOptions(new ArrayList<>());
            return response;
        }

        // A second, separate lookup of the ordered spirit by name — kept
        // deliberately apart from the lookup inside recommend() above, so
        // that proven, already-tested method is never touched just to
        // expose this one extra piece of data the prompt needs (its own
        // flavor, price, and proof, not just its name).
        Spirit ordered = spiritRepository.findAll().stream()
                .filter(s -> s.getName().equalsIgnoreCase(request.getSpiritName()))
                .findFirst()
                .orElse(null);

        int scriptedCount = Math.min(SCRIPTED_PICKS, all.size());
        List<SpiritMatch> topPicks = all.subList(0, scriptedCount);
        List<SpiritMatch> otherOptions = all.size() > scriptedCount
                ? new ArrayList<>(all.subList(scriptedCount, all.size()))
                : new ArrayList<>();

        List<String> scripts = scriptGenerationService.generateScripts(ordered, topPicks);

        List<ScriptResponse.GeneratedScript> generatedPicks = new ArrayList<>();
        for (int i = 0; i < topPicks.size(); i++) {
            SpiritMatch m = topPicks.get(i);
            ScriptResponse.GeneratedScript g = new ScriptResponse.GeneratedScript();
            g.setName(m.getName());
            g.setDistillery(m.getDistillery());
            g.setFlavorTags(m.getFlavorTags());
            g.setMashBill(m.getMashBill());
            g.setPricePour(m.getPricePour());
            g.setProof(m.getProof());
            g.setComparisonOnly(m.isComparisonOnly());
            g.setAspirational(m.isAspirational());
            // Falls back to the existing plain reason text if, for any
            // reason, fewer scripts came back than picks were sent —
            // never leaves a pick with no line at all.
            g.setScript(i < scripts.size() ? scripts.get(i) : m.getReason());
            generatedPicks.add(g);
        }

        response.setTopPicks(generatedPicks);
        response.setOtherOptions(otherOptions);
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
    private String buildReason(Spirit ordered, ScoredCandidate sc) {
        Spirit candidate = sc.spirit();
        List<String> reasons = new ArrayList<>();

        // cross-category acknowledgment comes first, the same way a real
        // bartender would lead: "I know this is a Rye, but..."
        if (!sc.sameCategory()) {
            reasons.add("different style (" + candidate.getCategory()
                    + "), but the notes line up close enough to be worth a try");
        }

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

        // price step — frame as a normal upsell, an aspirational mention if
        // the jump is too big to be realistic, or a comparison talking
        // point if it's actually cheaper than what was ordered
        if (candidate.getPricePour() != null && ordered.getPricePour() != null) {
            double diff = candidate.getPricePour().doubleValue()
                    - ordered.getPricePour().doubleValue();
            if (sc.isComparisonOnly()) {
                reasons.add("lighter pour at a lower price — good talking point, not an upsell");
            } else if (sc.isAspirational()) {
                reasons.add(String.format(
                        "a big step up at $%.0f more — worth mentioning, not a realistic next pour", diff));
            } else if (diff > 0) {
                reasons.add(String.format("only $%.0f more", diff));
            }
        }

        if (reasons.isEmpty()) {
            reasons.add("similar flavor profile");
        }

        return String.join(", ", reasons);
    }

    // Small internal holder for a candidate's score plus the context
    // buildReason and the sort both need (same category? comparison-only?
    // aspirational?). A record fits well here since this is just an
    // immutable bundle of values passed around inside this one class,
    // nothing persisted or sent over the API — Spring Data and Jackson
    // never see it.
    private record ScoredCandidate(Spirit spirit, int score, boolean sameCategory,
                                    boolean isComparisonOnly, boolean isAspirational) {}
}