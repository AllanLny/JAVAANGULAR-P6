package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.services.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/me")
public class MeController {

    private final MeService meService;

    @Autowired
    public MeController(MeService meService) {
        this.meService = meService;
    }

    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser() {
        return ResponseEntity.ok(meService.getCurrentUserInfo());
    }

    @PutMapping
    public ResponseEntity<UserDto> updateCurrentUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(meService.updateCurrentUser(userDto));
    }
}