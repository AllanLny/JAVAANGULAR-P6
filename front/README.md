# Frontend - Application MDD

Ce projet est le frontend de l'application **MDD**, développé avec Angular. Il permet aux utilisateurs de se connecter, de s'inscrire, de gérer leur profil, de consulter des articles, de créer des articles, et de s'abonner à des thèmes.

---

## **Prérequis**

Avant de commencer, assurez-vous d'avoir les outils suivants installés sur votre machine :

- [Angular CLI](https://angular.io/cli) (version 14 ou supérieure)

---

## **Installation**

1. Clonez le dépôt :

   ```bash
   git clone https://github.com/AllanLny/JAVAANGULAR-P6/tree/main/front
   cd front
   ```

2. Installez les dépendances :

    ```npm install
     ```

3. Configurez le proxy pour les appels API :

Le fichier proxy.conf.json est déjà configuré pour rediriger les appels vers http://localhost:8080.

## **Lancer le projet**

Pour démarrer le projet en mode développement, exécutez la commande suivante :

``` 
ng serve 
```

L'application sera accessible à l'adresse suivante : http://localhost:4200.

## **Fonctionnalités**

# Pages principales
- Accueil : Page d'accueil avec des boutons pour se connecter ou s'inscrire.
- Connexion : Permet aux utilisateurs de se connecter avec leur email ou nom d'utilisateur.
- Inscription : Permet aux nouveaux utilisateurs de créer un compte.
- Articles : Liste des articles disponibles, avec tri par date.
- Détail d'un article : Affiche le contenu d'un article et permet d'ajouter des commentaires.
- Créer un article : Permet aux utilisateurs connectés de créer un nouvel article.
- Thèmes : Liste des thèmes disponibles, avec possibilité de s'abonner.
- Profil : Permet de modifier les informations utilisateur et de gérer les abonnements.
#Gestion des utilisateurs
- Connexion et déconnexion.
- Inscription avec validation des champs.
- Mise à jour du profil utilisateur (nom d'utilisateur, email, mot de passe).
#Gestion des articles
- Consultation des articles.
- Création d'articles avec sélection d'un thème.
#Gestion des thèmes
- Affichage des thèmes disponibles.
- Abonnement et désabonnement aux thèmes.

## **Technologies utilisées**

- Framework : Angular 14
- Langage : TypeScript
- Gestion des styles : SCSS

## **Auteur**

Allan 