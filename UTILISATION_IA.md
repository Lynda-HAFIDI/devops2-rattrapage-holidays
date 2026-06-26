\# Utilisation de l’IA



L’IA a été utilisée comme aide ponctuelle pendant le développement, notamment pour le choix d’une API externe simple, la structuration de la fonctionnalité, l’amélioration du code et la création d’un test.



\## Prompt 1



Je dois réaliser une petite fonctionnalité backend pour un projet DevOps.  

Peux-tu me proposer une API REST publique, simple à utiliser, sans authentification, adaptée à un projet académique ?



\## Réponse



Tu peux utiliser l’API Nager.Date.



Elle est adaptée pour un petit projet académique parce qu’elle est publique, simple à appeler et ne demande pas d’authentification. Elle permet de récupérer les jours fériés d’un pays pour une année donnée.



Exemple d’endpoint :



https://date.nager.at/api/v3/PublicHolidays/2026/FR



Pour une fonctionnalité simple, tu peux créer une route qui récupère les jours fériés français et retourne le prochain jour férié à partir de la date actuelle.



\## Utilisation dans le projet



J’ai choisi l’API Nager.Date pour récupérer les jours fériés français et construire une route REST qui retourne le prochain jour férié.



\---



\## Prompt 2



Je veux ajouter une fonctionnalité backend Spring Boot qui appelle une API externe et expose le résultat via une route REST.  

Je veux suivre une architecture controller / service / classe de réponse pour avoir un code clair. Comment structurer ça ?



\## Réponse



Tu peux organiser la fonctionnalité en trois classes principales :



\- `HolidayController` : expose la route REST appelée par le client.

\- `HolidayService` : contient l’appel à l’API externe et la logique métier.

\- `HolidayResponse` : représente la réponse JSON retournée au client.



Le controller ne doit pas contenir la logique métier. Il doit seulement recevoir la requête et appeler le service.  

Le service s’occupe de construire l’URL de l’API externe, récupérer les données, les traiter et construire la réponse finale.  

La classe `HolidayResponse` permet de retourner uniquement les informations utiles au lieu de renvoyer toute la réponse brute de l’API externe.



\## Utilisation dans le projet



J’ai créé les classes `HolidayController`, `HolidayService` et `HolidayResponse` pour séparer les responsabilités.



\---



\## Prompt 3



Voici mon idée : appeler l’API Nager.Date pour récupérer les jours fériés français, puis retourner uniquement le prochain jour férié.  

Comment améliorer mon code pour ne pas renvoyer toute la réponse brute de l’API ?



\## Réponse



Il vaut mieux ne pas retourner directement toute la liste reçue depuis l’API externe.  

Tu peux récupérer la liste des jours fériés, parcourir les dates, comparer chaque date avec la date actuelle, puis retourner uniquement le prochain jour férié.



La réponse peut contenir seulement les champs utiles :



\- le pays ;

\- l’année ;

\- le nom du jour férié ;

\- la date ;

\- le nombre de jours restants.



Cela permet d’avoir une vraie transformation métier au lieu d’un simple renvoi de données brutes.



\## Utilisation dans le projet



J’ai ajouté une logique métier dans `HolidayService` pour filtrer la liste des jours fériés et construire une réponse simplifiée.



\---



\## Prompt 4



Je veux tester ma fonctionnalité, mais je ne veux pas dépendre directement de l’API externe dans mon test.  

Peux-tu me proposer un test simple avec JUnit pour vérifier la logique métier du service ?



\## Réponse



Pour éviter de dépendre de l’API externe dans le test, tu peux tester la logique métier avec des données simulées.



L’idée est de créer une liste de jours fériés directement dans le test, puis d’appeler une méthode du service qui cherche le prochain jour férié à partir de cette liste.



Cela permet de vérifier le traitement principal sans dépendre d’Internet ni de la disponibilité de l’API distante.



Exemple de test attendu :



\- créer une liste avec deux jours fériés ;

\- choisir une date de référence ;

\- appeler la méthode de traitement ;

\- vérifier que le jour férié retourné est le bon.



\## Utilisation dans le projet



J’ai créé `HolidayServiceTest` pour tester la logique métier avec des données simulées.



\---



\## Prompt 5



J’ai essayé de faire un test avec MockMvc et WebMvcTest, mais j’ai des erreurs de dépendances et d’imports.  

Quelle solution plus simple puis-je utiliser pour tester ma fonctionnalité ?



\## Réponse



Si le test avec `MockMvc` et `WebMvcTest` prend trop de temps ou crée des erreurs de configuration, tu peux simplifier en testant directement le service.



Pour cela, il faut séparer l’appel API externe et le traitement métier.  

Tu peux créer une méthode dans le service qui prend en paramètre une liste de jours fériés, une date actuelle et une année, puis retourne un `HolidayResponse`.



Ensuite, le test appelle cette méthode avec des données simulées.  

C’est plus simple, plus stable, et cela permet quand même de vérifier la partie importante de la fonctionnalité : la logique métier.



\## Utilisation dans le projet



J’ai séparé la logique dans la méthode `buildNextHolidayResponse`, puis j’ai testé cette méthode dans `HolidayServiceTest`.

