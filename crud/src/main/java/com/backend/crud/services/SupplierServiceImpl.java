package com.backend.crud.services;


import com.backend.crud.entities.Supplier;
import com.backend.crud.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        return null;
    }

    @Override
    public List<Supplier> getSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }

    public Optional<Supplier> getSupplierById(String supplier) {
        return supplierRepository.findBySupplier(supplier);
    }
}
