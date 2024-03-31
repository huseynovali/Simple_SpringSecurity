package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.module.User;
import com.example.demo.repos.UserRepo;
import org.springframework.core.SpringVersion;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;
    private UserMapper mapstructMapper;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addNewUser(UserRequestDto newuser) {
        User user = mapstructMapper.dtoToUser(newuser);
        userRepo.save(user);
    }



}
