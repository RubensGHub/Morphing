# Application de morphing d'images

## Présentation :
Notre projet est une application java qui effectue différents morphings d'une image de départ à une image d'arrivée, celles-ci étant choisies par l'utilisateur.
Les images peuvent être de différentes extensions (jpg, jpeg, png, gif). 
Il intègre le morphing de formes simples ainsi que le morphing avec n'importe quel image, notamment des visages.
Il est également possible de choisir le nombre total d'images intermédiaires qui seront utilisées pour générer le GIF, jusqu'à 50.

## Pré-requis et Installation :
Afin de pouvoir lancer le projet, il est nécessaire d'installer les librairies requises pour l'utilisation du JavaFX.
Commencez par extraire le dossier de l'archive.
Poursuivez en ouvrant le projet dans votre éditeur de texte préféré. Téléchargez javafx SFX ici : https://gluonhq.com/products/javafx/ (version 22.0.1 ou la plus récente). 
- Sur Eclipse, ajoutez les librairies .jar téléchargées précédemment dans le menu Java Build Path ;
- Sur Visual Studio Code, ajoutez les librairies .jar téléchargées précédemment en cliquant sur le "+" de "Referenced Libraries", dans la partie "JAVA PROJECT".
Il vous faudra également télécharger une librairie "gifencoder" pour veiller au bon fonctionnement du GIF (https://jar-download.com/artifact-search/gifencoder).


## Démarrage :
Enfin, vous pouvez lancer le programme (Run) depuis le fichier AccueilApp.java se trouvant dans le dossier "commun" dans les sources (src). Après avoir réalisé tout cela, vous pouvez désormais choisir le mode de morphing que vous désirez, sélectionner deux images portant l'une des extensions prises en charges, choisir le nombre d'images intermédiaires (un plus grand nombre d'image intermediaire prendra plus de temps mais offrira un résultat plus fluide), et admirer le résultat. Ce dernier se caractèrise par un fichier .gif qui se crééra directement dans le dossier du projet. 

## Exigences et informations supplémentaires :
Attention, pour le bon déroulement du morphing et ce pour tous les types de morphing, il vous faudra absolument choisir uniquement des images de tailles identiques.

## Version
**Version stable :** 1.0
**Lien GitHub du projet :** https://github.com/RubensGHub/Morphing

## Auteurs
 - **BOUCHOU Ryan** (https://github.com/iRyann)
 - **CORRAL Romain** (https://github.com/RomainCrrl)
 - **SAUVAN Alexandre** (https://github.com/Potoxela)
 - **TUEUX Rubens** (https://github.com/RubensGHub)
 - **USIETO Paul** (https://github.com/paulusieto)
