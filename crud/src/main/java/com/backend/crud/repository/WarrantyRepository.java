package com.backend.crud.repository;

import com.backend.crud.entities.Repair;
import com.backend.crud.entities.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarrantyRepository extends JpaRepository<Warranty, Long> {
    Optional<Warranty> findByWarranty(String Warranty);
}
