/*
package com.backend.crud.config;

import com.backend.crud.entities.Quotation;
import com.backend.crud.repository.QuotationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    CommandLineRunner testRunner(QuotationRepository repository) {
        return args -> {
            Quotation quotation = new Quotation();
            quotation.setQuotation("Test Quotation");
            quotation.setManufacturer("Test Manufacturer");
            quotation.setModel("Test Model");
            quotation.setSerialnumber("123456789");
            quotation.setDescription("Sample description");
            quotation.setImgUrl("http://example.com/image.jpg");
            quotation.setTypeProblem("Sample Problem");

            // Intenta guardar la entidad en la base de datos
            repository.save(quotation);
            System.out.println("Quotation saved successfully!");
        };
    }
}

 */