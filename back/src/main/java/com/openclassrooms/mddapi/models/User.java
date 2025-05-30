package com.openclassrooms.mddapi.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_theme_subscriptions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id")
    )
    private Set<Theme> subscribedThemes = new HashSet<>();

    // Existing getters and setters
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

    public Set<Theme> getSubscribedThemes() {
        return subscribedThemes;
    }

    public void setSubscribedThemes(Set<Theme> subscribedThemes) {
        this.subscribedThemes = subscribedThemes;
    }

    public void subscribeToTheme(Theme theme) {
        subscribedThemes.add(theme);
    }

    public void unsubscribeFromTheme(Theme theme) {
        subscribedThemes.remove(theme);
    }
}