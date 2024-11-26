package com.backend.crud.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quotation;
    private String time;

    @NotBlank
    private String manufacturer;
    @NotBlank
    private String model;
    @NotBlank
    private String serialnumber;
    @NotBlank
    private String description;
    @NotBlank
    private String imgUrl;
    private String status;
    private String typeProblem;

}
