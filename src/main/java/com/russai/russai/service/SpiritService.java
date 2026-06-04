package com.russai.russai.service;

import com.russai.russai.model.Spirit;
import com.russai.russai.repository.SpiritRepository;
import org.springframework.stereotype.Service;

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

    // Constructor injection — clean way to wire dependencies
    public SpiritService(SpiritRepository spiritRepository) {
        this.spiritRepository = spiritRepository;
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
}