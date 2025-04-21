package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ThemeCreateDto;
import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.mappers.ThemeMapper;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final AuthService authService;
    private final UserRepository userRepository;
    

    @Autowired
    public ThemeService(ThemeRepository themeRepository, AuthService authService, UserRepository userRepository) {
        this.themeRepository = themeRepository;
        this.authService = authService;
        this.userRepository = userRepository;
    }


    public List<ThemeDto> getAllThemes() {
        User currentUser = authService.getCurrentUser();
        List<Theme> themes = themeRepository.findAll();

        return themes.stream()
                .map(theme -> {
                    ThemeDto dto = ThemeMapper.toDto(theme);
                    dto.setSubscribed(currentUser.getSubscribedThemes().contains(theme));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public ThemeDto getThemeById(Long id) {
        User currentUser = authService.getCurrentUser();
        Theme theme = themeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Theme not found"));

        ThemeDto dto = ThemeMapper.toDto(theme);
        dto.setSubscribed(currentUser.getSubscribedThemes().contains(theme));
        return dto;
    }

    public ThemeDto subscribeToTheme(Long themeId) {
        User currentUser = authService.getCurrentUser();
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new ResourceNotFoundException("Theme not found"));

        currentUser.subscribeToTheme(theme);

        ThemeDto dto = ThemeMapper.toDto(theme);
        dto.setSubscribed(true);
        return dto;
    }

    public ThemeDto unsubscribeFromTheme(Long themeId) {
        User currentUser = authService.getCurrentUser();
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new ResourceNotFoundException("Theme not found"));
    
        currentUser.unsubscribeFromTheme(theme);
        userRepository.save(currentUser);
    
        ThemeDto dto = ThemeMapper.toDto(theme);
        dto.setSubscribed(false);
        return dto;
    }

    public ThemeDto createTheme(ThemeCreateDto createDto) {
        Theme theme = new Theme();
        theme.setName(createDto.getName());
        theme.setDescription(createDto.getDescription());

        Theme savedTheme = themeRepository.save(theme);
        return ThemeMapper.toDto(savedTheme);
    }
}