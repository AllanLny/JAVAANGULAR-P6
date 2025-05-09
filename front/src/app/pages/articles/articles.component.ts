import { Component, OnInit } from '@angular/core';
import { ArticleService, Article } from '../../services/article.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss']
})
export class ArticlesComponent implements OnInit {
  articles: Article[] = [];
  sortOrder: 'date-desc' | 'date-asc' = 'date-desc';

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  loadArticles(): void {
    const oldestFirst = this.sortOrder === 'date-asc';
    this.articleService.getArticles(oldestFirst).subscribe({
      next: (data: Article[]) => {
        this.articles = data;
      },
      error: (err: unknown) => {
        console.error('Error loading articles:', err);
      }
    });
  }

  toggleSortOrder(): void {
    this.sortOrder = this.sortOrder === 'date-desc' ? 'date-asc' : 'date-desc';
    this.loadArticles();
  }
}