package com.backend.crud.services;

import com.backend.crud.entities.Repair;
import com.backend.crud.entities.Client;
import com.backend.crud.repository.RepairRepository;
import com.backend.crud.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairRepository repairRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public Repair saveRepair(Repair repair) {
        // Guardamos el cliente sin validar el correo
        Client savedClient = clientRepository.save(repair.getClient());

        // Establecer el cliente guardado en la reparación
        repair.setClient(savedClient);

        // Guardar la reparación
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