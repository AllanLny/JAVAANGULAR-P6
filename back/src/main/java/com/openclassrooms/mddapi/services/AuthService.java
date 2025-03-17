package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.LoginDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegistrationDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.dto.JwtResponse;

public interface AuthService {
    UserDto register(UserRegistrationDto registrationDto);
    JwtResponse login(LoginDto loginDto);
    User getCurrentUser();
}
