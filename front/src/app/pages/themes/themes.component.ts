import { Component, OnInit } from '@angular/core';
import { ThemeService, Theme } from '../../services/theme.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss'],
})
export class ThemesComponent implements OnInit {
  themes: Theme[] = [];

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.loadThemes();
  }

  loadThemes(): void {
    this.themeService.getThemes().subscribe({
      next: (data: Theme[]) => {
        this.themes = data;
      },
      error: (err: unknown) => {
        console.error('Error loading themes:', err);
      },
    });
  }

  subscribeToTheme(themeId: number): void {
    this.themeService.subscribeToTheme(themeId).subscribe({
      next: () => {
        console.log(`Subscribed to theme with ID: ${themeId}`);
        const theme = this.themes.find((t) => t.id === themeId);
        if (theme) {
          theme.subscribed = true;
        }
      },
      error: (err: unknown) => {
        console.error('Error subscribing to theme:', err);
      },
    });
  }
}