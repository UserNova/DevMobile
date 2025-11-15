# PizzaRecipes

## Objectif général
Ce TP a pour but de développer une application Android en Java qui affiche une liste de pizzas avec leur nom, prix, durée de préparation et image, puis d’accéder à un détail complet de chaque recette.

L’objectif est de comprendre :
- L’organisation d’un projet Android en packages logiques (`classes`, `dao`, `service`, `adapter`, `ui`)
- La manipulation d’une ListView avec Adapter personnalisé
- Le passage de données entre activités
- L’utilisation d’un Splash Screen animé
- La mise en œuvre d’un modèle de données orienté objet

## Points importants du code
- **ProduitService** : singleton pour stocker et gérer les pizzas avec `data.add(new Produit(...))`.
- **ListView + Adapter** : affichage du nom, image, durée et prix des pizzas.
- **Intent** : passage des informations d’une pizza sélectionnée à `DetailActivity`.
- **Seed** : la méthode `seed()` pré-remplit 10 pizzas avec `data.add(new Produit(...))`.
- **CRUD** : toutes les opérations de création, mise à jour, suppression et recherche sont implémentées dans le service.

## Résultat attendu
- L’application démarre sur un Splash Screen.
- Affiche la liste des pizzas (nom, image, durée, prix).
- Le clic sur une pizza ouvre un écran de détails complet.
- Le code est bien organisé en couches (DAO / Service / Adapter / UI).

# DEMO : 


https://github.com/user-attachments/assets/1b4dc390-e236-4565-a6fb-67d418da9a8d

