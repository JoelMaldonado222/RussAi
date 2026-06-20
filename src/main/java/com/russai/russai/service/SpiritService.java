package com.russai.russai.service;

import com.russai.russai.model.Spirit;
import com.russai.russai.repository.SpiritRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

// @Service tells Spring this is a business logic class
// Spring manages it and makes it available for injection into the controller
@Service
public class SpiritService {

    // Service depends on the repository to talk to the database
    private final SpiritRepository spiritRepository;

    // Service depends on EmbeddingService to generate vector embeddings
    // for spirit flavor profiles during backfill and on creation.
    private final EmbeddingService embeddingService;

    // Constructor injection — clean way to wire dependencies
    public SpiritService(SpiritRepository spiritRepository,
                         EmbeddingService embeddingService) {
        this.spiritRepository = spiritRepository;
        this.embeddingService = embeddingService;
    }

    // GET all spirits — delegates to repository
    public List<Spirit> getAllSpirits() {
        return spiritRepository.findAll();
    }

    // GET one spirit by ID — returns Optional so caller handles not found
    public Optional<Spirit> getSpiritById(UUID id) {
        return spiritRepository.findById(id);
    }

    // POST — creates a new spirit and returns the saved record
    public Spirit createSpirit(Spirit spirit) {
        return spiritRepository.save(spirit);
    }

    // PUT — replaces every field on an existing spirit
    public Optional<Spirit> updateSpirit(UUID id, Spirit updatedSpirit) {
        return spiritRepository.findById(id).map(existing -> {
            existing.setName(updatedSpirit.getName());
            existing.setCategory(updatedSpirit.getCategory());
            existing.setDistillery(updatedSpirit.getDistillery());
            existing.setMashBill(updatedSpirit.getMashBill());
            existing.setFlavorTags(updatedSpirit.getFlavorTags());
            existing.setPricePour(updatedSpirit.getPricePour());
            existing.setProof(updatedSpirit.getProof());
            existing.setAgeStatement(updatedSpirit.getAgeStatement());
            existing.setBatchType(updatedSpirit.getBatchType());
            existing.setFinish(updatedSpirit.getFinish());
            return spiritRepository.save(existing);
        });
    }

    // PATCH — updates only the fields that are sent, leaves everything else untouched
    public Optional<Spirit> patchSpirit(UUID id, Map<String, Object> fields) {
        return spiritRepository.findById(id).map(existing -> {
            fields.forEach((key, value) -> {
                switch (key) {
                    case "name" -> existing.setName((String) value);
                    case "category" -> existing.setCategory((String) value);
                    case "distillery" -> existing.setDistillery((String) value);
                    case "mashBill" -> existing.setMashBill((String) value);
                    case "flavorTags" -> existing.setFlavorTags((String) value);
                    case "batchType" -> existing.setBatchType((String) value);
                    case "finish" -> existing.setFinish((String) value);
                    case "ageStatement" -> existing.setAgeStatement((Integer) value);
                    case "pricePour" -> existing.setPricePour(
                            new java.math.BigDecimal(value.toString()));
                    case "proof" -> existing.setProof(
                            new java.math.BigDecimal(value.toString()));
                }
            });
            return spiritRepository.save(existing);
        });
    }

    // DELETE — returns true if deleted, false if not found
    public boolean deleteSpirit(UUID id) {
        if (spiritRepository.existsById(id)) {
            spiritRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Builds the text that represents a spirit's identity and flavor for
    // embedding. Combines name, category, distillery, mash bill, and flavor
    // tags into one string. Proof and price are intentionally excluded — those
    // are handled as precise numeric rules in the recommendation scoring
    // engine, while the embedding captures flavor and character similarity.
    private String buildEmbeddingText(Spirit spirit) {
        StringBuilder sb = new StringBuilder();
        if (spirit.getName() != null)       sb.append(spirit.getName()).append(". ");
        if (spirit.getCategory() != null)   sb.append(spirit.getCategory()).append(". ");
        if (spirit.getDistillery() != null) sb.append(spirit.getDistillery()).append(". ");
        if (spirit.getMashBill() != null)   sb.append(spirit.getMashBill()).append(". ");
        if (spirit.getFlavorTags() != null) sb.append(spirit.getFlavorTags());
        return sb.toString().trim();
    }

    // Converts a List<Float> embedding into the string format pgvector expects,
    // e.g. [0.1,0.2,0.3]. This string is cast to the vector type inside the
    // native updateEmbedding query.
    private String toVectorString(List<Float> embedding) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < embedding.size(); i++) {
            sb.append(embedding.get(i));
            if (i < embedding.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    // One-time (re-runnable) backfill: generates and stores an embedding for
    // every spirit. Safe to run repeatedly — each run overwrites existing
    // embeddings, so improved flavor text can be re-embedded at any time.
    // Returns a summary of how many succeeded and failed.
    public Map<String, Object> backfillEmbeddings() {
        List<Spirit> spirits = spiritRepository.findAll();
        int success = 0;
        List<String> failures = new ArrayList<>();

        for (Spirit spirit : spirits) {
            try {
                String text = buildEmbeddingText(spirit);
                List<Float> embedding = embeddingService.generateEmbedding(text);
                String vectorString = toVectorString(embedding);
                spiritRepository.updateEmbedding(spirit.getSpiritId(), vectorString);
                success++;
            } catch (Exception e) {
                failures.add(spirit.getName() + ": " + e.getMessage());
            }
        }

        return Map.of(
                "totalSpirits", spirits.size(),
                "succeeded", success,
                "failed", failures.size(),
                "failures", failures
        );
    }
}