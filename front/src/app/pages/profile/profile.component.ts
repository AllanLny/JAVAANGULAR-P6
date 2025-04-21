import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ThemeService, Subscription } from '../../services/theme.service';
import { AuthService, User } from '../../services/auth.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  profileForm!: FormGroup;
  subscriptions: Subscription[] = [];

  constructor(
    private fb: FormBuilder,
    private themeService: ThemeService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      newPassword: [''],
    });

    this.loadSubscriptions();
  }

  loadSubscriptions(): void {
    this.authService.getCurrentUser().subscribe({
      next: (user: User) => {
        this.subscriptions = user.subscribedThemes || [];
      },
      error: (err) => {
        console.error('Erreur lors du chargement des abonnements :', err);
      },
    });
  }

  saveProfile(): void {
    if (this.profileForm.valid) {
      const updatedUser = {
        username: this.profileForm.value.username,
        email: this.profileForm.value.email,
        password: this.profileForm.value.newPassword || undefined,
      };

      this.authService.updateCurrentUser(updatedUser).subscribe({
        next: (response: { token: string }) => {
          console.log('Profil mis à jour avec succès :', response);
          alert('Votre profil a été mis à jour avec succès.');
          localStorage.setItem('authToken', response.token);
        },
        error: (err: unknown) => {
          console.error('Erreur lors de la mise à jour du profil :', err);
        },
      });
    } else {
      alert('Veuillez remplir correctement le formulaire.');
    }
  }

  logout(): void {
    localStorage.removeItem('authToken');
    this.router.navigate(['/login']);
  }

  unsubscribe(themeId: number): void {
    this.themeService.unsubscribeFromTheme(themeId).subscribe({
      next: () => {
        this.subscriptions = this.subscriptions.filter(
          (theme) => theme.id !== themeId
        );
        console.log(`Désabonné du thème avec l'ID : ${themeId}`);
      },
      error: (err) => {
        console.error('Erreur lors de la désinscription du thème :', err);
      },
    });
  }
}