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

public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    private String email;

    @NotBlank
    private String category;
    @NotBlank
    private String bankdetails;
    @NotBlank
    private String status;

}
