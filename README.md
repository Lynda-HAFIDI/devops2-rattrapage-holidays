# DevOps2 Rattrapage - API jours fériés

## Vue d'ensemble

Le projet est une petite API REST développée avec Spring Boot.

Il permet de récupérer le prochain jour férié en France à partir d'une API REST distante.

Le projet couvre aujourd'hui :

* l'appel d'une API REST externe sans authentification ;
* la récupération des jours fériés français via Nager.Date ;
* un traitement métier pour trouver le prochain jour férié ;
* le calcul du nombre de jours restants ;
* l'exposition du résultat via une route REST locale ;
* une architecture simple en controller, service et DTO ;
* un test unitaire de la logique métier ;
* une déclaration de l'utilisation de l'IA.

## Fonctionnalité

### Prochain jour férié

La fonctionnalité principale permet de récupérer le prochain jour férié français.

Le backend appelle l'API externe Nager.Date pour récupérer la liste des jours fériés français de l'année en cours.

Ensuite, le service :

* récupère la liste des jours fériés ;
* compare les dates avec la date du jour ;
* sélectionne le prochain jour férié ;
* calcule le nombre de jours restants ;
* retourne une réponse JSON simplifiée.

### Exemple de réponse

```json
{
  "country": "FR",
  "year": 2026,
  "name": "Fête nationale",
  "date": "2026-07-14",
  "daysRemaining": 18
}
```

## Intégration externe

### Nager.Date

Le projet utilise l'API Nager.Date pour récupérer les jours fériés d'un pays.

L'endpoint utilisé est l'endpoint `PublicHolidays` avec deux paramètres principaux :

* l'année ;
* le code du pays.

Dans ce projet, le pays utilisé est `FR`.

Cette API a été choisie car elle est :

* publique ;
* simple à utiliser ;
* accessible sans authentification ;
* adaptée à une fonctionnalité backend courte.

## Route REST

La route REST exposée par le projet est :

```text
GET /api/holidays/next
```

Exemple d'appel en local :

```text
http://localhost:8080/api/holidays/next
```

Cette route retourne le prochain jour férié français au format JSON.

## Architecture

Le projet suit une architecture simple avec séparation des responsabilités.

### HolidayController

Le controller expose la route REST.

Il reçoit l'appel HTTP du client et délègue le traitement au service.

Classe :

```text
HolidayController
```

Méthode principale :

```text
getNextHoliday()
```

### HolidayService

Le service contient la logique métier.

Il appelle l'API externe Nager.Date, récupère les jours fériés, filtre les dates et construit la réponse finale.

Classe :

```text
HolidayService
```

Méthodes principales :

```text
getNextFrenchHoliday()
buildNextHolidayResponse()
```

### HolidayResponse

Cette classe représente la réponse JSON retournée au client.

Elle contient uniquement les informations utiles :

* `country`
* `year`
* `name`
* `date`
* `daysRemaining`

Classe :

```text
HolidayResponse
```

## Stack technique

* Java 17
* Spring Boot
* Spring Web
* Gradle
* JUnit 5

## Persistance

Le projet ne nécessite pas de base de données.

Les données sont récupérées directement depuis l'API externe Nager.Date au moment de l'appel de la route REST.

## Front-end

Aucun front-end n'a été développé.

La fonctionnalité se teste directement via :

* un navigateur ;
* Postman ;
* curl ;
* ou tout autre client HTTP.

## Structure du projet

```text
.
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/devops2/rattrapage/holiday/
│   │           ├── HolidayController.java
│   │           ├── HolidayService.java
│   │           └── HolidayResponse.java
│   └── test/
│       └── java/
│           └── com/devops2/rattrapage/holiday/
│               └── HolidayServiceTest.java
├── build.gradle
├── README.md
└── UTILISATION_IA.md
```

## Démarrage rapide

### Pré-requis

* JDK 17 ou plus
* Gradle Wrapper fourni avec le projet

Aucune base de données n'est nécessaire.

### Lancement

Depuis la racine du projet :

```powershell
.\gradlew.bat bootRun
```

L'application est alors disponible en local sur le port `8080`.

Pour tester la fonctionnalité, utiliser la route :

```text
GET /api/holidays/next
```

Adresse locale complète :

```text
http://localhost:8080/api/holidays/next
```

## Tests

### Commande

Depuis la racine du projet :

```powershell
.\gradlew.bat clean test
```

### Test réalisé

Le test principal est :

```text
HolidayServiceTest
```

Ce test vérifie la logique métier du service avec des données simulées.

Il ne dépend pas directement de l'API externe, ce qui permet d'avoir un test plus stable.

Le test vérifie que le service est capable de trouver le prochain jour férié à partir d'une liste donnée.

## Endpoints utiles

### Jours fériés

```text
GET /api/holidays/next
```

Description :

Retourne le prochain jour férié français.




```powershell
.\gradlew.bat clean test
```
