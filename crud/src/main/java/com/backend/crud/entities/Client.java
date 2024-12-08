package com.backend.crud.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String lastname;

    private String mail;

    @NotBlank
    private String phone;

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