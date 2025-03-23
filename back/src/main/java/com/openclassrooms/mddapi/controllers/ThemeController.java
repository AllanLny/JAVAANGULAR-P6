package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ThemeCreateDto;
import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.services.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/themes")
public class ThemeController {
    private final ThemeService themeService;

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @GetMapping
    public ResponseEntity<List<ThemeDto>> getAllThemes() {
        return ResponseEntity.ok(themeService.getAllThemes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThemeDto> getThemeById(@PathVariable Long id) {
        return ResponseEntity.ok(themeService.getThemeById(id));
    }

    @PostMapping("/{id}/subscribe")
    public ResponseEntity<ThemeDto> subscribeToTheme(@PathVariable Long id) {
        return ResponseEntity.ok(themeService.subscribeToTheme(id));
    }

    @DeleteMapping("/{id}/subscribe")
    public ResponseEntity<ThemeDto> unsubscribeFromTheme(@PathVariable Long id) {
        return ResponseEntity.ok(themeService.unsubscribeFromTheme(id));
    }

    @PostMapping
    public ResponseEntity<ThemeDto> createTheme(@Valid @RequestBody ThemeCreateDto createDto) {
        return new ResponseEntity<>(themeService.createTheme(createDto), HttpStatus.CREATED);
    }
}