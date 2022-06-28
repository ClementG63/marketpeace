# MarketPeace API
**Créateur**: Clément GUYON

**Ecole**: Ynov Lyon Campus

**Formation**: Mastère Développement Logiciel, Mobile & IoT

**Module**: Web Service

**Groupe**: Travail réalisé **SEUL**.

MarketPeace API est une application Web développée en **Java 8** avec le Framework **Spring Boot** (version 2.7.0) permettant aux utilisateurs de récupérer des données depuis une base de données **MySQL**.

## BASE DE DONNEES

L'export de la base de données est disponible à la racine du répertoire sous le nom "marketpeacedb_export.sql"

## Application Front

L'application Flutter est disponible sur [ce GitHub](https://github.com/ClementG63/market_peace).

## Endpoints et controllers

### Advertisement controller
Permet de manipuler les annonces (*Advertisement*) de l'application.
**Endpoints disponibles et sécurité :**

 - **findAll** via "GET /api/advertisements/", permet de récupérer toutes les annonces.
 ***Sécurité***: Aucun contrôle d'accès afin que même les utilisateurs non-connectés puissent récupérer les annonces afin de la afficher.
 ***Retours possibles***: 200, même si vide.
 
 - **findAdById** via "GET /api/advertisements/{id}" avec {id} correspondant à l'identifiant de l'annonce recherchée. Permet de récupérer une annonce spécifique, par exemple pour accéder aux détails d'une annonce [non implémentée]. 
 ***Sécurité***: Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized / 404 NotFound

 - **save** via "PUT /api/advertisements/" avec un body contenant l'annonce a créer ou modifier. Permet de créer une nouvelle annonce ou de mettre à jour une annonce existante. 
 ***Sécurité***: Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized / 404 NotFound

 - **deleteAd** via "DELETE /api/advertisements/{id}" avec {id} correspondant à l'identifiant de l'annonce à supprimer. Permet de supprimer une annonce. 
 ***Sécurité***: Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized / 404 NotFound

### User controller

Permet de manipuler les utilisateurs (*Users*) de l'application.
**Endpoints disponibles et sécurité :**

 - **findUserById** via "GET /api/users/{id}" avec {id} correspondant à l'identifiant de l'utilisateur recherché. Permet de récupérer un utilisateur spécifique, par exemple pour accéder aux détails d'un utilisateur, afin d'afficher son profil [non implémentée]. 
 ***Sécurité***: Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized / 404 NotFound

 - **save** via "PUT /api/users/" avec un body contenant l'utilisateur a créer ou modifier. Permet de créer un nouvel utilisateur  ou de mettre à jour un utilisateur existant. 
 ***Sécurité***: Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized / 404 NotFound

 - **deleteUser** via "DELETE /api/users/{id}" avec {id} correspondant à l'identifiant de l'utilisateur à supprimer. Permet de supprimer un utilisateur. [***ndlr***: PAS UTILISABLE PAR LES UTILISATEURS NON-ADMIN.]
 ***Sécurité***: Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized / 404 NotFound / 403 Forbidden

### Auth controller

 - **signin** via "POST /api/auth/signin". Permet à un utilisateur de se connecter, en récupérant ses informations utilisateurs ainsi que son Bearer Token.
 ***Sécurité***: Pas de sécurité.
  ***Retours possibles***: 200 OK /  401 Unauthorized (Bad Credentials)

 - **signup** via "POST /api/auth/signup". Permet à un utilisateur de s'inscrire avec ses différentes informations dans le body.
 ***Sécurité***: Pas de sécurité.
  ***Retours possibles***: 200 OK /  401 Unauthorized

### Test controller

Permet de tester les accès par rôle.
 - **all** via "GET /api/test/all". Test l'accès public.
 ***Sécurité***: Pas de sécurité.
  ***Retours possibles***: 200 OK

 - **user** via "GET /api/test/user". Test l'accès USER et ADMIN.
 ***Sécurité***: Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized

 - **admin** via "GET /api/test/admin". Test l'accès ADMIN.
 ***Sécurité***:  Authentification par Bearer Token requise dans les headers de la requête.
  ***Retours possibles***: 200 OK / 401 Unauthorized

## Architecture du projet

Voici une image présentant l'architecture de l'application.
- Le client est développé en Flutter et - pour l'instant - testé sur Web et **Android**.
- La base de données d'images utilisée est **Firebase Cloud Storage**, elle sert uniquement à stocker les images des annonces postées par les utilisateurs.
- L'API est développée en **Spring Boot** avec **Java 8**, elle permet l'accès aux ressources stockées dans la base de données. Certains sécurités ont été mises en place, comme le contrôle d'accès par Bearer Token (JWT) ainsi que via des rôles d'utilisateurs (USER/ADMIN).
- La base de données est une base MySQL avec 4 tables, (Advertisements, Roles, User_roles et Users)


![enter image description here](https://media.discordapp.net/attachments/786644865437270038/990268949176385566/unknown.png)

## Schema de la base de données

![enter image description here](https://media.discordapp.net/attachments/786644865437270038/990272869634285568/unknown.png)
