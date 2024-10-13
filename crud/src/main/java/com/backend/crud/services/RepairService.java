package com.backend.crud.services;

import com.backend.crud.entities.Repair;

import java.util.List;
import java.util.Optional;

public interface RepairService {
    Repair saveRepair(Repair repair);
    Repair updateRepair(Repair repair);
    List<Repair> getRepair();
    Optional<Repair> getRepairById(Long id);
    void deleteRepairById(Long id);
}
