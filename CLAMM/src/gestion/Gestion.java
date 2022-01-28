package gestion;

import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;
import gestion.membres.Membre;
import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Gestion permet de g�rer tous les membres et tous les �v�nements.
 * 
 * <p>La classe peut cr�er, ajouter, modifier et supprimer des membres et des �v�nements. 
 * Elle contient la liste de tous les membres, la liste de tous les �v�nements, ainsi que 
 * la liste de tous les {@link CodeErreur} survenus lors d'un appel d'une des m�thodes.
 * 
 * @author Manon, Christophe
 * @version 2.00
 * @see gestion.membres.Membre
 * @see gestion.evenements.Evenement
 */
public class Gestion {

  /**
   * Instance de la classe Verif, pour la v�rification des �l�ments des Membre et Evenement.
   */
  
  /**
   * Liste des codes erreurs.
   */
  private ArrayList<CodeErreur> codesErreurs;

  /**
   * Liste des �v�nements.
   */
  private ArrayList<Evenement> listeEvenements;

  /**
   * Prochain identifiant d'un �v�nement.
   */
  private int prochainIdEvenement;

  /**
   * Liste des membres.
   */
  private ArrayList<Membre> listeMembres;

  /**
   * Constructeur de la classe {@code Gestion}.
   */
  public Gestion() {
    this.setListeMembres(new ArrayList<Membre>());
    this.setListeEvenements(new ArrayList<Evenement>());
    this.setCodesErreurs(new ArrayList<CodeErreur>());
    this.prochainIdEvenement = 1;
  }

  /**
   * Instancie la liste des codes d'erreurs avec une liste pass�e en param�tre.
   * 
   * @param uneListe La liste des codes erreurs.
   */
  private void setCodesErreurs(ArrayList<CodeErreur> uneListe) {
    if (uneListe != null) {
      this.codesErreurs = uneListe;
    }
  }

  /**
   * Retourne la liste des codes d'erreurs.
   * 
   * @return la liste des codes erreurs.
   */
  public ArrayList<CodeErreur> getCodesErreurs() {
    return this.codesErreurs;
  }

  /**
   * Retourne la liste des �v�nements.
   *
   * @return la liste des �v�nements.
   */
  public ArrayList<Evenement> getListeEvenements() {
    return listeEvenements;
  }

