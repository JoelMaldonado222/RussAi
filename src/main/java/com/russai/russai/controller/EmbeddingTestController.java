package com.russai.russai.controller;

import com.russai.russai.service.EmbeddingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

// Temporary diagnostic endpoint used to verify the Bedrock embedding
// pipeline end to end. Returns the dimension count and a small sample
// of the generated vector. Intended for development validation only.
@RestController
public class EmbeddingTestController {

    private final EmbeddingService embeddingService;

    public EmbeddingTestController(EmbeddingService embeddingService) {
        this.embeddingService = embeddingService;
    }

    // GET /api/embedding-test?text=some+flavor+words
    // Generates an embedding for the supplied text and reports its size
    // along with the first five values as a sanity check.
    @GetMapping("/api/embedding-test")
    public Map<String, Object> testEmbedding(
            @RequestParam(defaultValue = "vanilla caramel oak toffee") String text) {

        List<Float> embedding = embeddingService.generateEmbedding(text);

        return Map.of(
                "inputText", text,
                "dimensions", embedding.size(),
                "firstFiveValues", embedding.subList(0, Math.min(5, embedding.size()))
        );
    }
}