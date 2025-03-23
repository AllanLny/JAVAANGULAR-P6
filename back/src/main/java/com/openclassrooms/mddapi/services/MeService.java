package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MeService {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MeService(AuthService authService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto getCurrentUserInfo() {
        User currentUser = authService.getCurrentUser();
        return UserMapper.toDto(currentUser);
    }

    public UserDto updateCurrentUser(UserDto userDto) {
        User currentUser = authService.getCurrentUser();

        User updatedUser = UserMapper.updateFromDto(currentUser, userDto, passwordEncoder);

        return UserMapper.toDto(userRepository.save(updatedUser));
    }
}