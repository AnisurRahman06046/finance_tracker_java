package com.capstone.financeTracker.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.financeTracker.entity.DailySummary;
import com.capstone.financeTracker.entity.User;

@Repository
public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {

    Optional<DailySummary> findByUserAndDate(User user, LocalDate date);
    
} 
