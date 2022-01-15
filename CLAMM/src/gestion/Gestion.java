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
   * Constructeur de la classe {@code Gestion}
   */
  public Gestion() {
    this.setListeMembres(new ArrayList<Membre>());
    this.setListeEvenements(new ArrayList<Evenement>());
    this.setCodesErreurs(new ArrayList<CodeErreur>());
    this.nextId = 1;
  }

  /**
   * @return la liste des �v�nements
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
   * Cr�e un {@code Membre}.
   * 
   * <p>
   * Si une ou plus d�finition(s) des attributs du nouveau membre est un �chec, ajoute le code
   * erreur dans la liste des codes erreurs, et retourne un membre {@code null}.
   * 
   * <p>
   * Les codes erreurs sont les suivants :
   * <ul>
   * <li>- PSEUDO_TROP_COURT : lorsque la taille du pseudo est trop petite</li>
   * <li>- PSEUDO_TROP_LONG : lorsque la taille du pseudo est trop grande</li>
   * <li>- PSEUDO_CARACTERE_INVALIDE : lorsqu'un caract�re du pseudo n'est pas possible</li>
   * <li>- NOM_TROP_LONG : lorsque le nom est trop long</li>
   * <li>- ...</li>
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
   * Les codes erreurs dus aux d�finitions sont les suivants :
   * <ul>
   * <li>- PSEUDO_TROP_COURT : lorsque la taille du pseudo est trop petite</li>
   * <li>- PSEUDO_TROP_LONG : lorsque la taille du pseudo est trop grande</li>
   * <li>- PSEUDO_CARACTERE_INVALIDE : lorsqu'un caract�re du pseudo n'est pas possible</li>
   * <li>- NOM_TROP_LONG : lorsque le nom est trop long</li>
   * <li>- ...</li>
   * 
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
  public ArrayList<CodeErreur> modifierMembre(String unPseudo, String unNom, String unPrenom,
      String unLieuNaissance, Date uneDateNaissance, String uneVille, String unMail,
      String unMotDePasse) {
    ArrayList<CodeErreur> res = null;
    Membre membreAModifier = this.getMembre(unPseudo);
    this.codesErreurs.clear();

    // Si le membre est dans la liste des membres
    if (membreAModifier != null) {
      CodeErreur codeErreur = null;

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
