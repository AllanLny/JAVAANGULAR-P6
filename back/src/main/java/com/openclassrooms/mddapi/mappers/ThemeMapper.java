package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.ThemeDto;
import com.openclassrooms.mddapi.models.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper {
    public static ThemeDto toDto(Theme theme) {
        if (theme == null) {
            return null;
        }

        ThemeDto dto = new ThemeDto();
        dto.setId(theme.getId());
        dto.setName(theme.getName());
        dto.setDescription(theme.getDescription());
        return dto;
    }
}