import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ArticleService } from '../../services/article.service';


export interface Article {
  id: number;
  title: string;
  content: string;
  createdAt: string;
  author: { username: string };
  theme?: { name: string };
  comments: { username: string; content: string }[]; // Ajout de `comments`
}

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss']
})
export class ArticleDetailComponent implements OnInit {
  article: Article | null = null;
  commentForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    const articleId = this.route.snapshot.paramMap.get('id');
    if (articleId) {
      this.loadArticle(articleId);
    }

    this.commentForm = this.fb.group({
      content: ['', [Validators.required, Validators.minLength(1)]],
    });
  }

  loadArticle(articleId: string): void {
    this.articleService.getArticleById(articleId).subscribe({
      next: (data) => {
        this.article = data;
      },
      error: (err) => {
        console.error('Error loading article:', err);
      },
    });
  }

  addComment(): void {
    if (this.commentForm.valid) {
      const articleId = this.route.snapshot.paramMap.get('id');
      if (articleId) {
        this.articleService.addComment(articleId, this.commentForm.value).subscribe({
          next: (comment) => {
            if (this.article) {
              this.article.comments.push(comment);
            }
            this.commentForm.reset();
          },
          error: (err) => {
            console.error('Error adding comment:', err);
          },
        });
      }
    }
  }
}