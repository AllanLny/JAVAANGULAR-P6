package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.models.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public static CommentDto toDto(Comment comment) {
        if (comment == null) {
            return null;
        }

        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setAuthor(UserMapper.toDto(comment.getAuthor()));
        dto.setArticleId(comment.getArticle().getId());

        return dto;
    }
}