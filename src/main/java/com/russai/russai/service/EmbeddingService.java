package com.russai.russai.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

// Generates vector embeddings for spirit flavor profiles using Amazon Titan
// via AWS Bedrock. Each embedding is a 1536-dimension representation of the
// input text, used for cosine-similarity search in the recommendation engine.
@Service
public class EmbeddingService {

    // Bedrock runtime client responsible for all calls to AWS.
    private final BedrockRuntimeClient bedrockClient;

    // Jackson mapper for building request JSON and parsing response JSON.
    private final ObjectMapper objectMapper;

    // Amazon Titan Text Embeddings V2 — outputs 1536 dimensions to match
    // the embedding column defined in the spirits table.
    private static final String MODEL_ID = "amazon.titan-embed-text-v2:0";

    public EmbeddingService() {
        // Bedrock client is pinned to us-east-1. AWS credentials are resolved
        // automatically by the SDK's default provider chain.
        this.bedrockClient = BedrockRuntimeClient.builder()
                .region(Region.US_EAST_1)
                .build();

        this.objectMapper = new ObjectMapper();
    }

    // Accepts arbitrary text and returns its embedding as a list of floats.
    public List<Float> generateEmbedding(String text) {
        try {
            // Build the request payload Titan expects: {"inputText": "..."}
            ObjectNode requestJson = objectMapper.createObjectNode();
            requestJson.put("inputText", text);
            String requestBody = objectMapper.writeValueAsString(requestJson);

            // Wrap the payload in a Bedrock InvokeModel request.
            InvokeModelRequest request = InvokeModelRequest.builder()
                    .modelId(MODEL_ID)
                    .contentType("application/json")
                    .accept("application/json")
                    .body(SdkBytes.fromUtf8String(requestBody))
                    .build();

            // Invoke the model. This performs the network call to Bedrock.
            InvokeModelResponse response = bedrockClient.invokeModel(request);

            // Parse the response body and extract the embedding array.
            String responseBody = response.body().asUtf8String();
            JsonNode responseJson = objectMapper.readTree(responseBody);
            JsonNode embeddingArray = responseJson.get("embedding");

            // Convert the JSON array into a List<Float>.
            List<Float> embedding = new ArrayList<>();
            for (JsonNode value : embeddingArray) {
                embedding.add(value.floatValue());
            }

            return embedding;

        } catch (Exception e) {
            // Surface any failure (network, AWS error, malformed response)
            // as a runtime exception with context for the caller.
            throw new RuntimeException("Failed to generate embedding: " + e.getMessage(), e);
        }
    }
}