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

    // ⚠️ TEMPORARY — one-time embedding backfill trigger. Generates and stores
    // embeddings for all spirits. Safe to re-run (overwrites). DELETE THIS
    // before final build, same as EmbeddingTestController.
    @PostMapping("/backfill-embeddings")
    public ResponseEntity<Map<String, Object>> backfillEmbeddings() {
        return ResponseEntity.ok(spiritService.backfillEmbeddings());
    }
}