package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailOrUsername(String email, String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}