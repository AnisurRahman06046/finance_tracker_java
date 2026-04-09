package com.capstone.financeTracker.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.financeTracker.dto.TransactionRequestDTO;
import com.capstone.financeTracker.dto.TransactionResponse;
import com.capstone.financeTracker.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/{userId}")
    public TransactionResponse createTransaction(@PathVariable Long userId,@RequestBody @Valid TransactionRequestDTO transaction){
        return transactionService.createTransaction(userId, transaction);
    }
    
}
