package com.backend.crud.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity

public class Warranty {

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
