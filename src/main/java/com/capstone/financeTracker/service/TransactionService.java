package com.capstone.financeTracker.service;

import org.springframework.stereotype.Service;

import com.capstone.financeTracker.entity.Transaction;
import com.capstone.financeTracker.entity.User;
import com.capstone.financeTracker.repository.TransactionRepository;
import com.capstone.financeTracker.repository.UserRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;
    private final UserRepository userRepo;

    public TransactionService(TransactionRepository transactionRepo, UserRepository userRepo){
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
    }

    public Transaction createTransaction(Transaction transaction, Long userId){
        User user = userRepo.findById(userId)
            .orElseThrow(()-> new RuntimeException("User not found"));
        
        transaction.setUser(user);
        return  transactionRepo.save(transaction);
    }
}
