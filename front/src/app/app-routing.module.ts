import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { AuthGuard } from './services/auth.guard';
import { CreateArticleComponent } from './pages/create-article/create-article.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ArticleDetailComponent } from './pages/article-detail/article-detail.component';
import { ProfileComponent } from './pages/profile/profile.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'articles', component: ArticlesComponent, canActivate: [AuthGuard] },
  { path: 'articles/create', component: CreateArticleComponent, canActivate: [AuthGuard] },
  { path: 'articles/:id', component: ArticleDetailComponent, canActivate: [AuthGuard] },
  { path: 'themes', component: ThemesComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}