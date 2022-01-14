package gestion;

import java.util.ArrayList;
import java.util.Date;
import gestionEvenements.Evenement;
import gestionEvenements.TypeEvenement;
import gestionMembres.Membre;

public class Gestion {

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
   * Constructeur de l'interface Gestion
   */
  public Gestion() {
    this.setListeMembres(new ArrayList<Membre>());
    this.setListeEvenements(new ArrayList<>());
    this.codesErreurs = new ArrayList<>();
    this.nextId = 1;
  }

  /**
   * @return la liste des évènements
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
   * Crée un membre.
   * 
   * @param unPseudo pseudo du membre à créer
   * @param unNom nom du membre à créer
   * @param unPrenom prénom du membre à créer
   * @param unLieuNaissance lieu de naissance du membre à créer
   * @param uneDateNaissance date de naissance du membre à créer
   * @param uneVille ville du membre à créer
   * @param unMail adresse mail du membre à créer
   * @param unMotDePasse mot de passe du membre à créer
   * @return une instance de la classe {@code Membre} la création est un succès, {@code null} sinon
   */
  private Membre creerMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    Membre membre = new Membre();
    int res = 0;

    // Définition des attributs du membre
    res += membre.setPseudo(unPseudo);
    res += membre.setNom(unNom);
    res += membre.setPrenom(unPrenom);
    res += membre.setLieuNaissance(unLieuNaissance);
    res += membre.setDateNaissance(uneDateNaissance);
    res += membre.setVille(uneVille);
    res += membre.setMail(unMail);
    res += membre.setMotDePasse(unMotDePasse);

    // recup membre.getErreurs();

    // Si une des définitions est un échec, retourne un membre null
    if (res != 0) {
      membre = null;
    }

    return membre;
  }

  /**
   * Ajoute un membre à la liste des membres, s'il n'existe pas déjà.
   * 
   * @param unPseudo pseudo du membre à ajouter
   * @param unNom nom du membre à ajouter
   * @param unPrenom prénom du membre à ajouter
   * @param unLieuNaissance lieu de naissance du membre à ajouter
   * @param uneDateNaissance date de naissance du membre à ajouter
   * @param uneVille ville du membre à ajouter
   * @param unMail adresse mail du membre à ajouter
   * @param unMotDePasse mot de passe du membre à ajouter
   * @return {@code 0} si l'ajout est un succès, {@code -1} sinon
   */
  public int ajouterMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    int res = -1;
    Membre membreExistant = this.getMembre(unPseudo);

    // Si le pseudo du membre à ajouter n'existe pas
    if (membreExistant == null) {
      Membre membre = this.creerMembre(unPseudo, unNom, unPrenom, unLieuNaissance, uneDateNaissance,
          uneVille, unMail, unMotDePasse);

      // Si la création du membre est un succès
      if (membre != null) {
        this.listeMembres.add(membre);
        res = 0;
      }
    }

    return res;
  }

  /**
   * Supprime un membre de la liste des membres.
   * 
   * @param unPseudo pseudo du membre à supprimer de la liste
   * @return {@code 0} si la suppression est un succès, {@code -1} sinon
   */
  public int supprimerMembre(String unPseudo) {
    int res = -1;

    // Si le paramètre n'est pas null
    if (unPseudo != null) {
      Membre membre = this.getMembre(unPseudo);

      // Si le membre est dans la liste des membres
      if (membre != null) {
        // Supprime le membre de la liste
        this.listeMembres.remove(membre);
        res = 1;
      }
    }

    return res;
  }

  /**
   * Modifie les données d'un membre de la liste des membres.
   * 
   * @param unMembre le membre de la liste à modifier
   * @return {@code 0} si la modification est un succès, {@code -1} sinon
   */
  public int modifierMembre(Membre unMembre) {
    int res = -1;

    // Si le paramètre n'est pas null
    if (unMembre != null) {
      Membre membreAModifier = this.getMembre(unMembre.getPseudo());

      // Si le membre est dans la liste des membres
      if (membreAModifier != null) {

        // Modifie le membre de la liste
        int date = membreAModifier.setDateNaissance(unMembre.getDateNaissance());
        int lieu = membreAModifier.setLieuNaissance(unMembre.getLieuNaissance());
        int mail = membreAModifier.setMail(unMembre.getLieuNaissance());
        int mdp = membreAModifier.setMotDePasse(unMembre.getMotDePasse());
        int nom = membreAModifier.setNom(unMembre.getNom());
        int prenom = membreAModifier.setPrenom(unMembre.getPrenom());
        int ville = membreAModifier.setVille(unMembre.getVille());

        // Vérifie l'état de la modification (totale, partielle ou refusée)
        if (date == 1 && lieu == 1 && mail == 1 && mdp == 1 && mdp == 1 && nom == 1 && prenom == 1
            && ville == 1) {
          res = 1;
        } else if (date == 1 || lieu == 1 || mail == 1 || mdp == 1 || mdp == 1 || nom == 1
            || prenom == 1 || ville == 1) {
          res = 0;
        }
      }
    }

    return res;
  }

  /**
   * Permet de retrouver un membre grâce à son pseudo.
   * 
   * @param unPseudo le pseudo du membre à récupérer
   * @return le membre correspondant au pseudo, {@code null} sinon
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
        }
      }
    }

    return membre;
  }

}
