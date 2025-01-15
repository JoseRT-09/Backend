package com.backend.crud.controllers;

import com.backend.crud.entities.*;
import com.backend.crud.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/purchases")
@CrossOrigin("http://localhost:4200/")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        try {
            System.out.println("Request de compra recibido:");
            System.out.println("Part ID: " + purchase.getPart().getId());
            System.out.println("Supplier ID: " + purchase.getSupplier().getId());
            System.out.println("Cantidad: " + purchase.getQuantity());

            Purchase savedPurchase = purchaseService.createPurchase(purchase);

            return ResponseEntity.ok(savedPurchase);
        } catch (Exception e) {
            System.err.println("Error en compra: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        try {
            List<Purchase> purchases = purchaseService.getAllPurchases();
            return ResponseEntity.ok(purchases);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Obtener compras por fecha
    @GetMapping("/date-range")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Purchase>> getPurchasesByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        try {
            List<Purchase> purchases = purchaseService.getPurchasesByDateRange(startDate, endDate);
            return ResponseEntity.ok(purchases);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Obtener compras por proveedor
    @GetMapping("/supplier/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Purchase>> getPurchasesBySupplier(@PathVariable Long id) {
        try {
            List<Purchase> purchases = purchaseService.getPurchasesBySupplier(id);
            return ResponseEntity.ok(purchases);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Obtener compras por parte
    @GetMapping("/part/{id}")
    @PreAuthorize("hasRole('TECHNICAL')")
    public ResponseEntity<List<Purchase>> getPurchasesByPart(@PathVariable Long id) {
        try {
            List<Purchase> purchases = purchaseService.getPurchasesByPart(id);
            return ResponseEntity.ok(purchases);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}