package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class UserDto {
    private Long id;
    private String email;
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Set<ThemeDto> subscribedThemes;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<ThemeDto> getSubscribedThemes() {
        return subscribedThemes;
    }

    public void setSubscribedThemes(Set<ThemeDto> subscribedThemes) {
        this.subscribedThemes = subscribedThemes;
    }
}