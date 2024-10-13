package com.backend.crud.controllers;


import com.backend.crud.entities.Repair;
import com.backend.crud.services.RepairServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/repairs")
@CrossOrigin("http://localhost:4200/")

public class RepairController {

    @Autowired
    RepairServiceImpl repairServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Repair> saveRepair(@RequestBody Repair repair) {
        try {
            Repair savedRepair = repairServiceImpl.saveRepair(repair);
            return new ResponseEntity<>(savedRepair, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Repair> updateRepair(@RequestBody Repair repair) {
        try {
            Repair updatedRepair = repairServiceImpl.updateRepair(repair);
            return new ResponseEntity<>(updatedRepair, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Repair>> getAllRepair() {
        return new ResponseEntity<>(repairServiceImpl.getRepair(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Repair> getUserById(@PathVariable Long id) {
        Optional<Repair> repair = repairServiceImpl.getRepairById(id);
        return repair.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Repair> deleteRepairById(@PathVariable Long id) {
        Optional<Repair> repair = repairServiceImpl.getRepairById(id);
        if (repair.isPresent()) {
            repairServiceImpl.deleteRepairById(repair.get().getId());
            return new ResponseEntity<>(repair.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
