package com.russai.russai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.russai.russai.model.RecommendationResponse.SpiritMatch;
import com.russai.russai.model.Spirit;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

// Turns the recommendation engine's structured output into short, natural,
// ready-to-say lines a bartender could actually use. This is the one place
// in the codebase that asks an LLM to write prose instead of just scoring
// or embedding data — everything it's given is a fact the scoring engine
// already computed, it's never asked to invent anything about a spirit.
//
// PROVIDER: OpenAI (gpt-5.4), called over HTTPS, same pattern as
// EmbeddingService. This is short, tonal copywriting, not complex
// reasoning, so the mini tier would technically work, but the cost gap to
// the standard tier is a fraction of a cent per call, irrelevant at this
// app's real volume — and this is the line a guest actually hears, so
// quality wins over shaving a cost difference that doesn't add up to
// anything. Bedrock remains the long-term target per the project's
// pluggable-provider design, but per the same reasoning that moved
// embeddings to OpenAI, there's no reason to fight for Bedrock model
// access for this either when OpenAI already works reliably.
@Service
public class ScriptGenerationService {

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    private static final String OPENAI_ENDPOINT = "https://api.openai.com/v1/chat/completions";

    // Using the standard GPT-5.4 tier, not the mini tier. This is short,
    // tonal, persuasive copywriting, not complex reasoning, so the absolute
    // flagship model isn't necessary, but the cost gap between mini and
    // standard here is a fraction of a cent per call, irrelevant at the
    // volume this app actually runs at. Since this line is what Joe (and
    // any future bartender) actually hears, quality wins over shaving
    // pennies that don't add up to anything real either way.
    private static final String MODEL_ID = "gpt-5.4";

    public ScriptGenerationService() {
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
    }

    // The bartender's voice and the exact shape each line should take.
    // Mirrors a real example: "Hey, if you're ever interested, I know you
    // like the Coy Hill, but the [pick] is only $[X] more, and the notes
    // are close, Coy Hill's got [flavor] and this one's got [flavor], plus
    // [whichever second real fact actually applies]." The second fact is
    // deliberately not fixed to one type — same distillery, same mash
    // bill, same cask finish, similar proof, longer age — it should be
    // whichever one is actually true for that specific pair, pulled from
    // the "known reasons" given, never invented.
    private static final String SYSTEM_PROMPT = """
            You write short, ready-to-say lines for a bartender to use when
            suggesting a guest's next pour. You are not writing marketing
            copy or a list of bullet points — you are writing exactly what
            a confident, knowledgeable bartender would say out loud in the
            moment, in casual, natural speech.

            Each line you write must, in this order:
            1. Casually acknowledge what the guest already ordered, by name.
            2. State the real price difference in dollars.
            3. Compare the two flavor profiles briefly, in plain words, not
               by just reading off the tag lists.
            4. Add exactly ONE more supporting fact, chosen from whichever
               of the given "known reasons" is actually true for that pair,
               same distillery, same mash bill, same cask finish, similar
               proof, more years aged, whatever genuinely applies. Never
               list more than one. Never invent a fact that wasn't given.

            Tone rules, these matter:
            - Sound certain and warm, never hedging, apologetic, or pushy.
              A guest should feel like they're getting a genuine, confident
              recommendation, not a sales pitch.
            - If a pick is tagged UPSELL, pitch it like you'd genuinely want
              them to try it.
            - If a pick is tagged ASPIRATIONAL, frame it as a fun, low-key
              mention, something like "if you're ever feeling fancy," not a
              real push, since the price jump is too large to be a normal
              next pour.
            - If a pick is tagged COMPARISON_ONLY, frame it as a side
              comparison or talking point, never as a suggestion to switch,
              since it's cheaper than what they already ordered.
            - Keep each line to two or three sentences, short enough to
              actually say out loud in a few seconds, not a paragraph.

            Respond with valid JSON only, no markdown, no commentary, in
            exactly this shape: {"scripts": ["line for option 1", "line for
            option 2"]}. The array must have exactly as many entries as
            there are options given, in the same order.
            """;

