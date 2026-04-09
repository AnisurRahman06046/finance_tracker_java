package com.capstone.financeTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.financeTracker.entity.User;




public interface UserRepository extends  JpaRepository<User, Long> {
    
}
