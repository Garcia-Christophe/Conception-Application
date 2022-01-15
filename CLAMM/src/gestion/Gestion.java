package gestion;

import java.util.ArrayList;
import java.util.Date;
import gestionEvenements.Evenement;
import gestionEvenements.TypeEvenement;
import gestionMembres.Membre;

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
   * Prochain identifiant
   */
  private int nextId;

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
    this.nextId = 1;
  }

  /**
   * @return la liste des évènements
   */
  public ArrayList<Evenement> getListeEvenements() {
    return listeEvenements;
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
   * @param liste La liste des évènements
   */
  private void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  }

  /**
   * Créer un événement.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return l'object l'événement si création réussie, null si erreur de création
   */
  private Evenement creerEvenement(int unId, String unNom, String unDescriptif, String uneImage,
      Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    Evenement unEvenement = new Evenement();

    int resultats_set = 0;

    resultats_set += unEvenement.setId(unId);

    resultats_set += unEvenement.setNom(unNom);

    resultats_set += unEvenement.setDescriptif(unDescriptif);

    resultats_set += unEvenement.setImage(uneImage);

    resultats_set += unEvenement.setDate(uneDate);

    resultats_set += unEvenement.setLieu(unLieu);

    resultats_set += unEvenement.setNbMaxPersonnes(unNbMaxPersonnes);

    resultats_set += unEvenement.setType(unType);

    if (resultats_set < 0) {
      unEvenement = null;
    }

    return unEvenement;
  }

  /**
   * Ajoute un événement à la liste des événements.
   * 
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code 0} si l'ajout est un succès, {@code -1} si erreur de création de l'événement
   */
  public int ajouterEvenement(String unNom, String unDescriptif, String uneImage, Date uneDate,
      String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    Evenement unEvenement = creerEvenement(nextId, unNom, unDescriptif, uneImage, uneDate, unLieu,
        unNbMaxPersonnes, unType);

    int res = -1;

    if (unEvenement != null) {
      listeEvenements.add(unEvenement);

      nextId++;

      res = 0;
    }

    return res;
  }

  /**
   * Retourne l'événement de la liste des événements avec son identifiant.
   * 
   * @param unId Identifiant de l'événement
   * @return l'object Evenement si présent dans la liste, si non présent dans la liste
   */
  public Evenement getEvenement(int unId) {
    Evenement unEvenement = null;

    if (unId > 0 || unId < nextId) {
      for (Evenement e : listeEvenements) {
        if (e.getId() == unId) {
          unEvenement = e;
        }
      }
    }

    return unEvenement;
  }

  /**
   * Supprime événement de la liste des événements avec son identifiant.
   * 
   * @param unId Identifiant de l'événement à supprimer
   * @return {@code 0} si la suppression est un succès, {@code -1} si l'événement correspondant à
   *         l'identifiant n'est pas présent dans la liste
   */
  public int supprimerEvenement(int unId) {
    Evenement unEvenement = getEvenement(unId);

    int res = -1;

    if (unEvenement != null) {
      listeEvenements.remove(unEvenement);
      res = 0;
    }

    return res;
  }

  /**
   * Modifie un événement de la liste des événements.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code 0} si la modification est un succès, {@code -1} si l'événement correspondant à
   *         l'identifiant n'est pas présent dans la liste, {@code 1} si erreur de modification de
   *         l'événement
   */
  public int modifierEvenement(int unId, String unNom, String unDescriptif, String uneImage,
      Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    int res = -1;

    if (unId > 0 || unId < nextId) {

      for (int i = 0; i < listeEvenements.size(); i++) {
        if (listeEvenements.get(i).getId() == unId) {
          Evenement unEvenement = creerEvenement(unId, unNom, unDescriptif, uneImage, uneDate,
              unLieu, unNbMaxPersonnes, unType);

          if (unEvenement == null) {
            res = 1;
          } else {
            listeEvenements.set(i, unEvenement);

            res = 0;
          }
        }
      }
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
   * <p>
   * Les codes erreurs sont les suivants :
   * <ul>
   * <li>- PSEUDO_TROP_COURT : lorsque la taille du pseudo est trop petite</li>
   * <li>- PSEUDO_TROP_LONG : lorsque la taille du pseudo est trop grande</li>
   * <li>- PSEUDO_CARACTERE_INVALIDE : lorsqu'un caractère du pseudo n'est pas possible</li>
   * <li>- NOM_TROP_LONG : lorsque le nom est trop long</li>
   * <li>- ...</li>
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
   * Les codes erreurs dus aux définitions sont les suivants :
   * <ul>
   * <li>- PSEUDO_TROP_COURT : lorsque la taille du pseudo est trop petite</li>
   * <li>- PSEUDO_TROP_LONG : lorsque la taille du pseudo est trop grande</li>
   * <li>- PSEUDO_CARACTERE_INVALIDE : lorsqu'un caractère du pseudo n'est pas possible</li>
   * <li>- NOM_TROP_LONG : lorsque le nom est trop long</li>
   * <li>- ...</li>
   * 
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
  public ArrayList<CodeErreur> modifierMembre(String unPseudo, String unNom, String unPrenom,
      String unLieuNaissance, Date uneDateNaissance, String uneVille, String unMail,
      String unMotDePasse) {
    ArrayList<CodeErreur> res = null;
    Membre membreAModifier = this.getMembre(unPseudo);
    this.codesErreurs.clear();

    // Si le membre est dans la liste des membres
    if (membreAModifier != null) {
      CodeErreur codeErreur = null;

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
