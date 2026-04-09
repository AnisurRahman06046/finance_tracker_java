package com.capstone.financeTracker.service;

import org.springframework.stereotype.Service;

import com.capstone.financeTracker.dto.TransactionRequestDTO;
import com.capstone.financeTracker.dto.TransactionResponse;
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

    // public Transaction createTransaction(Transaction transaction, Long userId){
    //     User user = userRepo.findById(userId)
    //         .orElseThrow(()-> new RuntimeException("User not found"));
        
    //     transaction.setUser(user);
    //     return  transactionRepo.save(transaction);
    // }

    public TransactionResponse createTransaction(Long userId, TransactionRequestDTO data){
        if(!data.getType().equals("INCOME") && !data.getType().equals("EXPENSE")){
            throw new RuntimeException("Invalid transaction type");
        }
        User user = userRepo.findById(userId)
            .orElseThrow(()-> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();

        transaction.setAmount(data.getAmount());
        transaction.setType(data.getType());
        transaction.setDescription(data.getDescription());
        transaction.setDate(data.getDate());
        transaction.setUser(user);

        Transaction savedRes = transactionRepo.save(transaction);
        return new TransactionResponse(
            savedRes.getId(),
            savedRes.getAmount(),
            savedRes.getType(),
            savedRes.getDate(), 
            savedRes.getDescription()
        );
    }
}
