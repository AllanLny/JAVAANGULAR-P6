import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Article {
  id: number;
  title: string;
  content: string;
  createdAt: string;
  author: { username: string };
  theme?: { name: string };
  comments: { username: string; content: string }[]; 
}

export interface Comment {
  username: string;
  content: string;
}

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private baseUrl = '/api/articles';

  constructor(private http: HttpClient) {}

  getArticles(oldestFirst: boolean = false): Observable<Article[]> {
    const params = new HttpParams().set('oldestFirst', oldestFirst.toString());
    return this.http.get<Article[]>(`${this.baseUrl}/feed`, { params });
  }

  addArticle(article: { title: string; content: string; theme: string }): Observable<Article> {
    return this.http.post<Article>(`${this.baseUrl}`, article);
  }

  getArticleById(articleId: string): Observable<Article> {
    return this.http.get<Article>(`${this.baseUrl}/${articleId}`);
  }

  addComment(articleId: string, comment: { content: string }): Observable<Comment> {
    return this.http.post<Comment>(`${this.baseUrl}/${articleId}/comments`, comment);
  }

  getThemes(): Observable<{ id: number; name: string }[]> {
    return this.http.get<{ id: number; name: string }[]>(`/api/themes`);
  }
}