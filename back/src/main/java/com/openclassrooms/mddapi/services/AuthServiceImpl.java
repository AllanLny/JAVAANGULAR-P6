package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.JwtResponse;
import com.openclassrooms.mddapi.dto.LoginDto;
import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.dto.UserRegistrationDto;
import com.openclassrooms.mddapi.exceptions.AuthenticationException;
import com.openclassrooms.mddapi.exceptions.EmailAlreadyExistsException;
import com.openclassrooms.mddapi.exceptions.UsernameAlreadyExistsException;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.security.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtToken jwtToken;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtToken jwtToken) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtToken = jwtToken;
    }

    @Override
    public UserDto register(UserRegistrationDto dto) {
        if (dto == null || dto.getEmail() == null || dto.getUsername() == null || dto.getPassword() == null) {
            throw new AuthenticationException("Tous les champs sont obligatoires");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email déjà utilisé");
        }
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UsernameAlreadyExistsException("Nom d'utilisateur déjà utilisé");
        }

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public JwtResponse login(LoginDto dto) {
        // Check for null dto or null fields
        if (dto == null || dto.getEmailOrUsername() == null || dto.getPassword() == null ||
                dto.getEmailOrUsername().trim().isEmpty() || dto.getPassword().trim().isEmpty()) {
            throw new AuthenticationException("Email/username et mot de passe sont obligatoires");
        }

        // Try to find user by email or username
        User user = userRepository.findByEmailOrUsername(dto.getEmailOrUsername(), dto.getEmailOrUsername())
                .orElseThrow(() -> new AuthenticationException("Identifiants invalides"));

        // Check password match
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new AuthenticationException("Identifiants invalides");
        }

        // Generate token
        String token = jwtToken.createToken(user);
        return new JwtResponse(token);
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Utilisateur non trouvé"));
    }
}