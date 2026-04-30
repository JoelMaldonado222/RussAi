package com.russai.russai.controller;

import com.russai.russai.model.Spirit;
import com.russai.russai.repository.SpiritRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
}