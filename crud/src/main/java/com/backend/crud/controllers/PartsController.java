package com.backend.crud.controllers;

import com.backend.crud.entities.Parts;
import com.backend.crud.services.PartsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/parts")
@CrossOrigin("http://localhost:4200/")
public class PartsController {

    @Autowired
    private PartsServiceImpl partsServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> saveParts(@RequestBody Parts parts) {
        try {
            Parts savedParts = partsServiceImpl.saveParts(parts);
            return ResponseEntity.ok(savedParts);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> updateParts(@PathVariable Long id, @RequestBody Parts parts) {
        try {
            if (!partsServiceImpl.getPartsById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }
            parts.setId(id);
            Parts updatedParts = partsServiceImpl.updateParts(parts);
            return ResponseEntity.ok(updatedParts);
        } catch (Exception e) {
            e.printStackTrace(); // Para logging del error
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Parts>> getAllParts() {
        return ResponseEntity.ok(partsServiceImpl.getParts());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> getPartById(@PathVariable Long id) {
        return partsServiceImpl.getPartsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> deletePartsById(@PathVariable Long id) {
        Optional<Parts> parts = partsServiceImpl.getPartsById(id);
        if (parts.isPresent()) {
            partsServiceImpl.deletePartsById(id);
            return ResponseEntity.ok(parts.get());
        }
        return ResponseEntity.notFound().build();
    }
}