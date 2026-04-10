package com.capstone.financeTracker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
 
    @Transactional
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

        DailySummary summary = dailySummaryRepo.findByUserAndDate(user, data.getDate())
            .orElseGet(() -> {
                DailySummary newSummary = new DailySummary();
                newSummary.setUser(user);
                newSummary.setDate(data.getDate());
                newSummary.setTotalIncome(0.0);
                newSummary.setTotalExpense(0.0);
                newSummary.setBalance(0.0);
                // Version is handled automatically by Hibernate
                return newSummary;
            });

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




    public Page<TransactionResponse> getTransactions(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        return transactionRepo.findAll(pageable)
        .map(t-> new TransactionResponse(          
                    t.getId(),
                    t.getAmount(),
                    t.getType(),
                    t.getDate(),
                    t.getDescription()));
            
    }
}
