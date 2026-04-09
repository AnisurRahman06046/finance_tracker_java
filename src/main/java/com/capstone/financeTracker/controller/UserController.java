package com.capstone.financeTracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.financeTracker.entity.User;
import com.capstone.financeTracker.service.UserService;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        System.out.println("Received user: " + user.getName() + ", " + user.getEmail());
        return userService.createUser(user);
    }
    
    @GetMapping("/list")
    public List<User> users(){
        return userService.getUsers();
    }
}
