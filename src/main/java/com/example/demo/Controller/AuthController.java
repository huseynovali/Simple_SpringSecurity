package com.example.demo.Controller;

import com.example.demo.dto.request.LoginDto;
import com.example.demo.dto.request.UserRequestDto;
import com.example.demo.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @GetMapping
    public void getTest(){

        System.out.println("TEST");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserRequestDto userRequestDto) {
        authService.register(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Kullanıcı başarıyla kaydedildi.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        authService.login(loginDto);
        return ResponseEntity.status(HttpStatus.OK).body("Başarılı giriş.");

    }
}
