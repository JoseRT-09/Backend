package com.backend.crud.services;

import com.backend.crud.entities.Quotation;
import com.backend.crud.repository.QuotationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationServiceImpl implements QuotationService {

    @Autowired
    QuotationRepository quotationRepository;

    @Override
    public Quotation saveQuotation(Quotation quotation) {
        return quotationRepository.save(quotation);
    }

    @Override
    public Quotation updateQuotation(Quotation quotation) {
        return null;
    }

    @Override
    public List<Quotation> getQuotation() {
        return quotationRepository.findAll();
    }

    @Override
    public Optional<Quotation> getQuotationById(Long id) {
        return quotationRepository.findById(id);
    }

    @Override
    public void deleteQuotationById(Long id) {
        quotationRepository.deleteById(id);
    }

}
