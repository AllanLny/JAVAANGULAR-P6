package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.Article;
import com.openclassrooms.mddapi.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByCreatedAtDesc();
    List<Article> findAllByOrderByCreatedAtAsc();

    @Query("SELECT a FROM Article a WHERE a.theme.id IN (SELECT t.id FROM Theme t JOIN t.subscribers s WHERE s = ?1)")
    List<Article> findByUserSubscriptions(User user, Sort sort);
}