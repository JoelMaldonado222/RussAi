package com.russai.russai.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

// Generates vector embeddings for spirit flavor profiles.
//
// PROVIDER: OpenAI (text-embedding-3-small), called over HTTPS.
// The embedding provider is intentionally isolated behind this one method so
// it can be swapped without touching the vector store or the recommendation
// engine. Amazon Bedrock Titan remains the production target (see commented
// implementation at the bottom) and was the original provider; it was swapped
// to OpenAI during development while account-level Bedrock quota provisioning
// was pending. Output is pinned to 1536 dimensions to match the embedding
// column defined in the spirits table.
@Service
public class EmbeddingService {

    // Jackson mapper for building request JSON and parsing response JSON.
    private final ObjectMapper objectMapper;

    // Reusable HTTP client for calls to the OpenAI embeddings endpoint.
    private final HttpClient httpClient;

    // OpenAI embeddings endpoint.
    private static final String OPENAI_ENDPOINT = "https://api.openai.com/v1/embeddings";

    // text-embedding-3-small with an explicit 1536-dimension output, matching
    // the vector(1536) column in the spirits table.
    private static final String MODEL_ID = "text-embedding-3-small";
    private static final int DIMENSIONS = 1536;

    public EmbeddingService() {
        this.objectMapper = new ObjectMapper();
        this.httpClient = HttpClient.newHttpClient();
    }

    // Accepts arbitrary text and returns its embedding as a list of floats.
    public List<Float> generateEmbedding(String text) {
        try {
            // The API key is read from the environment, never hardcoded. It is
            // exported in the shell profile (OPENAI_API_KEY) and resolved at
            // runtime, keeping the secret out of the repository entirely.
            String apiKey = System.getenv("OPENAI_API_KEY");
            if (apiKey == null || apiKey.isBlank()) {
                throw new IllegalStateException(
                        "OPENAI_API_KEY environment variable is not set.");
            }

            // Build the request payload OpenAI expects:
            // {"input": "...", "model": "...", "dimensions": 1536}
            ObjectNode requestJson = objectMapper.createObjectNode();
            requestJson.put("input", text);
            requestJson.put("model", MODEL_ID);
            requestJson.put("dimensions", DIMENSIONS);
            String requestBody = objectMapper.writeValueAsString(requestJson);

            // Build the HTTPS POST request with auth and content-type headers.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(OPENAI_ENDPOINT))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Send the request and capture the response body as a String.
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());

            // Any non-200 status means the call failed; surface the body so the
            // cause (bad key, rate limit, malformed input) is visible.
            if (response.statusCode() != 200) {
                throw new RuntimeException(
                        "OpenAI returned status " + response.statusCode()
                                + ": " + response.body());
            }

            // Parse the response and extract the embedding array. OpenAI nests
            // the vector at data[0].embedding (one level deeper than Titan).
            JsonNode responseJson = objectMapper.readTree(response.body());
            JsonNode embeddingArray = responseJson
                    .get("data")
                    .get(0)
                    .get("embedding");

            // Convert the JSON array into a List<Float>.
            List<Float> embedding = new ArrayList<>();
            for (JsonNode value : embeddingArray) {
                embedding.add(value.floatValue());
            }

            return embedding;

        } catch (Exception e) {
            // Surface any failure (network, API error, malformed response)
            // as a runtime exception with context for the caller.
            throw new RuntimeException("Failed to generate embedding: " + e.getMessage(), e);
        }
    }

    /*
     * ============================================================
     * PRODUCTION TARGET — Amazon Bedrock (Titan) implementation.
     * ------------------------------------------------------------
     * This was the original provider. It is preserved here as the
     * intended production implementation. To swap back, restore the
     * AWS SDK imports and the bedrockruntime dependency in pom.xml,
     * then replace the OpenAI call above with this. Output is also
     * 1536 dimensions, so no schema change is required either way.
     *
     * private final BedrockRuntimeClient bedrockClient =
     *         BedrockRuntimeClient.builder()
     *                 .region(Region.US_EAST_1)
     *                 .build();
     *
     * private static final String MODEL_ID = "amazon.titan-embed-text-v2:0";
     *
     * public List<Float> generateEmbedding(String text) {
     *     try {
     *         ObjectNode requestJson = objectMapper.createObjectNode();
     *         requestJson.put("inputText", text);
     *         String requestBody = objectMapper.writeValueAsString(requestJson);
     *
     *         InvokeModelRequest request = InvokeModelRequest.builder()
     *                 .modelId(MODEL_ID)
     *                 .contentType("application/json")
     *                 .accept("application/json")
     *                 .body(SdkBytes.fromUtf8String(requestBody))
     *                 .build();
     *
     *         InvokeModelResponse response = bedrockClient.invokeModel(request);
     *
     *         String responseBody = response.body().asUtf8String();
     *         JsonNode responseJson = objectMapper.readTree(responseBody);
     *         JsonNode embeddingArray = responseJson.get("embedding");
     *
     *         List<Float> embedding = new ArrayList<>();
     *         for (JsonNode value : embeddingArray) {
     *             embedding.add(value.floatValue());
     *         }
     *         return embedding;
     *     } catch (Exception e) {
     *         throw new RuntimeException("Failed to generate embedding: " + e.getMessage(), e);
     *     }
     * }
     * ============================================================
     */
}