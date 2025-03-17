package com.openclassrooms.mddapi.dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {
    @NotBlank(message = "Email ou nom d'utilisateur est obligatoire")
    private String emailOrUsername;

    @NotBlank(message = "Mot de passe est obligatoire")
    private String password;

    // Getters and setters
    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}