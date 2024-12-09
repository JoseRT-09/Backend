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
    private String lastname;

    @NotBlank
    private String mail;

    @NotBlank
    private String phone;

    @NotBlank
    private String rfc;

    @NotBlank
    private String categories;

    @NotBlank
    private String status;


    //direccion
    @NotBlank
    private String state;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;

    @NotBlank
    private String colony;

    @NotBlank
    private String street;

    @NotBlank
    private String nh;

}
