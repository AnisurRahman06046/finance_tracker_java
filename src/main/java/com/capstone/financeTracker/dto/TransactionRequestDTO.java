package com.capstone.financeTracker.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {
    
    @NotNull
    @Positive
    private Double amount;

    @NotBlank
    private String type;

    @NotNull 
    private LocalDate date;

    private String description;
}
