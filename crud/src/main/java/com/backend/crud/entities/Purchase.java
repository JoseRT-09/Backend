package com.backend.crud.entities;

import com.backend.crud.entities.Parts;
import com.backend.crud.entities.Supplier;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import com.backend.crud.enums.PurchaseStatus;

@Entity
@Data
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private Supplier supplier;

    @NotNull
    @ManyToOne
    private Parts part;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @PositiveOrZero
    private Double unitPrice;

    @NotNull
    @PositiveOrZero
    private Double totalAmount;

    private String cardNumber;
    private String cardExpiry;
    private String cardCVV;

    @NotNull
    private LocalDateTime purchaseDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus status;

    private String transactionId;
}