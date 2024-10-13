package com.backend.crud.repository;

import com.backend.crud.entities.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    Optional<Repair> findByRepair(String repairs);
}
