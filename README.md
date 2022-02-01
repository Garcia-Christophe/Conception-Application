# UE Conception d’applications

L’objet de ce projet est de concevoir de manière incrémentale un logiciel de gestion
d’événements pour une association

## Cahier des charges

**Membre** : un membre d’association est caractérisé par son nom, son prénom, un mail, un
pseudo, un mot de passe, sa ville de résidence, sa date et son lieu de naissance. Le pseudo est
unique.


**Evénement** : un événement est défini par un nom, un descriptif, une image (optionnel), une
date avec des horaires, un lieu (nom, description et lien google maps), un type (repas, AG,
animation, chantier collectif, …) et un nombre maximum de personnes.


**Inscription** : pour participer à un évènement, un sondage est ouvert avant l’événement sur une
période donnée. Durant toute cette période, un membre peut, via ce sondage, s’inscrire,
modifier son inscription ou se désinscrire. Le formulaire d’inscription demande le nombre de
personnes présentes, la réponse à un ensemble de questions (booléennes) et propose une zone
libre de commentaires.

**Suivi** : on doit pouvoir visualiser l’ensemble des membres de l’association, l’ensemble des
événements, l’ensemble des inscriptions pour un événement donné (avec le nombre d’inscrits)
et les informations relatives à une inscription donnée.


Mise en œuvre : Pour faciliter la maintenance de l’application, on a décidé de faire une
application Java. L’ensemble des données est stocké dans une base de données. Le membre de
l’association s’inscrit via un formulaire Web. 


