package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.ArticleCreateDto;
import com.openclassrooms.mddapi.dto.ArticleDto;
import com.openclassrooms.mddapi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/feed")
    public ResponseEntity<List<ArticleDto>> getFeed(
            @RequestParam(defaultValue = "false") boolean oldestFirst) {
        return ResponseEntity.ok(articleService.getFeed(oldestFirst));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArticleDto>> getAllArticles(
            @RequestParam(defaultValue = "false") boolean oldestFirst) {
        return ResponseEntity.ok(articleService.getAllArticles(oldestFirst));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @PostMapping
    public ResponseEntity<ArticleDto> createArticle(@Valid @RequestBody ArticleCreateDto createDto) {
        return new ResponseEntity<>(articleService.createArticle(createDto), HttpStatus.CREATED);
    }
}