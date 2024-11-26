
package com.backend.crud.repository;

import com.backend.crud.entities.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    //Optional<Quotation> findByQuotationId(Long id);
}