  /**
   * Instancie la liste des �v�nements avec une liste pass�e en param�tre.
   * 
   * @param liste La liste des �v�nements
   */
  private void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  }

  /**
   * Cr�er un {@link gestion.evenements.Evenement}.
   * 
   * <p>Si une ou plus d�finition(s) des attributs du nouveau evenement est un �chec, 
   * le code erreur est ajout� dans la liste des codes erreurs, et retourne un membre 
   * {@code null}.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return une nouvelle instance de la classe {@link gestion.evenements.Evenement} si la cr�ation
   *         est un succ�s, {@code null} sinon
   */
  private Evenement creerEvenement(int unId, String unNom, String unDescriptif, String uneImage,
      Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {

    Evenement unEvenement = new Evenement();

    this.codesErreurs.clear();

    CodeErreur codeErreur = null;

    // Identifiant de l'�v�nement
    codeErreur = this.uneVerif.verifId(unId);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      unEvenement.setId(unId);
    }

    // Nom de l'�v�nement
    codeErreur = this.uneVerif.verifNomEvenement(unNom);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      unEvenement.setNom(unNom);
    }

    // Descriptif de l'�v�nement
    codeErreur = this.uneVerif.verifDescriptifEvenement(unDescriptif);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      unEvenement.setDescriptif(unDescriptif);
    }

    // Image de l'�v�nement
    unEvenement.setImage(uneImage);

    // Date de l'�v�nement
    codeErreur = this.uneVerif.verifDateEvenement(uneDate);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      unEvenement.setDate(uneDate);
    }

    // Lieu de l'�v�nement
    codeErreur = this.uneVerif.verifLieuEvenement(unLieu);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      unEvenement.setLieu(unLieu);
    }

    // NbMaxPersonnes de l'�v�nement
    codeErreur = this.uneVerif.verifNbMaxPersonnesEvenement(unNbMaxPersonnes);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      unEvenement.setNbMaxPersonnes(unNbMaxPersonnes);
    }

    // Type de l'�v�nement
    codeErreur = this.uneVerif.verifTypeEvenement(unType);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      unEvenement.setType(unType);
    }

    // Si une des d�finitions est un �chec, retourne un evenement null
    if (!this.codesErreurs.isEmpty()) {
      unEvenement = null;
    }

    return unEvenement;
  }

  /**
   * Ajoute un {@link gestion.evenements.Evenement} � la liste des �v�nements.
   * 
   * <p>La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@link gestion.evenements.Evenement}.
   * 
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unLieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code null} si l'ajout du nouveau {@link gestion.evenements.Evenement} est un succ�s,
   *         une liste de {@link CodeErreur} sinon
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
   * Permet de retrouver un {@link gestion.evenements.Evenement} gr�ce � son identifiant
   * {@code unId}.
   * 
   * @param unId Identifiant de l'�v�nement � retrouver
   * @return l'instance de {@link gestion.evenements.Evenement} correspondant � l'identifiant,
   *         {@code null} sinon
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
   * Supprime un {@link gestion.evenements.Evenement} de la liste des �v�nements.
   * 
   * <p>La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@link gestion.evenements.Evenement}.
   * 
   * @param unId Identifiant de l'�v�nement � supprimer
   * @return {@code null} si la suppression de l'�v�nement est un succ�s, une liste de
   *         {@link CodeErreur} sinon
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
   * <p>La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@link gestion.evenements.Evenement}.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement
   * @param unLieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code null} si la modification de l'�v�nement est un succ�s, une liste de
   *         {@link CodeErreur} sinon
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
   * Retourne la liste des membres.
   * 
   * @return la liste des membres
   */
  public ArrayList<Membre> getListeMembres() {
    return this.listeMembres;
  }

  /**
   * Instancie la liste des membres avec une liste pass�e en param�tre.
   * 
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
   * Cr�e un {@link gestion.membres.Membre}.
   * 
   * <p>Si une ou plus d�finition(s) des attributs du nouveau membre est un �chec, ajoute le code
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
   * @return une nouvelle instance de la classe {@link gestion.membres.Membre} si la cr�ation est un
   *         succ�s, {@code null} sinon
   */
  private Membre creerMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    Membre membre = new Membre();
    this.codesErreurs.clear();
    CodeErreur codeErreur = null;

    // D�finition du nouveau pseudo du membre
    codeErreur = this.uneVerif.verifPseudo(unPseudo);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setPseudo(unPseudo);
    }

    // D�finition du nouveau nom du membre
    codeErreur = this.uneVerif.verifIdentiteMembre(unNom,0);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setNom(unNom);
    }

    // D�finition du nouveau pr�nom du membre
    codeErreur = this.uneVerif.verifIdentiteMembre(unPrenom,1);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setPrenom(unPrenom);
    }

    // D�finition du nouveau lieu de naissance du membre
    codeErreur = this.uneVerif.verifLieuxMembre(unLieuNaissance,0);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setLieuNaissance(unLieuNaissance);
    }

    // D�finition de la nouvelle date de naissance du membre
    codeErreur = this.uneVerif.verifDateMembre(uneDateNaissance);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setDateNaissance(uneDateNaissance);
    }

    // D�finition de la nouvelle ville du membre
    codeErreur = this.uneVerif.verifLieuxMembre(uneVille,1);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setVille(uneVille);
    }

    // D�finition de la nouvelle adresse mail du membre
    codeErreur = this.uneVerif.verifMailMembre(unMail);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setMail(unMail);
    }

    // D�finition du nouveau mot de passe du membre
    codeErreur = this.uneVerif.verifMdpMembre(unMotDePasse);
    if (codeErreur != null) {
      this.codesErreurs.add(codeErreur);
    } else {
      membre.setMotDePasse(unMotDePasse);
    }

    // Si une des d�finitions est un �chec, retourne un membre null
    if (!this.codesErreurs.isEmpty()) {
      membre = null;
    }

    return membre;
  }

  /**
   * Ajoute un {@link gestion.membres.Membre} � la liste des membres, seulement s'il n'existe pas
   * d�j�.
   * 
   * <p>Avec la m�thode {@link #getMembre(String)}, on r�cup�re le membre d�j� existant dans la 
   * liste des membres � partir du pseudo {@code unPseudo}. Si la valeur n'est pas {@code null}, 
   * alors le code erreur {@code CodeErreur.PSEUDO_DEJA_EXISTANT} est renvoy�. Sinon, un appel �
   * {@link #creerMembre(String, String, String, String, Date, String, String, String)} est r�alis�
   * pour cr�er le membre avant de l'ajouter dans la liste des membres.
   * 
   * <p>La liste des codes erreurs sont ceux de l'�num�ration {@link CodeErreur} correspondants � un
   * {@link gestion.membres.Membre}.
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
   *         {@link CodeErreur} sinon
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
      res = this.getCodesErreurs();
    }

    return res;
  }

  /**
   * Supprime un {@link gestion.membres.Membre} de la liste des membres.
   * 
   * <p>Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la 
   * m�thode {@link #getMembre(String)}. Si le membre est trouv�, alors le retire de la liste des 
   * membres et ne renvoie aucun code erreur, renvoie {@code null}. Si aucun membre n'est trouv� 
   * dans la liste des membres, alors renvoie le code erreur 
   * {@code gestion.CodeErreur.MEMBRE_INTROUVABLE}.
   * 
   * <p>La liste des codes erreurs sont ceux de l'�num�ration {@link gestion.CodeErreur} 
   * correspondants � un {@link gestion.membres.Membre}.
   * 
   * @param unPseudo pseudo du membre � supprimer de la liste
   * @return {@code null} si la suppression du membre est un succ�s, une liste de {@link CodeErreur}
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
      res = this.getCodesErreurs();
    }

    return res;
  }

  /**
   * Modifie les donn�es d'un {@link gestion.membres.Membre} de la liste des membres, si et
   * seulement si toutes les modifications sont des succ�s.
   * 
   * <p>Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la 
   * m�thode {@link #getMembre(String)}. Si aucun membre n'est trouv� dans la liste des membres, 
   * alors renvoie le code erreur {@code gestion.CodeErreur.MEMBRE_INTROUVABLE}. Si le membre est 
   * trouv�, alors tente de modifier ses attributs. Si tous les attributs du membre ont �t� 
   * modifi�s avec succ�s, ne renvoie aucun code erreur, renvoie {@code null}, sinon renvoie 
   * la liste des codes erreurs correspondantes.
   * 
   * <p>La liste des codes erreurs sont ceux de l'�num�ration {@link gestion.CodeErreur} 
   * correspondants � un {@link gestion.membres.Membre}.
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
   *         {@link CodeErreur} sinon
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
        codeErreur = this.uneVerif.verifPseudo(unPseudo);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setPseudo(unPseudo);
        }

        // D�finition du nouveau nom du membre
        codeErreur = this.uneVerif.verifIdentiteMembre(unNom,0);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setNom(unNom);
        }

        // D�finition du nouveau pr�nom du membre
        codeErreur = this.uneVerif.verifIdentiteMembre(unPrenom,1);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setPrenom(unPrenom);
        }

        // D�finition du nouveau lieu de naissance du membre
        codeErreur = this.uneVerif.verifLieuxMembre(unLieuNaissance,0);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setLieuNaissance(unLieuNaissance);
        }

        // D�finition de la nouvelle date de naissance du membre
        codeErreur = this.uneVerif.verifDateMembre(uneDateNaissance);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setDateNaissance(uneDateNaissance);
        }

        // D�finition de la nouvelle ville du membre
        codeErreur = this.uneVerif.verifLieuxMembre(uneVille,1);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setVille(uneVille);
        }

        // D�finition de la nouvelle adresse mail du membre
        codeErreur = this.uneVerif.verifMailMembre(unMail);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setMail(unMail);
        }

        // D�finition du nouveau mot de passe du membre
        codeErreur = this.uneVerif.verifMdpMembre(unMotDePasse);
        if (codeErreur != null) {
          this.codesErreurs.add(codeErreur);
        } else {
          membreAModifier.setMotDePasse(unMotDePasse);
        }

        // Si une des d�finitions est un �chec, retourne la liste des codes erreurs
        if (!this.codesErreurs.isEmpty()) {
          res = this.getCodesErreurs();
        }
      } else {
        this.codesErreurs.add(CodeErreur.PSEUDO_DEJA_EXISTANT);
        res = this.getCodesErreurs();
      }
    } else {
      this.codesErreurs.add(CodeErreur.MEMBRE_INTROUVABLE);
      res = this.getCodesErreurs();
    }

    return res;
  }

  /**
   * Permet de retrouver un {@link gestion.membres.Membre} gr�ce � son pseudo {@code unPseudo}.
   * 
   * <p>Si le pseudo {@code unPseudo} n'est pas {@code null}, alors compare un par un le pseudo de
   * chacun des membres de la liste des membres avec celui pass� en param�tre. Le parcours de la
   * liste s'arr�te lorsque le membre ayant le m�me pseudo � �t� trouv� et renvoie le
   * {@link gestion.membres.Membre}, ou que la liste a �t� enti�rement parcourue et renvoie
   * {@code null}.
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
