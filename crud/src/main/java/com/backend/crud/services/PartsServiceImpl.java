package com.backend.crud.services;

import com.backend.crud.entities.Parts;
import com.backend.crud.repository.PartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartsServiceImpl implements PartsService {

    @Autowired
    PartsRepository partsRepository;

    @Override
    public Parts saveParts(Parts parts) {
        return partsRepository.save(parts);
    }

    @Override
    public Parts updateParts(Parts parts) {
        return null;
    }

    @Override
    public List<Parts> getParts() {
        return partsRepository.findAll();
    }

    @Override
    public Optional<Parts> getPartsById(Long id) {
        return partsRepository.findById(id);
    }

    @Override
    public void deletePartsById(Long id) {
        partsRepository.deleteById(id);
    }

    public Optional<Parts> getPartsByname(String name) {
        return partsRepository.findByname(name);
    }
}
