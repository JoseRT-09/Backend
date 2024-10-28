package com.backend.crud.controllers;


import com.backend.crud.entities.Quotation;
import com.backend.crud.services.QuotationService;
import com.backend.crud.services.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/quotation")
@CrossOrigin("http://localhost:4200/")

public class QuotationController {

    @Autowired
    QuotationServiceImpl quotationServiceImpl;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Quotation> saveQuotation(@RequestBody Quotation quotation) {
        try {
            Quotation savedQuotation = quotationServiceImpl.saveQuotation(quotation);
            return new ResponseEntity<>(savedQuotation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Quotation> updateQuotation(@RequestBody Quotation quotation) {
        try {
            Quotation updatedQuotation = quotationServiceImpl.updateQuotation(quotation);
            return new ResponseEntity<>(updatedQuotation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Quotation>> getAllQuotation() {
        return new ResponseEntity<>(quotationServiceImpl.getQuotation(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Quotation> getQuotationById(@PathVariable Long id) {
        Optional<Quotation> quotation = quotationServiceImpl.getQuotationById(id);
        return quotation.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Quotation> deleteQuotationById(@PathVariable Long id) {
        Optional<Quotation> quotation = quotationServiceImpl.getQuotationById(id);
        if (quotation.isPresent()) {
            quotationServiceImpl.deleteQuotationById(quotation.get().getId());
            return new ResponseEntity<>(quotation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
