package com.russai.russai.model;

import lombok.Data;
import java.util.List;

// This is what the engine sends back — the ordered spirit plus upsell suggestions
@Data
public class RecommendationResponse {

    private String orderedSpirit;

    // The ordered spirit's own facts, exposed alongside the suggestions so
    // a side-by-side comparison is possible without a second lookup. This
    // is the backup a bartender actually needs if a guest pushes back —
    // "the notes are similar? how?" — the real numbers and tags should be
    // sitting right here, not just summarized into one sentence.
    private String orderedSpiritFlavorTags;
    private double orderedSpiritProof;
    private String orderedSpiritMashBill;

    // NEW: the ordered spirit's own pour price, exposed alongside the other
    // ordered* facts. The UI needs the guest's current bottle price on
    // screen next to the upsell prices, and it wasn't being returned before.
    private double orderedSpiritPricePour;

    private List<SpiritMatch> recommendations;

    // Each individual recommendation — what to suggest and why
    @Data
    public static class SpiritMatch {
        private String name;
        private String distillery;
        private String flavorTags;
        private String mashBill;
        private double pricePour;
        private double proof;
        private String reason;

        // NEW: batch type (e.g. "Barrel Proof", "Single Barrel", "Bottled
        // in Bond"). A term guests recognize — shown on every card.
        private String batchType;

        // NEW: age statement in years. Integer (not int) so "no age
        // statement" stays null and the UI hides it instead of showing 0.
        private Integer ageStatement;

        // NEW: barrel finish (e.g. "Port Wine Cask"). Usually "None"; the UI
        // shows it only when it's something worth mentioning.
        private String finish;

        // True when this spirit is cheaper than what was ordered. The engine
        // still includes it if the flavor is a genuinely close match, but it
        // should never be presented as a real upsell — the UI can use this
        // flag to show it as a side comparison/talking point instead of a
        // ranked recommendation, without having to parse the reason text.
        private boolean comparisonOnly;

        // True when this spirit costs more than three times what was
        // ordered. Still a genuinely close flavor match, but too big a
        // price leap to present as a realistic next pour — the UI can use
        // this to show it as a "worth mentioning" aside (a trophy bottle
        // the guest might be curious about) rather than a normal upsell.
        private boolean aspirational;
    }
}