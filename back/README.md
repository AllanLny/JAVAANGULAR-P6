# Projet FullStack BackEnd

Ce projet est le backend d'une application FullStack développée avec Spring Boot. Il fournit une API REST pour gérer les utilisateurs, les articles, les commentaires et les thèmes.

## Table des matières

- [Prérequis](#prérequis)
- [Installation](#installation)
- [Configuration](#configuration)
- [Lancement du projet](#lancement-du-projet)
- [Endpoints disponibles](#endpoints-disponibles)
- [Technologies utilisées](#technologies-utilisées)
---

## Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre machine :

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)

---

## Installation

1. Clonez ce dépôt sur votre machine locale :

   ```bash
   git clone https://github.com/AllanLny/JAVAANGULAR-P6/tree/main/back
   ```

2. Installez les dépendances Maven :

   ```bash
   ./mvnw clean install
   ```

## Installation

1. Configurez la base de données MySQL dans le fichier src/main/resources/application.properties 
2. Configurez les propriétés JWT dans le même fichier :

   ```bash
    jwt.secret=VotreSecretJWT
    jwt.expiration=86400000
   ```

3. Assurez-vous que la base de données p6db existe dans votre instance MySQL :

## Lancement du projet

1. Lancez l'application Spring Boot :

```bash
        ./mvnw spring-boot:run
```

2.L'API sera disponible à l'adresse suivante :

```bash
http://localhost:8080
```

## Endpoints disponibles

Authentification
POST /api/auth/register : Inscription d'un utilisateur.
POST /api/auth/login : Connexion d'un utilisateur.
Utilisateur
GET /api/me : Récupérer les informations de l'utilisateur connecté.
PUT /api/me : Mettre à jour les informations de l'utilisateur connecté (email, username, mot de passe).
Articles
GET /api/articles/feed : Récupérer les articles des thèmes auxquels l'utilisateur est abonné.
GET /api/articles/all : Récupérer tous les articles.
GET /api/articles/{id} : Récupérer un article par ID.
POST /api/articles : Créer un nouvel article.
Commentaires
POST /api/articles/{articleId}/comments : Ajouter un commentaire à un article.
Thèmes
GET /api/themes : Récupérer tous les thèmes.
GET /api/themes/{id} : Récupérer un thème par ID.
POST /api/themes/{id}/subscribe : S'abonner à un thème.
DELETE /api/themes/{id}/subscribe : Se désabonner d'un thème.
POST /api/themes : Créer un nouveau thème.

## Technologies utilisées
Java 11
Spring Boot 2.7.3
Spring Data JPA
Spring Security
Spring Validation
MySQL
JWT (JSON Web Tokens) pour l'authentification
Lombok pour réduire le code boilerplate
SpringDoc OpenAPI pour la documentation de l'API

## Documentation de l'API

La documentation de l'API est disponible via Swagger UI à l'adresse suivante :


## Auteur
Allan 