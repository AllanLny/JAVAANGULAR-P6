package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.UserDto;
import com.openclassrooms.mddapi.models.User;
import org.springframework.stereotype.Component;

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
        // Password intentionally excluded for security
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
    public static User updateFromDto(User user, UserDto dto) {
        if (user == null || dto == null) {
            return user;
        }

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        return user;
    }
}