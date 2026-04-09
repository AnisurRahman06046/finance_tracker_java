package com.capstone.financeTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.financeTracker.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
}