    // Generates one ready-to-say line per pick, in the same order given.
    public List<String> generateScripts(Spirit ordered, List<SpiritMatch> picks) {
        try {
            String apiKey = System.getenv("OPENAI_API_KEY");
            if (apiKey == null || apiKey.isBlank()) {
                throw new IllegalStateException(
                        "OPENAI_API_KEY environment variable is not set.");
            }

            String userPrompt = buildUserPrompt(ordered, picks);

            ObjectNode requestJson = objectMapper.createObjectNode();
            requestJson.put("model", MODEL_ID);
            requestJson.put("temperature", 0.7);

            ObjectNode responseFormat = objectMapper.createObjectNode();
            responseFormat.put("type", "json_object");
            requestJson.set("response_format", responseFormat);

            ArrayNode messages = objectMapper.createArrayNode();

            ObjectNode systemMessage = objectMapper.createObjectNode();
            systemMessage.put("role", "system");
            systemMessage.put("content", SYSTEM_PROMPT);
            messages.add(systemMessage);

            ObjectNode userMessage = objectMapper.createObjectNode();
            userMessage.put("role", "user");
            userMessage.put("content", userPrompt);
            messages.add(userMessage);

            requestJson.set("messages", messages);
            String requestBody = objectMapper.writeValueAsString(requestJson);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OPENAI_ENDPOINT))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "OpenAI returned status " + response.statusCode()
                                + ": " + response.body());
            }

            // Chat completions nests the model's reply at choices[0].message.content
            JsonNode responseJson = objectMapper.readTree(response.body());
            String content = responseJson
                    .get("choices").get(0)
                    .get("message").get("content")
                    .asText();

            // The model was instructed to return {"scripts": [...]} as its
            // entire reply, since response_format is set to json_object.
            JsonNode contentJson = objectMapper.readTree(content);
            JsonNode scriptsArray = contentJson.get("scripts");

            List<String> scripts = new ArrayList<>();
            for (JsonNode node : scriptsArray) {
                scripts.add(node.asText());
            }
            return scripts;

        } catch (Exception e) {
            throw new RuntimeException("Failed to generate scripts: " + e.getMessage(), e);
        }
    }

    // Builds the structured facts the model is allowed to use — nothing
    // more. Each pick includes its tier (UPSELL / ASPIRATIONAL /
    // COMPARISON_ONLY) and the existing "reason" string the scoring engine
    // already built, which is where the model finds that one real
    // supporting fact to mention.
    private String buildUserPrompt(Spirit ordered, List<SpiritMatch> picks) {
        StringBuilder sb = new StringBuilder();

        sb.append("Guest ordered: ").append(ordered.getName())
                .append(" — flavor: ").append(ordered.getFlavorTags())
                .append(", mash bill: ").append(ordered.getMashBill())
                .append(", price: $").append(ordered.getPricePour())
                .append(", proof: ").append(ordered.getProof())
                .append("\n\n");

        sb.append("Write one line for each of these ")
                .append(picks.size())
                .append(" options, in order:\n\n");

        for (int i = 0; i < picks.size(); i++) {
            SpiritMatch m = picks.get(i);
            double priceDiff = m.getPricePour() - ordered.getPricePour().doubleValue();

            String tier = m.isComparisonOnly() ? "COMPARISON_ONLY"
                    : m.isAspirational() ? "ASPIRATIONAL"
                    : "UPSELL";

            sb.append(i + 1).append(". ").append(m.getName())
                    .append(" — distillery: ").append(m.getDistillery())
                    .append(", flavor: ").append(m.getFlavorTags())
                    .append(", mash bill: ").append(m.getMashBill())
                    .append(", price: $").append(m.getPricePour())
                    .append(" (price difference from guest's pour: $")
                    .append(String.format("%.0f", priceDiff)).append(")")
                    .append(", proof: ").append(m.getProof())
                    .append(", tier: ").append(tier)
                    .append(", known reasons: ").append(m.getReason())
                    .append("\n");
        }

        return sb.toString();
    }
}