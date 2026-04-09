package com.capstone.financeTracker.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionResponse {
    
    private Long id;
    private Double amount;
    private String type;
    private LocalDate date;
    private String description;
}
