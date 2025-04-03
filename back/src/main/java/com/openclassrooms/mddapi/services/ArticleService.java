package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.ArticleCreateDto;
import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.exceptions.ResourceNotFoundException;
import com.openclassrooms.mddapi.mappers.ArticleMapper;
import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.Theme;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;
    private final AuthService authService;

    @Autowired
    public ArticleService(
            ArticleRepository articleRepository,
            ThemeRepository themeRepository,
            AuthService authService) {
        this.articleRepository = articleRepository;
        this.themeRepository = themeRepository;
        this.authService = authService;
    }

    public List<ArticleDto> getFeed(boolean oldestFirst) {
        User currentUser = authService.getCurrentUser();
        Sort sort = oldestFirst
                ? Sort.by(Sort.Direction.ASC, "createdAt")
                : Sort.by(Sort.Direction.DESC, "createdAt");

        List<Article> articles = articleRepository.findByUserSubscriptions(currentUser, sort);

        return articles.stream()
                .map(ArticleMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ArticleDto> getAllArticles(boolean oldestFirst) {
        List<Article> articles;
        if (oldestFirst) {
            articles = articleRepository.findAllByOrderByCreatedAtAsc();
        } else {
            articles = articleRepository.findAllByOrderByCreatedAtDesc();
        }

        return articles.stream()
                .map(ArticleMapper::toDto)
                .collect(Collectors.toList());
    }

    public ArticleDto getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));

        return ArticleMapper.toDto(article);
    }

    public ArticleDto createArticle(ArticleCreateDto createDto) {
        User currentUser = authService.getCurrentUser();
        Theme theme = themeRepository.findById(createDto.getThemeId())
                .orElseThrow(() -> new ResourceNotFoundException("Theme not found"));

        Article article = new Article();
        article.setTitle(createDto.getTitle());
        article.setContent(createDto.getContent());
        article.setCreatedAt(LocalDateTime.now());
        article.setTheme(theme);
        article.setAuthor(currentUser);

        Article savedArticle = articleRepository.save(article);
        return ArticleMapper.toDto(savedArticle);
    }
}