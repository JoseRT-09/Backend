package com.backend.crud.services;

import com.backend.crud.entities.Parts;
import com.backend.crud.entities.Warranty;

import java.util.List;
import java.util.Optional;

public interface WarrantyService {

    Warranty saveWarranty(Warranty warranty);
    Warranty updateWarranty(Warranty warranty);
    List<Warranty> getWarranty();
    Optional<Warranty> getWarrantyById(Long id);
    void deleteWarrantyById(Long id);
}
