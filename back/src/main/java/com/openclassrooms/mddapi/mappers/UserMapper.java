package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.dto.ThemeDto;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class UserMapper {

    /**
     * Converts a User entity to a UserDto
     * @param user the user entity to convert
     * @return the corresponding UserDto
     */
        public static UserDto toDto(User user) {
            if (user == null) {
                return null;
            }

            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setEmail(user.getEmail());
            dto.setUsername(user.getUsername());

            // Map subscribed themes
            if (user.getSubscribedThemes() != null) {
                Set<ThemeDto> themeDtos = user.getSubscribedThemes().stream()
                        .map(theme -> {
                            ThemeDto themeDto = ThemeMapper.toDto(theme);
                            themeDto.setSubscribed(true);
                            return themeDto;
                        })
                        .collect(Collectors.toSet());
                dto.setSubscribedThemes(themeDtos);
            }

            return dto;
        }

    /**
     * Converts a UserDto to a User entity
     * @param dto the UserDto to convert
     * @return the corresponding User entity
     */
    public static User toEntity(UserDto dto) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(dto.getId());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        return user;
    }

    /**
     * Updates an existing User entity with data from a UserDto
     * @param user the existing User entity
     * @param dto the UserDto with new data
     * @return the updated User entity
     */
    public static User updateFromDto(User user, UserDto dto, PasswordEncoder passwordEncoder) {
        if (user == null || dto == null) {
            return user;
        }

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        return user;
    }
}