package com.russai.russai.model;

import lombok.Data;
import java.util.List;

// What /api/recommend/script returns. The top two picks each get a real,
// ready-to-say bartender line generated from the facts the scoring engine
// already computed. The remaining picks are passed through as plain data,
// no generation, since a bartender will naturally work those into the
// conversation on their own if it goes that far — there's no need to script
// every option, just the strongest one or two.
@Data
public class ScriptResponse {

    private String orderedSpirit;

    // Same reasoning as RecommendationResponse — the ordered spirit's own
    // facts, exposed so a guest's "how so?" has a real answer underneath
    // the script, not just the line itself.
    private String orderedSpiritFlavorTags;
    private double orderedSpiritProof;
    private String orderedSpiritMashBill;

    private List<GeneratedScript> topPicks;
    private List<RecommendationResponse.SpiritMatch> otherOptions;

    // One of the top picks, with its generated line attached. Carries the
    // same comparison facts as a plain SpiritMatch (flavor tags, mash
    // bill, proof) even though it also has a full script, since a
    // bartender might want to go deeper than the script if a guest asks.
    @Data
    public static class GeneratedScript {
        private String name;
        private String distillery;
        private String flavorTags;
        private String mashBill;
        private double pricePour;
        private double proof;
        private boolean comparisonOnly;
        private boolean aspirational;
        private String script;
    }
}