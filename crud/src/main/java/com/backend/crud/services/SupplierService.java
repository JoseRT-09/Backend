package com.backend.crud.services;

import com.backend.crud.entities.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    Supplier saveSupplier(Supplier supplier);
    Supplier updateSupplier(Supplier supplier);
    List<Supplier> getSupplier();
    Optional<Supplier> getSupplierById(Long id);
    void deleteSupplierById(Long id);
}
