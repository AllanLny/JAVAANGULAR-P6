import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ArticleService } from '../../services/article.service';
import { Theme } from '../../services/theme.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})

export class CreateArticleComponent implements OnInit {
  createArticleForm!: FormGroup;
  themes: Theme[] = []; 
  
  
  constructor(
    private fb: FormBuilder,
    private articleService: ArticleService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.createArticleForm = this.fb.group({
      themeId: [null, [Validators.required]],
      title: ['', [Validators.required]],
      content: ['', [Validators.required, Validators.minLength(1)]],
    });
  
    this.loadThemes();
  }
  
  validateThemeSelection(control: AbstractControl): { [key: string]: boolean } | null {
    if (control.value === null || control.value === '') {
      return { invalidTheme: true };
    }
    return null;
  }

  loadThemes(): void {
    this.articleService.getThemes().subscribe({
      next: (data: { id: number; name: string }[]) => {
        this.themes = data.map(theme => ({
          ...theme,
          description: '',
        }));
      },
      error: (err: unknown) => {
        console.error('Error loading themes:', err);
      },
    });
  }

  onSubmit(): void {
    if (this.createArticleForm.valid) {
      this.articleService.addArticle(this.createArticleForm.value).subscribe({
        next: () => {
          console.log('Article created successfully');
          this.router.navigate(['/articles']);
        },
        error: (err: unknown) => {
          console.error('Error creating article:', err);
        },
      });
    }
  }
}