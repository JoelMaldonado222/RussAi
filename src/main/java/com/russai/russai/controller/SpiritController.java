package com.russai.russai.controller;

import com.russai.russai.model.Spirit;
import com.russai.russai.repository.SpiritRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.UUID;

// This class handles HTTP requests and returns JSON automatically
@RestController
// All endpoints in this class live under this URL (/api/spirits)
@RequestMapping("/api/spirits")
public class SpiritController {

    // keep repository here so can talk to the database
    private final SpiritRepository spiritRepository;

    // This is the clean way to "wire in"  repository without needing @Autowired
    public SpiritController(SpiritRepository spiritRepository) {
        this.spiritRepository = spiritRepository;
    }

    // This maps a "GET" request to the method below
    @GetMapping
    public List<Spirit> getAllSpirits() {
        // asking the repository to go grab every spirit in the table
        return spiritRepository.findAll();
    }

    // This maps GET /api/spirits/{id} — the {id} comes from the URL
@GetMapping("/{id}")
public ResponseEntity<Spirit> getSpiritById(@PathVariable UUID id) {
    // look for the spirit in the database — it might or might not exist
    Optional<Spirit> spirit = spiritRepository.findById(id);

    // if it exists return it with 200 OK, if not return 404 Not Found
    if (spirit.isPresent()) {
        return ResponseEntity.ok(spirit.get());
    } else {
        return ResponseEntity.notFound().build();
    }
}

// This maps POST /api/spirits — receives a new spirit in the request body and saves it
@PostMapping
public ResponseEntity<Spirit> createSpirit(@RequestBody Spirit spirit) {
    // save the spirit to the database and return it with 201 Created
    Spirit saved = spiritRepository.save(spirit);
    return ResponseEntity.status(201).body(saved);
}
}