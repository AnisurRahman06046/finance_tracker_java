package com.capstone.financeTracker.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="dailySummary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DailySummary {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Double totalIncome=0.0;
    private Double totalExpense=0.0;
    private Double balance = 0.0;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}
