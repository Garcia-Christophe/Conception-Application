package gestion;

import java.util.ArrayList;
import java.util.Date;
import gestionEvenements.Evenement;
import gestionEvenements.TypeEvenement;
import gestionMembres.Membre;

/**
 * La classe Gestion permet de g�rer tous les membres et tous les �v�nements.
 * 
 * <p>
 * La classe peut cr�er, ajouter, modifier et supprimer des membres et des �v�nements. Elle contient
 * la liste de tous les membres, la liste de tous les �v�nements, ainsi que la liste de tous les
 * {@code CodeErreur} survenus lors d'un appel d'une des m�thodes.
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
   * Liste des �v�nements
   */
  private ArrayList<Evenement> listeEvenements;

  /**
   * Prochain identifiant d'un �v�nement
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
   * Cr�er un {@code Evenement}.
   * 
   * <p>
   * Si une ou plus d�finition(s) des attributs du nouveau evenement est un �chec, le code erreur
   * est ajout� dans la liste des codes erreurs, et retourne un membre {@code null}.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return une nouvelle instance de la classe {@code Evenement} si la cr�ation est un succ�s,
   *         {@code null} sinon
   */
  private Evenement creerEvenement(int unId, String unNom, String unDescriptif, String uneImage,
      Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {

    Evenement unEvenement = new Evenement();

    this.codesErreurs.clear();

    CodeErreur codeErreur = null;

    // Identifiant de l'�v�nement
    codeErreur = unEvenement.setId(unId);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Nom de l'�v�nement
    codeErreur = unEvenement.setNom(unNom);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Descriptif de l'�v�nement
    codeErreur = unEvenement.setDescriptif(unDescriptif);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Image de l'�v�nement
    codeErreur = unEvenement.setImage(uneImage);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Date de l'�v�nement
    codeErreur = unEvenement.setDate(uneDate);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Lieu de l'�v�nement
    codeErreur = unEvenement.setLieu(unLieu);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // NbMaxPersonnes de l'�v�nement
    codeErreur = unEvenement.setNbMaxPersonnes(unNbMaxPersonnes);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Type de l'�v�nement
    codeErreur = unEvenement.setType(unType);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Si une des d�finitions est un �chec, retourne un evenement null
    if (!this.codesErreurs.isEmpty()) {
      unEvenement = null;
    }

    return unEvenement;
  }

  /**
   * Ajoute un {@code Evenement} � la liste des �v�nements.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@code Evenement}.
   * 
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code null} si l'ajout du nouveau membre est un succ�s, une liste de
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
   * Permet de retrouver un {@code Evenement} gr�ce � son identifiant {@code unId}.
   * 
   * @param unId Identifiant de l'�v�nement � retrouver
   * @return l'instance de {@code Evenement} correspondant � l'identifiant, {@code null} sinon
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
   * Supprime un {@code Evenement} de la liste des �v�nements.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@code Evenement}.
   * 
   * @param unId Identifiant de l'�v�nement � supprimer
   * @return {@code null} si la suppression de l'�v�nement est un succ�s, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> supprimerEvenement(int unId) {
    ArrayList<CodeErreur> res = null;
    Evenement evenementASupprimer = this.getEvenement(unId);

    // Si l'evenement est dans la liste des �v�nements
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
   * Modifie un �v�nement de la liste des �v�nements si toutes les modifications sont possibles.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@code Evenement}.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code null} si la modification de l'�v�nement est un succ�s, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> modifierEvenement(int unId, String unNom, String unDescriptif,
      String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    ArrayList<CodeErreur> res = null;
    this.codesErreurs.clear();

    Evenement evenementAModifier = this.getEvenement(unId);

    // Si l'evenement est dans la liste des �v�nements
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
   * Cr�e un {@code Membre}.
   * 
   * <p>
   * Si une ou plus d�finition(s) des attributs du nouveau membre est un �chec, ajoute le code
   * erreur dans la liste des codes erreurs, et retourne un membre {@code null}.
   * 
   * @param unPseudo pseudo du membre � cr�er
   * @param unNom nom du membre � cr�er
   * @param unPrenom pr�nom du membre � cr�er
   * @param unLieuNaissance lieu de naissance du membre � cr�er
   * @param uneDateNaissance date de naissance du membre � cr�er
   * @param uneVille ville du membre � cr�er
   * @param unMail adresse mail du membre � cr�er
   * @param unMotDePasse mot de passe du membre � cr�er
   * @return une nouvelle instance de la classe {@code Membre} si la cr�ation est un succ�s,
   *         {@code null} sinon
   */
  private Membre creerMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    Membre membre = new Membre();
    this.codesErreurs.clear();
    CodeErreur codeErreur = null;

    // D�finition du nouveau pseudo du membre
    codeErreur = membre.setPseudo(unPseudo);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // D�finition du nouveau nom du membre
    codeErreur = membre.setNom(unNom);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // D�finition du nouveau pr�nom du membre
    codeErreur = membre.setPrenom(unPrenom);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // D�finition du nouveau lieu de naissance du membre
    codeErreur = membre.setLieuNaissance(unLieuNaissance);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // D�finition de la nouvelle date de naissance du membre
    codeErreur = membre.setDateNaissance(uneDateNaissance);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // D�finition de la nouvelle ville du membre
    codeErreur = membre.setVille(uneVille);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // D�finition de la nouvelle adresse mail du membre
    codeErreur = membre.setMail(unMail);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // D�finition du nouveau mot de passe du membre
    codeErreur = membre.setMotDePasse(unMotDePasse);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    }

    // Si une des d�finitions est un �chec, retourne un membre null
    if (!this.codesErreurs.isEmpty()) {
      membre = null;
    }

    return membre;
  }

  /**
   * Ajoute un {@code Membre} � la liste des membres, seulement s'il n'existe pas d�j�.
   * 
   * <p>
   * Avec la m�thode {@link #getMembre(String)}, on r�cup�re le membre d�j� existant dans la liste
   * des membres � partir du pseudo {@code unPseudo}. Si la valeur n'est pas {@code null}, alors le
   * code erreur {@code CodeErreur.PSEUDO_DEJA_EXISTANT} est renvoy�. Sinon, un appel �
   * {@link #creerMembre(String, String, String, String, Date, String, String, String)} est r�alis�
   * pour cr�er le membre avant de l'ajouter dans la liste des membres.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@code Membre}.
   * 
   * @param unPseudo pseudo du nouveau membre
   * @param unNom nom du nouveau membre
   * @param unPrenom pr�nom du nouveau membre
   * @param unLieuNaissance lieu de naissance du nouveau membre
   * @param uneDateNaissance date de naissance du nouveau membre
   * @param uneVille ville du nouveau membre
   * @param unMail adresse mail du nouveau membre
   * @param unMotDePasse mot de passe du nouveau membre
   * @return {@code null} si l'ajout du nouveau membre est un succ�s, une liste de
   *         {@code CodeErreur} sinon
   */
  public ArrayList<CodeErreur> ajouterMembre(String unPseudo, String unNom, String unPrenom,
      String unLieuNaissance, Date uneDateNaissance, String uneVille, String unMail,
      String unMotDePasse) {
    ArrayList<CodeErreur> res = null;
    Membre membre = this.getMembre(unPseudo);

    // Si aucun membre de la liste des membres ne poss�de le m�me pseudo
    if (membre == null) {
      membre = this.creerMembre(unPseudo, unNom, unPrenom, unLieuNaissance, uneDateNaissance,
          uneVille, unMail, unMotDePasse);

      // Si la cr�ation du membre est un succ�s
      if (membre != null) {
        this.listeMembres.add(membre); // ajoute le membre � la liste des membres
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
   * Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la m�thode
   * {@link #getMembre(String)}. Si le membre est trouv�, alors le retire de la liste des membres et
   * ne renvoie aucun code erreur, renvoie {@code null}. Si aucun membre n'est trouv� dans la liste
   * des membres, alors renvoie le code erreur {@code CodeErreur.MEMBRE_INTROUVABLE}.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@code Membre}.
   * 
   * @param unPseudo pseudo du membre � supprimer de la liste
   * @return {@code null} si la suppression du membre est un succ�s, une liste de {@code CodeErreur}
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
   * Modifie les donn�es d'un membre de la liste des membres, si et seulement si toutes les
   * modifications sont des succ�s.
   * 
   * <p>
   * Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la m�thode
   * {@link #getMembre(String)}. Si aucun membre n'est trouv� dans la liste des membres, alors
   * renvoie le code erreur {@code CodeErreur.MEMBRE_INTROUVABLE}. Si le membre est trouv�, alors
   * tente de modifier ses attributs. Si tous les attributs du membre ont �t� modifi�s avec succ�s,
   * ne renvoie aucun code erreur, renvoie {@code null}, sinon renvoie la liste des codes erreurs
   * correspondantes.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@code Membre}.
   * 
   * @param ancienPseudo ancien pseudo du membre modifier
   * @param unPseudo nouveau pseudo du membre
   * @param unNom nouveau nom du membre
   * @param unPrenom nouveau pr�nom du membre
   * @param unLieuNaissance nouveua lieu de naissance du membre
   * @param uneDateNaissance nouvelle date de naissance du membre
   * @param uneVille nouvelle ville du membre
   * @param unMail nouvelle adresse mail du membre
   * @param unMotDePasse nouveau mot de passe du membre
   * @return {@code null} si la modification du membre est un succ�s, une liste de
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

      // D�finitions des nouveaux attributs si le pseudo est unique
      if (this.getMembre(unPseudo) == null
          || (ancienPseudo != null && unPseudo != null && ancienPseudo.equals(unPseudo))) {
        // D�finition du nouveau pseudo du membre
        codeErreur = membreAModifier.setPseudo(unPseudo);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // D�finition du nouveau nom du membre
        codeErreur = membreAModifier.setNom(unNom);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // D�finition du nouveau pr�nom du membre
        codeErreur = membreAModifier.setPrenom(unPrenom);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // D�finition du nouveau lieu de naissance du membre
        codeErreur = membreAModifier.setLieuNaissance(unLieuNaissance);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // D�finition de la nouvelle date de naissance du membre
        codeErreur = membreAModifier.setDateNaissance(uneDateNaissance);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // D�finition de la nouvelle ville du membre
        codeErreur = membreAModifier.setVille(uneVille);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // D�finition de la nouvelle adresse mail du membre
        codeErreur = membreAModifier.setMail(unMail);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // D�finition du nouveau mot de passe du membre
        codeErreur = membreAModifier.setMotDePasse(unMotDePasse);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        }

        // Si une des d�finitions est un �chec, retourne la liste des codes erreurs
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
   * Permet de retrouver un membre gr�ce � son pseudo {@code unPseudo}.
   * 
   * <p>
   * Si le pseudo {@code unPseudo} n'est pas {@code null}, alors compare un par un le pseudo de
   * chacun des membres de la liste des membres avec celui pass� en param�tre. Le parcours de la
   * liste s'arr�te lorsque le membre ayant le m�me pseudo � �t� trouv� et renvoie le
   * {@code Membre}, ou que la liste a �t� enti�rement parcourue et renvoie {@code null}.
   * 
   * @param unPseudo le pseudo du membre � r�cup�rer
   * @return l'instance de {@code Membre} correspondant au pseudo, {@code null} sinon
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
          membreTrouve = true; // membre trouv�, stoppe le parcours de la liste des membres
        }

        i++; // passe au membre suivant
      }
    }

    return membre;
  }

}
