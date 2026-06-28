package com.russai.russai.controller;

import com.russai.russai.model.Spirit;
import com.russai.russai.service.SpiritService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/spirits")
public class SpiritController {

    private final SpiritService spiritService;

    public SpiritController(SpiritService spiritService) {
        this.spiritService = spiritService;
    }

    // GET /api/spirits — returns all spirits
    @GetMapping
    public List<Spirit> getAllSpirits() {
        return spiritService.getAllSpirits();
    }

    // GET /api/spirits/{id} — returns one spirit or 404
    @GetMapping("/{id}")
    public ResponseEntity<Spirit> getSpiritById(@PathVariable UUID id) {
        return spiritService.getSpiritById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/spirits — creates a new spirit
    @PostMapping
    public ResponseEntity<Spirit> createSpirit(@RequestBody Spirit spirit) {
        Spirit created = spiritService.createSpirit(spirit);
        return ResponseEntity.status(201).body(created);
    }

    // PUT /api/spirits/{id} — replaces entire spirit record
    @PutMapping("/{id}")
    public ResponseEntity<Spirit> updateSpirit(@PathVariable UUID id,
                                               @RequestBody Spirit updatedSpirit) {
        return spiritService.updateSpirit(id, updatedSpirit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/spirits/{id} — updates only the fields sent
    @PatchMapping("/{id}")
    public ResponseEntity<Spirit> patchSpirit(@PathVariable UUID id,
                                              @RequestBody Map<String, Object> fields) {
        return spiritService.patchSpirit(id, fields)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/spirits/{id} — deletes a spirit
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpirit(@PathVariable UUID id) {
        if (spiritService.deleteSpirit(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ⚠️ TEMPORARY — production embeddings bootstrap. The original version
    // of this endpoint had no auth, which was fine on localhost but not on
    // a real public URL, even briefly — anyone who found it could trigger
    // real OpenAI charges. This version requires a private header matching
    // the ADMIN_KEY environment variable, set once in Railway, never
    // committed anywhere. REMOVE THIS ENTIRELY once the one-time
    // production backfill is confirmed done — it has no reason to exist
    // past that single use.
    @PostMapping("/backfill-embeddings")
    public ResponseEntity<?> backfillEmbeddings(
            @RequestHeader(value = "X-Admin-Key", required = false) String providedKey) {

        String realKey = System.getenv("ADMIN_KEY");
        if (realKey == null || realKey.isBlank()
                || providedKey == null || !providedKey.equals(realKey)) {
            return ResponseEntity.status(403).body(Map.of("error", "Forbidden"));
        }

        return ResponseEntity.ok(spiritService.backfillEmbeddings());
    }

    // ⚠️ TEMPORARY — diagnostic endpoint to verify the cosine similarity
    // search works end to end before it's wired into RecommendationService.
    // GET /api/spirits/similar-test?name=E.H. Taylor Barrel Proof&limit=5
    // Errors are caught here explicitly (instead of letting the generic
    // GlobalExceptionHandler swallow them into "Something went wrong") so
    // a typo'd name or a missing embedding shows a real, readable reason.
    // DELETE THIS before final build, same as EmbeddingTestController.
    @GetMapping("/similar-test")
    public ResponseEntity<Map<String, Object>> getSimilarSpiritsTest(
            @RequestParam String name,
            @RequestParam(defaultValue = "5") int limit) {
        try {
            List<Spirit> matches = spiritService.getSimilarSpirits(name, limit);
            return ResponseEntity.ok(Map.of(
                    "orderedSpirit", name,
                    "matches", matches
            ));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}