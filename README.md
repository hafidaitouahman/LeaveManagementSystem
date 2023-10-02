# Gestion des Absences - Projet

## Introduction

Ce projet est une application de gestion des absences qui permet de suivre et de gérer les absences des employés dans une entreprise. Il offre des fonctionnalités pour enregistrer les absences, suivre les demandes, et bien plus encore.

## Technologies Utilisées

- **Java**: Le langage de programmation principal pour le développement côté serveur. (Version Java 17)
- **Spring Boot**: Le framework utilisé pour créer l'application, offrant l'injection de dépendances, une architecture MVC, et bien plus encore. (Version Spring Boot 3.0.6)
- **Spring Data JPA**: Simplifie la manipulation des données en fournissant une implémentation JPA pour l'accès aux données.
- **MySQL**: Le système de gestion de base de données relationnelle utilisé pour le stockage des données.
- **Angular**: Le framework JavaScript utilisé pour le développement côté client. (Version Angular 16.1.0)
- **JSON Web Token (JWT)**: Pour l'authentification sécurisée et l'autorisation des utilisateurs.
- **Maven**: L'outil de construction utilisé pour gérer les dépendances du projet et l'emballage.
- **Git**: Le système de contrôle de version pour le développement collaboratif.

## Fonctionnalités

### Gestion des Employés :

- Les employés peuvent s'inscrire avec leur nom ,adresse e-mail et un mot de passe.
- Les employés existants peuvent s'authentifier en utilisant leurs informations d'identification enregistrées.

### Gestion des Absences :

- Les employés peuvent enregistrer leurs absences en spécifiant la date et le motif.
- Les gestionnaires peuvent approuver ou rejeter les demandes d'absence soumises par les employés.
- Les employés peuvent consulter l'état de leurs demandes d'absence.

### Gestion de departement :

- Les gestionnaires peuvent générer les departements d'organisme.

### Gestion de site :

- Les gestionnaires peuvent générer les sites d'organisme.

### Gestion d'equipe :

- Les gestionnaires peuvent générer les equipes d'organisme.

### Gestion de type de congé :

- Les gestionnaires peuvent générer les types de congé.

### Gestion de Quota :

- Les gestionnaires peuvent générer les quotas d'organisme.
- La quota se regenere automatiquement le premier jour de chaque debut d'annee.

### Sécurité avec JWT :

- L'application utilise JSON Web Tokens (JWT) pour l'authentification et l'autorisation sécurisées des utilisateurs.
- Les utilisateurs reçoivent un JWT après une authentification réussie, qu'ils utilisent pour les appels API ultérieurs.
- Les JWT sont validés pour s'assurer que seuls les utilisateurs autorisés peuvent accéder à des ressources spécifiques.

## Configuration et Installation

### Cloner le Répertoire :

```bash
git clone https://gitlab.com/salmamouayad/myapp.git
cd myapp
