# UCE Génie Logiciel Avancé : Techniques de tests

## Introduction

Vous allez à travers ces projet mettre en application une partie des aspects évoqués en cours vis à vis des techniques de tests.  
Pour cela nous allons réaliser un projet logiciel de petite taille, en suivant la roadmap suivante : 
- Setup du projet
- Mise en place des outils d’intégration continue
- Écriture des tests unitaires
- Écriture des mocks, et validation des tests
- Développement dirigé par les tests
- Documentation et conventions de style
- Test d'une implémentation donnée

Durant cette série de TPs, le gestionnaire de version Git sera utilisé à foison, à travers la plateforme GitHub. Si vous n’êtes pas à l’aise avec cet outil[^1], [voici](http://rogerdudler.github.io/git-guide/) un petit guide à garder sous la main.

## Sujets

L'ensemble des sujets de TPs peut être trouvé dans le dossier `TPs`.

Le dossier `src` contient la définition de l'ensemble des interfaces qui seront l'objet de vos travaux.

## Rendus

Le rendu des TPs se fait au rythme suivant :

- TP1 : 2ème séance
- TP2 : 2ème séance
- TP3 : 3ème séance
- TP4 : 5ème séance
- TP5 : dernière séance
- TP6 : dernière séance

Pour chaque rendu vous devez créer un tag à partir du commit qui correspond à la complétion du TP.  
Si vous ne spécifiez pas de tag, le dernier commit à la date-heure de la fin de séance sera celui considéré.

[^1]: Si vous n’êtes vraiment pas à l’aise avec cet outil nous vous conseillons quand même vivement de vous y mettre.


## Informations

- **Nom** : Hugo Chautard
- **Groupe** : M1 ILSEN Alternance

## Badges

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/Hchautard/ceri-m1-techniques-de-test/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Hchautard/ceri-m1-techniques-de-test/tree/master)
[![codecov](https://codecov.io/github/Hchautard/ceri-m1-techniques-de-test/graph/badge.svg?token=MJIDUN8QJ3)](https://codecov.io/github/Hchautard/ceri-m1-techniques-de-test)
[![Checkstyle](https://img.shields.io/badge/checkstyle-report-blue)](https://<votre-url-du-rapport>/checkstyle.html)

## Description

Ce projet a pour but de mettre en œuvre différentes techniques de tests vues en cours. 
Nous avons utilisé les technologies suivantes :

- **Java** pour le langage principal du projet.
- **Maven** pour la gestion des dépendances et la compilation.
- **JUnit** pour l'exécution des tests unitaires.
- **Mockito** pour les mocks et tests unitaires avancés.
- **JaCoCo** pour la génération de la couverture de test.
- **CircleCI** pour l'intégration continue.
- **Codecov** pour le suivi de la couverture de test.

## Choix techniques

Ce projet adopte une approche de développement dirigée par les tests (TDD) pour assurer que chaque fonctionnalité est couverte par des tests dès sa conception. Nous utilisons Mockito pour simuler les dépendances et tester chaque composant indépendamment.

## le TP6

Dans ce TP, nous implémentons la classe RocketPokemonFactory que la très sympathique Team Rocket nous a donné.
La classe RocketPokemonFactory est fonctionnelle, mais elle comporte plusieurs problèmes de performance, erreurs de gestion des indices et manque de robustesse.

### Problème de performance
Cause : La méthode generateRandomStat() effectue 1 000 000 d'itérations.
Impact : Ralentit significativement la création des Pokémon.
Solution : Remplacer par Random().nextInt(101).

### Problème de gestion des Pokémon inconnus
Cause : Les Pokémon avec un index non mappé deviennent MISSINGNO.
Impact : Tous les Pokémon non référencés dans index2name sont identifiés comme MISSINGNO.
Solution : Ajouter plus de Pokémon à la carte index2name.

Par rapport aux tests effectués sur cette classe, il y a des erreurs lors de la création de nouveau Pokemon.
Cela veut dire que pour le prochain développeur a travaillé sur ce code, il aura pas mal de tests à corriger de par la mauvaise implémentation.
