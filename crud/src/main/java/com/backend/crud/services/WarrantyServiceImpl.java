package com.backend.crud.services;

import com.backend.crud.entities.Warranty;
import com.backend.crud.repository.WarrantyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class WarrantyServiceImpl implements WarrantyService {

    @Autowired
    WarrantyRepository warrantyRepository;

    @Override
    public Warranty saveWarranty(Warranty warranty) {
        return warrantyRepository.save(warranty);
    }

    @Override
    public Warranty updateWarranty(Warranty warranty) {
        if (warrantyRepository.existsById(warranty.getId())) {
            return warrantyRepository.save(warranty);
        }
        throw new RuntimeException("Garantía no encontrada");
    }

    @Override
    public List<Warranty> getWarranty() {
        return warrantyRepository.findAll();
    }

    @Override
    public Optional<Warranty> getWarrantyById(Long id) {
        return warrantyRepository.findById(id);
    }

    @Override
    public void deleteWarrantyById(Long id) {
        warrantyRepository.deleteById(id);
    }

}
