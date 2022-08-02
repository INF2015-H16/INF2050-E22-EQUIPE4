# INF2050-E22-EQUIPE4 SPRINT 3 

Ce programme calcule la valeur foncière d'un terrain de un ou plusieurs lots. Il peut prendre un fichier d'entrée de format JSON et retourner un fichier JSON avec les résultats des calculs effectués. Apres execution du programme, des informations pertinentes sont prises en note dans un fichier de format JSON et peuvent etre afficher ou reinitialiser avec les options -S ou -SR en ligne de commande.

## Requirements
Avoir Maven d'installer pour pouvoir build le projet [Etapes d'installation](https://maven.apache.org/install.html)

## Installation
1. Cloner ce repertoire dans un nouveau dossier 
2. Se rendre dans le dossier "evaluationfonciere" du repertoire clone 
3. Executer la commande au terminal : `mvn clean compile assembly:single`
4. Se rendre dans le dossier "target" : `cd .\target`
5. Executer le jar : `java -jar .\evaluationfonciere-1.0-jar-with-dependencies.jar <options> <fichiers>`

## Dependencies
[JSON-lib](http://json-lib.sourceforge.net/) version 2.4

## Notes
Ceci est la premiere fois que l'on travaille avec Maven. Le projet est remis sous format Maven, mais si il vous est impossible de build ou d'executer notre programme, faute de notre inexperience, une version non-Maven est disponible sur la branche `dev-backup`.

**En mergeant avec master, l'historique des commits de branches Maven ont ete ajoute a celle de master. Eric et Leonid ont travaille sur une branche non-Maven tandis que
Steven et Goldlen ont travailler sur le projet format Maven. La branche non-Maven a ete ajoute notre branche Maven manuellement vu la difference de formattage du projet, qui complique grandement la chose. L'historique de leur commits peut etre vu dans la branche dev-backup.**

## Authors
- Leonid Glazyrin GLAL77080105
- Goldlen Chhun CHHG20069604
- Steven Chieng CHIS01069604
- Eric Drapeau DRAE21079108

