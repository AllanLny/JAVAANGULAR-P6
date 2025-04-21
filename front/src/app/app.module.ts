import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ReactiveFormsModule } from '@angular/forms'; 
import { LoginComponent } from './pages/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './pages/register/register.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { HeaderComponent } from './components/header/header.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './services/auth.interceptor';
import { CreateArticleComponent } from './pages/create-article/create-article.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ArticleDetailComponent } from './pages/article-detail/article-detail.component';
import { ProfileComponent } from './pages/profile/profile.component';


@NgModule({
  declarations: [AppComponent, HomeComponent, LoginComponent, RegisterComponent, ArticlesComponent, HeaderComponent, CreateArticleComponent, ThemesComponent, ArticleDetailComponent, ProfileComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    FontAwesomeModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}