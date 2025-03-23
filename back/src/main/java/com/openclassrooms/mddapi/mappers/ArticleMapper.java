package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.models.Article;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArticleMapper {
    public static ArticleDto toDto(Article article) {
        if (article == null) {
            return null;
        }

        ArticleDto dto = new ArticleDto();
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        dto.setCreatedAt(article.getCreatedAt());
        dto.setTheme(ThemeMapper.toDto(article.getTheme()));
        dto.setAuthor(UserMapper.toDto(article.getAuthor()));

        if (article.getComments() != null) {
            dto.setComments(article.getComments().stream()
                    .map(CommentMapper::toDto)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}