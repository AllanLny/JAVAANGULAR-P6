package com.openclassrooms.mddapi.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}