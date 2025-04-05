import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'; // Ajoutez HttpParams ici
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {
  private baseUrl = '/api/articles'; 

  constructor(private http: HttpClient) {}

  getArticles(oldestFirst: boolean = false): Observable<any> {
    const params = new HttpParams().set('oldestFirst', oldestFirst.toString());
    return this.http.get(`${this.baseUrl}/all`, { params });
  }

  addArticle(article: { title: string; content: string; theme: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}`, article);
  }

  getArticleById(articleId: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${articleId}`);
  }

  addComment(articleId: string, comment: { content: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/${articleId}/comments`, comment);
  }
}