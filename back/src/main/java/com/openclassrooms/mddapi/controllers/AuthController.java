package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.JwtResponse;
import com.openclassrooms.mddapi.dto.LoginDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegistrationDto;
import com.openclassrooms.mddapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegistrationDto registrationDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(registrationDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }
}