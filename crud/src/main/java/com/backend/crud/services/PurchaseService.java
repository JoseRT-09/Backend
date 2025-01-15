package com.backend.crud.services;

import com.backend.crud.entities.Parts;
import com.backend.crud.entities.Purchase;
import com.backend.crud.entities.Supplier;
import com.backend.crud.enums.PurchaseStatus;
import com.backend.crud.repository.PurchaseRepository;
import com.backend.crud.services.PartsService;
import com.backend.crud.services.SupplierService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PartsService partsService;

    @Autowired
    private SupplierService supplierService;
    @PersistenceContext
    private EntityManager entityManager;



    @Transactional
    public Purchase createPurchase(Purchase purchase) {
        // Actualizar directamente con JPQL
        entityManager.createQuery(
                        "UPDATE Parts p SET p.quantity = p.quantity + :addQuantity WHERE p.id = :partId")
                .setParameter("addQuantity", purchase.getQuantity())
                .setParameter("partId", purchase.getPart().getId())
                .executeUpdate();

        // Guardar la compra
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setStatus(PurchaseStatus.COMPLETED);
        purchase.setTransactionId(UUID.randomUUID().toString());

        return purchaseRepository.save(purchase);
    }

    private void validateSupplier(Supplier supplier) {
        if (!supplierService.getSupplierById(supplier.getId()).isPresent()) {
            throw new RuntimeException("Proveedor no encontrado");
        }

        Supplier existingSupplier = supplierService.getSupplierById(supplier.getId()).get();
        if (!"ACTIVE".equals(existingSupplier.getStatus())) {
            throw new RuntimeException("El proveedor no está activo");
        }
    }

    private void processPayment(Purchase purchase) {
        // Aquí iría la lógica de procesamiento de pago
        purchase.setTransactionId(UUID.randomUUID().toString());
        purchase.setStatus(PurchaseStatus.COMPLETED);
    }

    private void updateInventory(Purchase purchase) {
        Parts part = purchase.getPart();
        part.setQuantity(part.getQuantity() + purchase.getQuantity());
        partsService.updateParts(part);
    }

    public List<Purchase> getAllPurchases() {
        // Cambiar PurchaseRepository.findAll() por purchaseRepository.findAll()
        return purchaseRepository.findAll();
    }

    public List<Purchase> getPurchasesByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        // Cambiar PurchaseRepository por purchaseRepository
        return purchaseRepository.findByPurchaseDateBetween(startDate, endDate);
    }

    public List<Purchase> getPurchasesBySupplier(Long supplierId) {
        return purchaseRepository.findBySupplierIdOrderByPurchaseDateDesc(supplierId);
    }

    public List<Purchase> getPurchasesByPart(Long partId) {
        return purchaseRepository.findByPartIdOrderByPurchaseDateDesc(partId);
    }
}