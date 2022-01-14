package gestionMembres;

import java.util.ArrayList;
import java.util.Date;
import gestionEvenements.FabriqueEvenement;

/**
 * La classe FabriqueMembre permet de centraliser la cr�ation des membres, et de rendre meur
 * instanciation plus flexible que l'utilisation de l'op�rateur d'instanciation {@code new}.
 * 
 * La classe est d�clar�e {@code final} car la construction d'objet est privatis�e, interdisant
 * ainsi l'h�ritage.
 * 
 * @author Christophe
 * @version 1.00
 * @see {@link Membre}, {@link FabriqueEvenement}
 */
public final class FabriqueMembre {

  /**
   * Unique instance de la classe FabriqueMembre (Singleton)
   */
  private static FabriqueMembre fabriqueMembreInstance;

  /**
   * Liste des membres
   */
  private ArrayList<Membre> listeMembres;

  /**
   * Cr�e l'instance du singleton que si elle est requise.
   * 
   * @return l'unique instance de FabriqueMembre
   */
  public static FabriqueMembre getInstance() {
    // Cr�e l'unique instance de la classe si elle n'existe pas
    if (FabriqueMembre.fabriqueMembreInstance == null) {
      FabriqueMembre.fabriqueMembreInstance = new FabriqueMembre();
    }

    return FabriqueMembre.fabriqueMembreInstance;
  }

  /**
   * Constructeur de FabriqueMembre. Cr�e une liste vide de membres.
   */
  private FabriqueMembre() {
    this.setListeMembres(new ArrayList<Membre>());
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
   * @return une instance de la classe {@code Membre} la cr�ation est un sicc�s, {@code null} sinon
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

    // Si une des d�finitions est un �chec, retourne un membre null
    if (res != 0) {
      membre = null;
    }

    return membre;
  }

  /**
   * Ajoute un membre � la liste des membres.
   * 
   * @return {@code 0} si l'ajout est un succ�s, {@code -1} sinon
   */
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

  /**
   * Permet de retrouver un membre gr�ce � son pseudo par recherche dichotomique.
   * 
   * @param unPseudo le pseudo du membre � r�cup�rer
   * @return le membre correspondant au pseudo, {@code null} sinon
   */
  public Membre getMembre2(String unPseudo) {
    Membre membre = null;

    // Si le param�tre n'est pas null
    if (unPseudo != null) {
      boolean membreTrouve = false;
      int debut = 0;
      int fin = this.listeMembres.size() - 1;
      int milieu = (fin + debut) / 2;
      int comparaison = 0;

      while (fin <= debut) {
        comparaison = this.listeMembres.get(milieu).compareTo(unPseudo);
        if (comparaison < 0) {
          fin = milieu + 1;
        } else if (comparaison == 0) {
          membre = this.listeMembres.get(milieu);
        } else {
          debut = milieu - 1;
        }

        // Recalcul du milieu
        milieu = (fin + debut) / 2;
      }
    }

    return membre;
  }

}
