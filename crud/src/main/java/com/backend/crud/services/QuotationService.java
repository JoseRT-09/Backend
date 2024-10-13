package com.backend.crud.services;

import com.backend.crud.entities.Quotation;

import java.util.List;
import java.util.Optional;

public interface QuotationService {

    Quotation saveQuotation(Quotation quotation);
    Quotation updateQuotation(Quotation quotation);
    List<Quotation> getQuotation();
    Optional<Quotation> getQuotationById(Long id);
    void deleteQuotationById(Long id);
}
