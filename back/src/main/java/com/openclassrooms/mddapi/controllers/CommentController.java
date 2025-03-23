package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.CommentCreateDto;
import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/articles/{articleId}/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> addComment(
            @PathVariable Long articleId,
            @Valid @RequestBody CommentCreateDto createDto) {
        return new ResponseEntity<>(commentService.addComment(articleId, createDto), HttpStatus.CREATED);
    }
}