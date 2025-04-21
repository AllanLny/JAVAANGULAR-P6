import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Theme {
  id: number;
  name: string;
  description: string;
  subscribed?: boolean;
}

export interface Subscription {
  id: number;
  name: string;
  description: string;
}

@Injectable({
  providedIn: 'root'
})

export class ThemeService {
  private baseUrl = '/api/themes';

  constructor(private http: HttpClient) {}

  getThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.baseUrl);
  }

  subscribeToTheme(themeId: number): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/${themeId}/subscribe`, {});
  }

  unsubscribeFromTheme(themeId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${themeId}/subscribe`);
  }
}