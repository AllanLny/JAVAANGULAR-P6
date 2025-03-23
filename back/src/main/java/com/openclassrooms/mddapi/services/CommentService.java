package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.CommentCreateDto;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final AuthService authService;

    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            ArticleRepository articleRepository,
            AuthService authService) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.authService = authService;
    }

    public CommentDto addComment(Long articleId, CommentCreateDto createDto) {
        User currentUser = authService.getCurrentUser();
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));

        Comment comment = new Comment();
        comment.setContent(createDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setAuthor(currentUser);
        comment.setArticle(article);

        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toDto(savedComment);
    }
}