package com.backend.crud.repository;

import com.backend.crud.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByPurchaseDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Purchase> findBySupplierIdOrderByPurchaseDateDesc(Long supplierId);

    List<Purchase> findByPartIdOrderByPurchaseDateDesc(Long partId);

    @Query("SELECT p FROM Purchase p ORDER BY p.purchaseDate DESC")
    List<Purchase> findAllOrderByPurchaseDateDesc();
}