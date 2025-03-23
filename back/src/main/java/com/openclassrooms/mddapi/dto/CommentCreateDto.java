package com.openclassrooms.mddapi.dto;

import javax.validation.constraints.NotBlank;

public class CommentCreateDto {
    @NotBlank(message = "Content is required")
    private String content;

    // Getter and setter
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}