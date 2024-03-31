package com.example.demo.service;

import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.module.User;
import com.example.demo.repos.UserRepo;

import java.util.Optional;

public class AuthenticationService {

    private UserRepo userRepo;
    private UserMapper mapstructMapper;

    public void register(UserRequestDto userRequestDto) {
        Optional<User> user = userRepo.findByEmail(userRequestDto.getEmail());
        if (user.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User newUser = mapstructMapper.dtoToUser(userRequestDto);
        userRepo.save(newUser);
    }

    public void login(UserRequestDto userRequestDto) {
        Optional<User> user = userRepo.findByEmail(userRequestDto.getEmail());
        if (!user.isPresent()) {
            throw new RuntimeException("User does not exist");
        }
        if (!user.get().getPassword().equals(userRequestDto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

    }


}
