package com.backend.crud.controllers;


import com.backend.crud.entities.Supplier;
import com.backend.crud.services.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
@CrossOrigin("http://localhost:4200/")
public class SupplierController {

    @Autowired
    SupplierServiceImpl supplierServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Supplier> saveSupplier(@RequestBody Supplier supplier) {
        try {
            Supplier savedSupplier = supplierServiceImpl.saveSupplier(supplier);
            return new ResponseEntity<>(savedSupplier, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Supplier> updateSupplier(@RequestBody Supplier supplier) {
        try {
            Supplier updatedSupplier = supplierServiceImpl.updateSupplier(supplier);
            return new ResponseEntity<>(updatedSupplier, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Supplier>> getAllSupplier() {
        return new ResponseEntity<>(supplierServiceImpl.getSupplier(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Supplier> getUserById(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierServiceImpl.getSupplierById(id);
        return supplier.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Supplier> deleteSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierServiceImpl.getSupplierById(id);
        if (supplier.isPresent()) {
            supplierServiceImpl.deleteSupplierById(supplier.get().getId());
            return new ResponseEntity<>(supplier.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
