package com.backend.crud.repository;

import com.backend.crud.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findBySupplier(String supplier);
}
