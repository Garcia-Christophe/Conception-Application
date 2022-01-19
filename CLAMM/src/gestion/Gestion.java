package gestion;

import java.util.ArrayList;
import java.util.Date;
import gestionEvenements.Evenement;
import gestionEvenements.TypeEvenement;
import gestionMembres.Membre;

/**
 * La classe Gestion permet de gérer tous les membres et tous les évènements.
 * 
 * <p>
 * La classe peut créer, ajouter, modifier et supprimer des membres et des évènements. Elle contient
 * la liste de tous les membres, la liste de tous les évènements, ainsi que la liste de tous les
 * {@code CodeErreur} survenus lors d'un appel d'une des méthodes.
 * 
 * @author Manon, Christophe
 * @version 1.00
 * @see {@link Membre}, {@link Evenement}
 */
public class Gestion {

  /**
   * Liste des codes erreurs
   */
  private ArrayList<CodeErreur> codesErreurs;

  /**
   * Liste des événements
   */
  private ArrayList<Evenement> listeEvenements;

  /**
   * Prochain identifiant d'un événement
   */
  private int prochainIdEvenement;

  /**
   * Liste des membres
   */
  private ArrayList<Membre> listeMembres;

  /**
   * Constructeur de la classe {@code Gestion}
   */
  public Gestion() {
    this.setListeMembres(new ArrayList<Membre>());
    this.setListeEvenements(new ArrayList<Evenement>());
    this.setCodesErreurs(new ArrayList<CodeErreur>());
    this.prochainIdEvenement = 1;
  }

  /**
   * @param uneListe La liste des codes erreurs
   */
  private void setCodesErreurs(ArrayList<CodeErreur> uneListe) {
    if (uneListe != null) {
      this.codesErreurs = uneListe;
    }
  }

  /**
   * @return la liste des codes erreurs
   */
  public ArrayList<CodeErreur> getCodesErreurs() {
    return this.codesErreurs;
  }

  /**
   * @return la liste des événements
   */
  public ArrayList<Evenement> getListeEvenements() {
    return listeEvenements;
  }

