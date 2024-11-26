package com.backend.crud.services;


import com.backend.crud.entities.Repair;
import com.backend.crud.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    RepairRepository repairRepository;

    @Override
    public Repair saveRepair(Repair repair) {
        return repairRepository.save(repair);
    }

    @Override
    public Repair updateRepair(Repair repair) {
        if (repairRepository.existsById(repair.getId())) {
            return repairRepository.save(repair);
        }
        throw new RuntimeException("Repair no encontrado");
    }

    @Override
    public List<Repair> getRepair() {
        return repairRepository.findAll();
    }

    @Override
    public Optional<Repair> getRepairById(Long id) {
        return repairRepository.findById(id);
    }

    @Override
    public void deleteRepairById(Long id) {
        repairRepository.deleteById(id);
    }
}
