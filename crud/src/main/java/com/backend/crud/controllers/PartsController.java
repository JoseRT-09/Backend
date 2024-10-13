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
    PartsServiceImpl partsServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> saveParts(@RequestBody Parts parts) {
        try {
            Parts savedParts = partsServiceImpl.saveParts(parts);
            return new ResponseEntity<>(savedParts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> updateParts(@RequestBody Parts parts) {
        try {
            Parts updatedParts = partsServiceImpl.updateParts(parts);
            return new ResponseEntity<>(updatedParts, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Parts>> getAllParts() {
        return new ResponseEntity<>(partsServiceImpl.getParts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> getUserById(@PathVariable Long id) {
        Optional<Parts> parts = partsServiceImpl.getPartsById(id);
        return parts.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Parts> deletePartsById(@PathVariable Long id) {
        Optional<Parts> parts = partsServiceImpl.getPartsById(id);
        if (parts.isPresent()) {
            partsServiceImpl.deletePartsById(parts.get().getId());
            return new ResponseEntity<>(parts.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
