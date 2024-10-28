package com.backend.crud.services;

import com.backend.crud.entities.Parts;

import java.util.List;
import java.util.Optional;


public interface PartsService {

    Parts saveParts(Parts parts);
    Parts updateParts(Parts parts);
    List<Parts> getParts();
    Optional<Parts> getPartsById(Long id);
    void deletePartsById(Long id);
}
