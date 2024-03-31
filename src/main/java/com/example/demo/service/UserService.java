package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.module.User;
import com.example.demo.repos.UserRepo;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addNewUser(UserRequestDto newuser) {
     userRepo.save(newuser);
    }




}
