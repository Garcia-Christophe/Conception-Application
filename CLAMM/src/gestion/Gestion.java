package gestion;

import java.util.ArrayList;
import java.util.Date;
import gestionEvenements.Evenement;
import gestionEvenements.TypeEvenement;
import gestionMembres.Membre;

public class Gestion {

  private ArrayList<CodeErreur> codesErreurs;

  /**
   * Liste des �v�nements
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
   * @return la liste des �v�nements
   */
  public ArrayList<Evenement> getListeEvenements() {
    return listeEvenements;
  }

  /**
   * @param liste La liste des �v�nements
   */
  private void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  }

  /**
   * Cr�er un �v�nement.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return l'object l'�v�nement si cr�ation r�ussie, null si erreur de cr�ation
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
   * Ajoute un �v�nement � la liste des �v�nements.
   * 
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code 0} si l'ajout est un succ�s, {@code -1} si erreur de cr�ation de l'�v�nement
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
   * Retourne l'�v�nement de la liste des �v�nements avec son identifiant.
   * 
   * @param unId Identifiant de l'�v�nement
   * @return l'object Evenement si pr�sent dans la liste, si non pr�sent dans la liste
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
   * Supprime �v�nement de la liste des �v�nements avec son identifiant.
   * 
   * @param unId Identifiant de l'�v�nement � supprimer
   * @return {@code 0} si la suppression est un succ�s, {@code -1} si l'�v�nement correspondant �
   *         l'identifiant n'est pas pr�sent dans la liste
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
   * Modifie un �v�nement de la liste des �v�nements.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code 0} si la modification est un succ�s, {@code -1} si l'�v�nement correspondant �
   *         l'identifiant n'est pas pr�sent dans la liste, {@code 1} si erreur de modification de
   *         l'�v�nement
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
   * Cr�e un membre.
   * 
   * @param unPseudo pseudo du membre � cr�er
   * @param unNom nom du membre � cr�er
   * @param unPrenom pr�nom du membre � cr�er
   * @param unLieuNaissance lieu de naissance du membre � cr�er
   * @param uneDateNaissance date de naissance du membre � cr�er
   * @param uneVille ville du membre � cr�er
   * @param unMail adresse mail du membre � cr�er
   * @param unMotDePasse mot de passe du membre � cr�er
   * @return une instance de la classe {@code Membre} la cr�ation est un succ�s, {@code null} sinon
   */
  private Membre creerMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    Membre membre = new Membre();
    int res = 0;

    // D�finition des attributs du membre
    res += membre.setPseudo(unPseudo);
    res += membre.setNom(unNom);
    res += membre.setPrenom(unPrenom);
    res += membre.setLieuNaissance(unLieuNaissance);
    res += membre.setDateNaissance(uneDateNaissance);
    res += membre.setVille(uneVille);
    res += membre.setMail(unMail);
    res += membre.setMotDePasse(unMotDePasse);

    // recup membre.getErreurs();

    // Si une des d�finitions est un �chec, retourne un membre null
    if (res != 0) {
      membre = null;
    }

    return membre;
  }

  /**
   * Ajoute un membre � la liste des membres, s'il n'existe pas d�j�.
   * 
   * @param unPseudo pseudo du membre � ajouter
   * @param unNom nom du membre � ajouter
   * @param unPrenom pr�nom du membre � ajouter
   * @param unLieuNaissance lieu de naissance du membre � ajouter
   * @param uneDateNaissance date de naissance du membre � ajouter
   * @param uneVille ville du membre � ajouter
   * @param unMail adresse mail du membre � ajouter
   * @param unMotDePasse mot de passe du membre � ajouter
   * @return {@code 0} si l'ajout est un succ�s, {@code -1} sinon
   */
  public int ajouterMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    int res = -1;
    Membre membreExistant = this.getMembre(unPseudo);

    // Si le pseudo du membre � ajouter n'existe pas
    if (membreExistant == null) {
      Membre membre = this.creerMembre(unPseudo, unNom, unPrenom, unLieuNaissance, uneDateNaissance,
          uneVille, unMail, unMotDePasse);

      // Si la cr�ation du membre est un succ�s
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
   * @param unPseudo pseudo du membre � supprimer de la liste
   * @return {@code 0} si la suppression est un succ�s, {@code -1} sinon
   */
  public int supprimerMembre(String unPseudo) {
    int res = -1;

    // Si le param�tre n'est pas null
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
   * Modifie les donn�es d'un membre de la liste des membres.
   * 
   * @param unMembre le membre de la liste � modifier
   * @return {@code 0} si la modification est un succ�s, {@code -1} sinon
   */
  public int modifierMembre(Membre unMembre) {
    int res = -1;

    // Si le param�tre n'est pas null
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

        // V�rifie l'�tat de la modification (totale, partielle ou refus�e)
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
   * Permet de retrouver un membre gr�ce � son pseudo.
   * 
   * @param unPseudo le pseudo du membre � r�cup�rer
   * @return le membre correspondant au pseudo, {@code null} sinon
   */
  public Membre getMembre(String unPseudo) {
    Membre membre = null;

    // Si le param�tre n'est pas null
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
