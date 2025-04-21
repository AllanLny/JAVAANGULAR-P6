import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subscription } from './theme.service';

export interface User {
  id: number;
  username: string;
  email: string;
  subscribedThemes: Subscription[];
}

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = '/api/auth';

  constructor(private http: HttpClient) {}

  login(credentials: { username: string; password: string }): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.apiUrl}/login`, credentials);
  }

  register(data: { email: string; username: string; password: string }): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/register`, data);
  }

  getCurrentUser(): Observable<User> {
    return this.http.get<User>('/api/me');
  }

  updateCurrentUser(user: { username: string; email: string; password?: string }): Observable<{ token: string }> {
    return this.http.put<{ token: string }>('/api/me', user);
  }
}