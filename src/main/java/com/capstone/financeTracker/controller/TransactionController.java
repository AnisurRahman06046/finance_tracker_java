package com.capstone.financeTracker.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.financeTracker.entity.Transaction;
import com.capstone.financeTracker.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/{userId}")
    public Transaction createTransaction(@PathVariable Long userId,@RequestBody Transaction transaction){
        return transactionService.createTransaction(transaction, userId);
    }
    
}
