package com.capstone.financeTracker.service;

import org.springframework.stereotype.Service;

import com.capstone.financeTracker.dto.TransactionRequestDTO;
import com.capstone.financeTracker.dto.TransactionResponse;
import com.capstone.financeTracker.entity.DailySummary;
import com.capstone.financeTracker.entity.Transaction;
import com.capstone.financeTracker.entity.User;
import com.capstone.financeTracker.repository.DailySummaryRepository;
import com.capstone.financeTracker.repository.TransactionRepository;
import com.capstone.financeTracker.repository.UserRepository;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepo;
    private final UserRepository userRepo;
    private final DailySummaryRepository dailySummaryRepo;

    public TransactionService(TransactionRepository transactionRepo, UserRepository userRepo, DailySummaryRepository dailySummaryRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.dailySummaryRepo = dailySummaryRepo;
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

        DailySummary summary = dailySummaryRepo.findByUserAndDate(user,data.getDate())
            .orElse(new DailySummary(null, 0.0, 0.0, 0.0, data.getDate(), user));

        if(data.getType().equals("INCOME")){
            summary.setTotalIncome(summary.getTotalIncome()+data.getAmount());
        }
        else{
            summary.setTotalExpense(summary.getTotalExpense()+data.getAmount());
        }

        summary.setBalance(summary.getTotalIncome()-summary.getTotalExpense());
        dailySummaryRepo.save(summary);

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
