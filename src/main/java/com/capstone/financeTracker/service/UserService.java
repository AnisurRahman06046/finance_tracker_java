package com.capstone.financeTracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capstone.financeTracker.entity.User;
import com.capstone.financeTracker.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User createUser(User user){
        final User savedUser = userRepo.save(user);
        return savedUser;
    }

    public List<User> getUsers(){
        List<User> users = userRepo.findAll();
        return users;
    }
}