  /**
   * @param liste La liste des évènements
   */
  private void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  }

  /**
   * Créer un {@code Evenement}.
   * 
   * <p>
   * Si une ou plus définition(s) des attributs du nouveau evenement est un échec, le code erreur
   * est ajouté dans la liste des codes erreurs, et retourne un membre {@code null}.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return une nouvelle instance de la classe {@code Evenement} si la création est un succès,
   *         {@code null} sinon
   */
  private Evenement creerEvenement(int unId, String unNom, String unDescriptif, String uneImage,
      Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {

    Evenement unEvenement = new Evenement();

    this.codesErreurs.clear();

    CodeErreur codeErreur = null;

    // Identifiant de l'événement
    codeErreur = unEvenement.setId(unId);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Nom de l'événement
    codeErreur = unEvenement.setNom(unNom);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Descriptif de l'événement
    codeErreur = unEvenement.setDescriptif(unDescriptif);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Image de l'événement
    codeErreur = unEvenement.setImage(uneImage);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Date de l'événement
    codeErreur = unEvenement.setDate(uneDate);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Lieu de l'événement
    codeErreur = unEvenement.setLieu(unLieu);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // NbMaxPersonnes de l'événement
    codeErreur = unEvenement.setNbMaxPersonnes(unNbMaxPersonnes);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Type de l'événement
    codeErreur = unEvenement.setType(unType);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Si une des définitions est un échec, retourne un evenement null
    if (!this.codesErreurs.isEmpty()) {
      unEvenement = null;
    }

    return unEvenement;
  }

  /**
   * Ajoute un {@code Evenement} à la liste des événements.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@code Evenement}.
   * 
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code null} si l'ajout du nouveau membre est un succès, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> ajouterEvenement(String unNom, String unDescriptif, String uneImage,
      Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {

    Evenement unEvenement = creerEvenement(prochainIdEvenement, unNom, unDescriptif, uneImage,
        uneDate, unLieu, unNbMaxPersonnes, unType);

    ArrayList<CodeErreur> res = null;

    if (unEvenement != null) {
      listeEvenements.add(unEvenement);

      prochainIdEvenement++;
    } else {
      res = getCodesErreurs();
    }

    return res;
  }

  /**
   * Permet de retrouver un {@code Evenement} grâce à son identifiant {@code unId}.
   * 
   * @param unId Identifiant de l'événement à retrouver
   * @return l'instance de {@code Evenement} correspondant à l'identifiant, {@code null} sinon
   */
  public Evenement getEvenement(int unId) {
    Evenement unEvenement = null;

    if (unId > 0 || unId < prochainIdEvenement) {
      for (Evenement e : listeEvenements) {
        if (e.getId() == unId) {
          unEvenement = e;
        }
      }
    }

    return unEvenement;
  }

  /**
   * Supprime un {@code Evenement} de la liste des événements.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@code Evenement}.
   * 
   * @param unId Identifiant de l'événement à supprimer
   * @return {@code null} si la suppression de l'événement est un succès, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> supprimerEvenement(int unId) {
    ArrayList<CodeErreur> res = null;
    Evenement evenementASupprimer = this.getEvenement(unId);

    // Si l'evenement est dans la liste des événements
    if (evenementASupprimer != null) {
      this.listeEvenements.remove(evenementASupprimer); // supprime le membre de la liste
    } else {
      this.codesErreurs.clear();
      this.codesErreurs.add(CodeErreur.EVENEMENT_INTROUVABLE);
      res = getCodesErreurs();
    }

    return res;
  }

  /**
   * Modifie un événement de la liste des événements si toutes les modifications sont possibles.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@code Evenement}.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code null} si la modification de l'événement est un succès, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> modifierEvenement(int unId, String unNom, String unDescriptif,
      String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    ArrayList<CodeErreur> res = null;
    this.codesErreurs.clear();

    Evenement evenementAModifier = this.getEvenement(unId);

    // Si l'evenement est dans la liste des événements
    if (evenementAModifier != null) {

      for (int i = 0; i < listeEvenements.size(); i++) {
        if (listeEvenements.get(i).getId() == unId) {
          Evenement unEvenement = creerEvenement(unId, unNom, unDescriptif, uneImage, uneDate,
              unLieu, unNbMaxPersonnes, unType);

          if (unEvenement == null) {
            res = getCodesErreurs(); // modifications pas possibles
          } else {
            listeEvenements.set(i, unEvenement);
          }

        }
      }
    } else {
      this.codesErreurs.add(CodeErreur.EVENEMENT_INTROUVABLE);
      res = getCodesErreurs();
    }

    return res;
  }

  /**
   * @return la liste des membres
   */
  public ArrayList<Membre> getListeMembres() {
    return this.listeMembres;
  }

  /**
   * @param uneListe la liste des membres
   * @return {@code 0}
   */
  public int setListeMembres(ArrayList<Membre> uneListe) {
    if (uneListe != null) {
      this.listeMembres = uneListe;
    }
    return 0;
  }

  /**
   * Crée un {@code Membre}.
   * 
   * <p>
   * Si une ou plus définition(s) des attributs du nouveau membre est un échec, ajoute le code
   * erreur dans la liste des codes erreurs, et retourne un membre {@code null}.
   * 
   * @param unPseudo pseudo du membre à créer
   * @param unNom nom du membre à créer
   * @param unPrenom prénom du membre à créer
   * @param unLieuNaissance lieu de naissance du membre à créer
   * @param uneDateNaissance date de naissance du membre à créer
   * @param uneVille ville du membre à créer
   * @param unMail adresse mail du membre à créer
   * @param unMotDePasse mot de passe du membre à créer
   * @return une nouvelle instance de la classe {@code Membre} si la création est un succès,
   *         {@code null} sinon
   */
  private Membre creerMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    Membre membre = new Membre();
    this.codesErreurs.clear();
    CodeErreur codeErreur = null;

    // Définition du nouveau pseudo du membre
    codeErreur = membre.setPseudo(unPseudo);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Définition du nouveau nom du membre
    codeErreur = membre.setNom(unNom);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Définition du nouveau prénom du membre
    codeErreur = membre.setPrenom(unPrenom);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Définition du nouveau lieu de naissance du membre
    codeErreur = membre.setLieuNaissance(unLieuNaissance);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Définition de la nouvelle date de naissance du membre
    codeErreur = membre.setDateNaissance(uneDateNaissance);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Définition de la nouvelle ville du membre
    codeErreur = membre.setVille(uneVille);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Définition de la nouvelle adresse mail du membre
    codeErreur = membre.setMail(unMail);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Définition du nouveau mot de passe du membre
    codeErreur = membre.setMotDePasse(unMotDePasse);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Si une des définitions est un échec, retourne un membre null
    if (!this.codesErreurs.isEmpty()) {
      membre = null;
    }

    return membre;
  }

  /**
   * Ajoute un {@code Membre} à la liste des membres, seulement s'il n'existe pas déjà.
   * 
   * <p>
   * Avec la méthode {@link #getMembre(String)}, on récupère le membre déjà existant dans la liste
   * des membres à partir du pseudo {@code unPseudo}. Si la valeur n'est pas {@code null}, alors le
   * code erreur {@code CodeErreur.PSEUDO_DEJA_EXISTANT} est renvoyé. Sinon, un appel à
   * {@link #creerMembre(String, String, String, String, Date, String, String, String)} est réalisé
   * pour créer le membre avant de l'ajouter dans la liste des membres.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@code Membre}.
   * 
   * @param unPseudo pseudo du nouveau membre
   * @param unNom nom du nouveau membre
   * @param unPrenom prénom du nouveau membre
   * @param unLieuNaissance lieu de naissance du nouveau membre
   * @param uneDateNaissance date de naissance du nouveau membre
   * @param uneVille ville du nouveau membre
   * @param unMail adresse mail du nouveau membre
   * @param unMotDePasse mot de passe du nouveau membre
   * @return {@code null} si l'ajout du nouveau membre est un succès, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> ajouterMembre(String unPseudo, String unNom, String unPrenom,
      String unLieuNaissance, Date uneDateNaissance, String uneVille, String unMail,
      String unMotDePasse) {
    ArrayList<CodeErreur> res = null;
    Membre membre = this.getMembre(unPseudo);

    // Si aucun membre de la liste des membres ne possède le même pseudo
    if (membre == null) {
      membre = this.creerMembre(unPseudo, unNom, unPrenom, unLieuNaissance, uneDateNaissance,
          uneVille, unMail, unMotDePasse);

      // Si la création du membre est un succès
      if (membre != null) {
        this.listeMembres.add(membre); // ajoute le membre à la liste des membres
      } else {
        res = this.getCodesErreurs(); // renvoie la liste des codes erreurs
      }
    } else {
      this.codesErreurs.clear();
      this.codesErreurs.add(CodeErreur.PSEUDO_DEJA_EXISTANT);
    }

    return res;
  }

  /**
   * Supprime un {@code Membre} de la liste des membres.
   * 
   * <p>
   * Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la méthode
   * {@link #getMembre(String)}. Si le membre est trouvé, alors le retire de la liste des membres et
   * ne renvoie aucun code erreur, renvoie {@code null}. Si aucun membre n'est trouvé dans la liste
   * des membres, alors renvoie le code erreur {@code CodeErreur.MEMBRE_INTROUVABLE}.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@code Membre}.
   * 
   * @param unPseudo pseudo du membre à supprimer de la liste
   * @return {@code null} si la suppression du membre est un succès, une liste de {@code CodeErreur}
   *         sinon
   */
  public ArrayList<CodeErreur> supprimerMembre(String unPseudo) {
    ArrayList<CodeErreur> res = null;
    Membre membreASupprimer = this.getMembre(unPseudo);

    // Si le membre est dans la liste des membres
    if (membreASupprimer != null) {
      this.listeMembres.remove(membreASupprimer); // supprime le membre de la liste
    } else {
      this.codesErreurs.clear();
      this.codesErreurs.add(CodeErreur.MEMBRE_INTROUVABLE);
    }

    return res;
  }

  /**
   * Modifie les données d'un membre de la liste des membres, si et seulement si toutes les
   * modifications sont des succès.
   * 
   * <p>
   * Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la méthode
   * {@link #getMembre(String)}. Si aucun membre n'est trouvé dans la liste des membres, alors
   * renvoie le code erreur {@code CodeErreur.MEMBRE_INTROUVABLE}. Si le membre est trouvé, alors
   * tente de modifier ses attributs. Si tous les attributs du membre ont été modifiés avec succès,
   * ne renvoie aucun code erreur, renvoie {@code null}, sinon renvoie la liste des codes erreurs
   * correspondantes.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@code Membre}.
   * 
   * @param ancienPseudo ancien pseudo du membre modifier
   * @param unPseudo nouveau pseudo du membre
   * @param unNom nouveau nom du membre
   * @param unPrenom nouveau prénom du membre
   * @param unLieuNaissance nouveua lieu de naissance du membre
   * @param uneDateNaissance nouvelle date de naissance du membre
   * @param uneVille nouvelle ville du membre
   * @param unMail nouvelle adresse mail du membre
   * @param unMotDePasse nouveau mot de passe du membre
   * @return {@code null} si la modification du membre est un succès, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> modifierMembre(String ancienPseudo, String unPseudo, String unNom,
      String unPrenom, String unLieuNaissance, Date uneDateNaissance, String uneVille,
      String unMail, String unMotDePasse) {
    ArrayList<CodeErreur> res = null;
    this.codesErreurs.clear();
    Membre membreAModifier = this.getMembre(ancienPseudo);

    // Si le membre est dans la liste des membres
    if (membreAModifier != null) {
      CodeErreur codeErreur = null;

      // Définitions des nouveaux attributs si le pseudo est unique
      if (this.getMembre(unPseudo) == null
          || (ancienPseudo != null && unPseudo != null && ancienPseudo.equals(unPseudo))) {
        // Définition du nouveau pseudo du membre
        codeErreur = membreAModifier.setPseudo(unPseudo);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Définition du nouveau nom du membre
        codeErreur = membreAModifier.setNom(unNom);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Définition du nouveau prénom du membre
        codeErreur = membreAModifier.setPrenom(unPrenom);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Définition du nouveau lieu de naissance du membre
        codeErreur = membreAModifier.setLieuNaissance(unLieuNaissance);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Définition de la nouvelle date de naissance du membre
        codeErreur = membreAModifier.setDateNaissance(uneDateNaissance);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Définition de la nouvelle ville du membre
        codeErreur = membreAModifier.setVille(uneVille);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Définition de la nouvelle adresse mail du membre
        codeErreur = membreAModifier.setMail(unMail);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Définition du nouveau mot de passe du membre
        codeErreur = membreAModifier.setMotDePasse(unMotDePasse);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Si une des définitions est un échec, retourne la liste des codes erreurs
        if (!this.codesErreurs.isEmpty()) {
          res = this.getCodesErreurs();
        }
      } else {
        this.codesErreurs.add(CodeErreur.PSEUDO_DEJA_EXISTANT);
      }
    } else {
      this.codesErreurs.add(CodeErreur.MEMBRE_INTROUVABLE);
    }

    return res;
  }

  /**
   * Permet de retrouver un membre grâce à son pseudo {@code unPseudo}.
   * 
   * <p>
   * Si le pseudo {@code unPseudo} n'est pas {@code null}, alors compare un par un le pseudo de
   * chacun des membres de la liste des membres avec celui passé en paramètre. Le parcours de la
   * liste s'arrête lorsque le membre ayant le même pseudo à été trouvé et renvoie le
   * {@code Membre}, ou que la liste a été entièrement parcourue et renvoie {@code null}.
   * 
   * @param unPseudo le pseudo du membre à récupérer
   * @return l'instance de {@code Membre} correspondant au pseudo, {@code null} sinon
   */
  public Membre getMembre(String unPseudo) {
    Membre membre = null;

    // Si le paramètre n'est pas null
    if (unPseudo != null) {
      boolean membreTrouve = false;
      int i = 0;

      // Cherche le membre ayant le pseudo unPseudo dans la liste des membres
      while (!membreTrouve && i < this.listeMembres.size()) {
        if (this.listeMembres.get(i).getPseudo().equals(unPseudo)) {
          membre = this.listeMembres.get(i);
          membreTrouve = true; // membre trouvé, stoppe le parcours de la liste des membres
        }

        i++; // passe au membre suivant
      }
    }

    return membre;
  }

}
