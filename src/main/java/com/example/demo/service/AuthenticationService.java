package com.example.demo.service;

import com.example.demo.dto.request.LoginDto;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.dto.response.UserResponseDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.module.User;
import com.example.demo.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final UserMapper mapstructMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto register(UserRequestDto userRequestDto) {
        Optional<User> existingUser = userRepo.findByEmail(userRequestDto.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User newUser = mapstructMapper.dtoToUser(userRequestDto);
        newUser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepo.save(newUser);
        return mapstructMapper.userToDto(newUser);
    }

    public UserResponseDto login(LoginDto loginDto) {
        Optional<User> user = userRepo.findByEmail(loginDto.getEmail());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return  mapstructMapper.userToDto(user.get());
    }

}
