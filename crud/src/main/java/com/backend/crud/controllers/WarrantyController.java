package com.backend.crud.controllers;


import com.backend.crud.entities.Warranty;
import com.backend.crud.services.WarrantyServiceImpl;
import com.backend.crud.services.WarrantyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/warranties")
@CrossOrigin("http://localhost:4200/")


public class WarrantyController {

    @Autowired
    WarrantyServiceImpl warrantyServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Warranty> saveWarranty(@RequestBody Warranty warranty) {
        try {
            Warranty savedWarranty = warrantyServiceImpl.saveWarranty(warranty);
            return new ResponseEntity<>(savedWarranty, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Warranty> updateWarranty(@RequestBody Warranty warranty) {
        try {
            Warranty updatedWarranty = warrantyServiceImpl.updateWarranty(warranty);
            return new ResponseEntity<>(updatedWarranty, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Warranty>> getAllWarranty() {
        return new ResponseEntity<>(warrantyServiceImpl.getWarranty(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Warranty> getWarrantyById(@PathVariable Long id) {
        Optional<Warranty> warranty = warrantyServiceImpl.getWarrantyById(id);
        return warranty.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Warranty> deleteWarrantyById(@PathVariable Long id) {
        Optional<Warranty> warranty = warrantyServiceImpl.getWarrantyById(id);
        if (warranty.isPresent()) {
            warrantyServiceImpl.deleteWarrantyById(warranty.get().getId());
            return new ResponseEntity<>(warranty.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
