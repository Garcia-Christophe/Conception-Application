package gestion;

import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;
import gestion.membres.Membre;
import gestion.participation.Participation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Gestion permet de gérer tous les membres et tous les évènements.
 * 
 * <p>
 * La classe peut créer, ajouter, modifier et supprimer des membres et des évènements. Elle contient
 * la liste de tous les membres, la liste de tous les évènements, ainsi que la liste de tous les
 * {@link CodeErreur} survenus lors d'un appel d'une des méthodes.
 * 
 * @author Manon, Christophe
 * @version 2.00
 * @see gestion.membres.Membre
 * @see gestion.evenements.Evenement
 */
public class Gestion {

  /**
   * Instance de la classe Verif, pour la vérification des éléments des Membre et Evenement.
   */
  private Verif uneVerif;

  /**
   * Liste des codes erreurs.
   */
  private ArrayList<CodeErreur> codesErreurs;

  /**
   * Liste des événements.
   */
  private ArrayList<Evenement> listeEvenements;

  /**
   * Prochain identifiant d'un événement.
   */
  private int prochainIdEvenement;

  /**
   * Liste des membres.
   */
  private ArrayList<Membre> listeMembres;

  /**
   * Liste des Participation.
   */
  private ArrayList<Participation> listeParticipations;

  /**
   * Base de données.
   */
  private BaseDeDonnees bdd;

  /**
   * Constructeur de la classe {@code Gestion}.
   * 
   * <p>
   * Initialise les listes de membres, événements et participations à partir des données de la base
   * de données.
   * 
   * @throws SQLException si la création de la {@link BaseDeDonnees} a échoué
   */
  public Gestion() throws SQLException {
    this.uneVerif = new Verif();
    this.prochainIdEvenement = 1;
    ArrayList<CodeErreur> err = new ArrayList<CodeErreur>();
    for (int i = 0; i < 9; i++) {
      err.add(CodeErreur.NO_ERROR);
    }
    this.setCodesErreurs(err);
    this.bdd = new BaseDeDonnees();
    this.setListeMembres(new ArrayList<Membre>());
    this.bdd.initMembre(this);
    this.setListeEvenements(new ArrayList<Evenement>());
    this.bdd.initEvenement(this);
    this.setListeParticipations(new ArrayList<Participation>());
    this.bdd.initParticipation(this);
  }

  /**
   * Instancie la liste des codes d'erreurs avec une liste passée en paramètre.
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
   * Retourne la liste des événements.
   *
   * @return la liste des événements.
   */
  public ArrayList<Evenement> getListeEvenements() {
    return listeEvenements;
  }

  /**
   * Instancie la liste des événements avec une liste passée en paramètre.
   * 
   * @param liste La liste des évènements
   */
  private void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  }

  /**
   * Créer un {@link gestion.evenements.Evenement}.
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
   * @return une nouvelle instance de la classe {@link gestion.evenements.Evenement} si la création
   *         est un succès, {@code null} sinon
   */
  private Evenement creerEvenement(int unId, String unNom, String unDescriptif, String uneImage,
      Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {

    Evenement unEvenement = new Evenement();


    CodeErreur codeErreur = null;

    // Identifiant de l'événement
    codeErreur = this.uneVerif.verifIdEvenement(unId);
    if (codeErreur != null) {
      this.codesErreurs.set(0, codeErreur);
    } else {
      this.codesErreurs.set(0, CodeErreur.NO_ERROR);
      unEvenement.setId(unId);
    }

    // Nom de l'événement
    codeErreur = this.uneVerif.verifNomEvenement(unNom);
    if (codeErreur != null) {
      this.codesErreurs.set(1, codeErreur);
    } else {
      this.codesErreurs.set(1, CodeErreur.NO_ERROR);
      unEvenement.setNom(unNom);
    }

    // Descriptif de l'événement
    codeErreur = this.uneVerif.verifDescriptifEvenement(unDescriptif);
    if (codeErreur != null) {
      this.codesErreurs.set(2, codeErreur);
    } else {
      this.codesErreurs.set(2, CodeErreur.NO_ERROR);
      unEvenement.setDescriptif(unDescriptif);
    }

    // Image de l'événement
    unEvenement.setImage(uneImage);
    this.codesErreurs.set(3, CodeErreur.NO_ERROR);

    // Date de l'événement
    codeErreur = this.uneVerif.verifDateEvenement(uneDate);
    if (codeErreur != null) {
      this.codesErreurs.set(4, codeErreur);
    } else {
      this.codesErreurs.set(4, CodeErreur.NO_ERROR);
      unEvenement.setDate(uneDate);
    }

    // Lieu de l'événement
    codeErreur = this.uneVerif.verifLieuEvenement(unLieu);
    if (codeErreur != null) {
      this.codesErreurs.set(5, codeErreur);
    } else {
      this.codesErreurs.set(5, CodeErreur.NO_ERROR);
      unEvenement.setLieu(unLieu);
    }

    // NbMaxPersonnes de l'événement
    codeErreur = this.uneVerif.verifNbMaxPersonnesEvenement(unNbMaxPersonnes);
    if (codeErreur != null) {
      this.codesErreurs.set(6, codeErreur);
    } else {
      this.codesErreurs.set(6, CodeErreur.NO_ERROR);
      unEvenement.setNbMaxPersonnes(unNbMaxPersonnes);
    }

    // Type de l'événement
    codeErreur = this.uneVerif.verifTypeEvenement(unType);
    if (codeErreur != null) {
      this.codesErreurs.set(7, codeErreur);
    } else {
      this.codesErreurs.set(7, CodeErreur.NO_ERROR);
      unEvenement.setType(unType);
    }

    // Si une des définitions est un échec, retourne un evenement null
    boolean erreur = false;
    for (int i = 0; i < this.codesErreurs.size(); i++) {
      if (this.codesErreurs.get(i) != CodeErreur.NO_ERROR) {
        erreur = true;
      }
    }
    if (erreur) {
      unEvenement = null;
    }

    return unEvenement;
  }

  /**
   * Ajoute un {@link gestion.evenements.Evenement} à la liste des événements et dans la base de
   * données si pas déjà présent.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@link gestion.evenements.Evenement}.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unLieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code null} si l'ajout du nouveau {@link gestion.evenements.Evenement} est un succès,
   *         une liste de {@link CodeErreur} sinon
   */
  public ArrayList<CodeErreur> ajouterEvenement(int unId, String unNom, String unDescriptif,
      String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {

    int id;
    if (unId == -1) {
      id = prochainIdEvenement;
    } else {
      id = unId;
    }
    Evenement unEvenement = creerEvenement(id, unNom, unDescriptif, uneImage, uneDate, unLieu,
        unNbMaxPersonnes, unType);

    ArrayList<CodeErreur> res = null;

    if (unEvenement != null) {
      Evenement evenement = this.getEvenement(unEvenement.getId());
      if (evenement == null) {
        try {
          boolean ajout = true;

          if (this.bdd.evenementPresent(unEvenement.getId()) == false) {
            ajout = this.bdd.ajouterEvenement(unEvenement);
          }

          if (ajout) {
            listeEvenements.add(unEvenement);
            this.codesErreurs.set(8, CodeErreur.NO_ERROR);
          } else {
            this.codesErreurs.set(8, CodeErreur.AJOUT_EVENEMENT_IMPOSSIBLE);
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }

        if (unId == -1) {
          prochainIdEvenement++;
        }
      } else {
        this.codesErreurs.set(0, CodeErreur.ID_DEJA_EXISTANT);
      }
    } else {
      res = getCodesErreurs();
    }

    return res;
  }

  /**
   * Permet de retrouver un {@link gestion.evenements.Evenement} grâce à son identifiant
   * {@code unId}.
   * 
   * @param unId Identifiant de l'événement à retrouver
   * @return l'instance de {@link gestion.evenements.Evenement} correspondant à l'identifiant,
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
   * Supprime un {@link gestion.evenements.Evenement} de la liste des événements et de la base de
   * données si présent.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@link gestion.evenements.Evenement}.
   * 
   * @param unId Identifiant de l'événement à supprimer
   * @return {@code null} si la suppression de l'événement est un succès, une liste de
   *         {@link CodeErreur} sinon
   */
  public ArrayList<CodeErreur> supprimerEvenement(int unId) {
    ArrayList<CodeErreur> res = null;
    Evenement evenementASupprimer = this.getEvenement(unId);

    // Si l'evenement est dans la liste des événements
    if (evenementASupprimer != null) {
      boolean suppression = this.bdd.supprimerEvenement(unId);

      if (suppression) {
        this.listeEvenements.remove(evenementASupprimer); // supprime le membre de la liste
        for (Participation p : this.getListeMembresParticipation(unId)) {
          this.listeParticipations.remove(p);
        }
        this.codesErreurs.set(8, CodeErreur.NO_ERROR);
      } else {
        this.codesErreurs.set(8, CodeErreur.SUPPRESSION_EVENEMENT_IMPOSSIBLE);
      }
    } else {

      this.codesErreurs.set(0, CodeErreur.EVENEMENT_INTROUVABLE);
      res = getCodesErreurs();
    }

    return res;
  }

  /**
   * Modifie un événement, si toutes les modifications sont possibles, de la liste des événements et
   * de la base de données si présent.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link CodeErreur} correspondants à un
   * {@link gestion.evenements.Evenement}.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement
   * @param unLieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code null} si la modification de l'événement est un succès, une liste de
   *         {@link CodeErreur} sinon
   */
  public ArrayList<CodeErreur> modifierEvenement(int unId, String unNom, String unDescriptif,
      String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    ArrayList<CodeErreur> res = null;


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
            boolean modification = this.bdd.modifierEvenement(unId, unEvenement);

            if (modification) {
              listeEvenements.set(i, unEvenement);

              // Modification de l'evenement present dans la liste des participations
              for (Participation p : this.listeParticipations) {
                if (p.getEvenement().getId() == unId) {
                  p.setEvenement(unEvenement);
                }
              }
              this.codesErreurs.set(8, CodeErreur.NO_ERROR);
            } else {
              this.codesErreurs.set(8, CodeErreur.MODIFICATION_EVENEMENT_IMPOSSIBLE);
            }
          }

        }
      }
    } else {
      this.codesErreurs.set(0, CodeErreur.EVENEMENT_INTROUVABLE);
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


  public void setProchainIdEvenement(int prochainIdEvenement) {
    this.prochainIdEvenement = prochainIdEvenement;
  }

  /**
   * Instancie la liste des membres avec une liste passée en paramètre.
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
   * Crée un {@link gestion.membres.Membre}.
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
   * @return une nouvelle instance de la classe {@link gestion.membres.Membre} si la création est un
   *         succès, {@code null} sinon
   */
  private Membre creerMembre(String unPseudo, String unNom, String unPrenom, String unLieuNaissance,
      Date uneDateNaissance, String uneVille, String unMail, String unMotDePasse) {
    Membre membre = new Membre();
    CodeErreur codeErreur = null;

    // Définition du nouveau pseudo du membre
    codeErreur = this.uneVerif.verifPseudoMembre(unPseudo);
    if (codeErreur != null) {
      this.codesErreurs.set(0, codeErreur);
    } else {
      this.codesErreurs.set(0, CodeErreur.NO_ERROR);
      membre.setPseudo(unPseudo);
    }

    // Définition du nouveau nom du membre
    codeErreur = this.uneVerif.verifIdentiteMembre(unNom, 0);
    if (codeErreur != null) {
      this.codesErreurs.set(1, codeErreur);
    } else {
      this.codesErreurs.set(1, CodeErreur.NO_ERROR);
      membre.setNom(unNom);
    }

    // Définition du nouveau prénom du membre
    codeErreur = this.uneVerif.verifIdentiteMembre(unPrenom, 1);
    if (codeErreur != null) {
      this.codesErreurs.set(2, codeErreur);
    } else {
      this.codesErreurs.set(2, CodeErreur.NO_ERROR);
      membre.setPrenom(unPrenom);
    }

    // Définition du nouveau lieu de naissance du membre
    codeErreur = this.uneVerif.verifLieuxMembre(unLieuNaissance, 0);
    if (codeErreur != null) {
      this.codesErreurs.set(3, codeErreur);
    } else {
      this.codesErreurs.set(3, CodeErreur.NO_ERROR);
      membre.setLieuNaissance(unLieuNaissance);
    }

    // Définition de la nouvelle date de naissance du membre
    codeErreur = this.uneVerif.verifDateMembre(uneDateNaissance);
    if (codeErreur != null) {
      this.codesErreurs.set(4, codeErreur);
    } else {
      this.codesErreurs.set(4, CodeErreur.NO_ERROR);
      membre.setDateNaissance(uneDateNaissance);
    }

    // Définition de la nouvelle ville du membre
    codeErreur = this.uneVerif.verifLieuxMembre(uneVille, 1);
    if (codeErreur != null) {
      this.codesErreurs.set(5, codeErreur);
    } else {
      this.codesErreurs.set(5, CodeErreur.NO_ERROR);
      membre.setVille(uneVille);
    }

    // Définition de la nouvelle adresse mail du membre
    codeErreur = this.uneVerif.verifMailMembre(unMail);
    if (codeErreur != null) {
      this.codesErreurs.set(6, codeErreur);
    } else {
      this.codesErreurs.set(6, CodeErreur.NO_ERROR);
      membre.setMail(unMail);
    }

    // Définition du nouveau mot de passe du membre
    codeErreur = this.uneVerif.verifMdpMembre(unMotDePasse);
    if (codeErreur != null) {
      this.codesErreurs.set(7, codeErreur);
    } else {
      this.codesErreurs.set(7, CodeErreur.NO_ERROR);
      membre.setMotDePasse(unMotDePasse);
    }

    // Si une des définitions est un échec, retourne un membre null
    boolean erreur = false;
    for (int i = 0; i < this.codesErreurs.size(); i++) {
      if (this.codesErreurs.get(i) != CodeErreur.NO_ERROR) {
        erreur = true;
      }
    }
    if (erreur) {
      membre = null;
    }
    return membre;
  }

  /**
   * Ajoute un {@link gestion.membres.Membre} seulement s'il n'existe pas déjà, à la liste des
   * membres et à la base de données s'il est pas présent.
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
   * {@link gestion.membres.Membre}.
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
   *         {@link CodeErreur} sinon
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
        try {
          boolean ajout = true;
          if (this.bdd.membrePresent(unPseudo) == false) {
            ajout = this.bdd.ajouterMembre(membre);
          }

          if (ajout) {
            this.listeMembres.add(membre); // ajoute le membre à la liste des membres
            this.codesErreurs.set(8, CodeErreur.NO_ERROR);
          } else {
            this.codesErreurs.set(8, CodeErreur.AJOUT_MEMBRE_IMPOSSIBLE);
          }
        } catch (SQLException e) {
          e.printStackTrace();
        }
      } else {
        res = this.getCodesErreurs(); // renvoie la liste des codes erreurs
      }
    } else {
      this.codesErreurs.set(0, CodeErreur.PSEUDO_DEJA_EXISTANT);
      res = this.getCodesErreurs();
    }

    return res;
  }

  /**
   * Supprime un {@link gestion.membres.Membre} de la liste des membres et de la base de données si
   * présent.
   * 
   * <p>
   * Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la méthode
   * {@link #getMembre(String)}. Si le membre est trouvé, alors le retire de la liste des membres et
   * ne renvoie aucun code erreur, renvoie {@code null}. Si aucun membre n'est trouvé dans la liste
   * des membres, alors renvoie le code erreur {@code gestion.CodeErreur.MEMBRE_INTROUVABLE}.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link gestion.CodeErreur} correspondants
   * à un {@link gestion.membres.Membre}.
   * 
   * @param unPseudo pseudo du membre à supprimer de la liste
   * @return {@code null} si la suppression du membre est un succès, une liste de {@link CodeErreur}
   *         sinon
   */
  public ArrayList<CodeErreur> supprimerMembre(String unPseudo) {
    ArrayList<CodeErreur> res = null;
    Membre membreASupprimer = this.getMembre(unPseudo);

    // Si le membre est dans la liste des membres
    if (membreASupprimer != null) {
      boolean suppression = this.bdd.supprimerMembre(membreASupprimer.getPseudo());

      if (suppression) {
        this.listeMembres.remove(membreASupprimer); // supprime le membre de la liste
        this.codesErreurs.set(8, CodeErreur.NO_ERROR);
      } else {
        this.codesErreurs.set(8, CodeErreur.SUPPRESSION_MEMBRE_IMPOSSIBLE);
      }
    } else {
      this.codesErreurs.set(0, CodeErreur.MEMBRE_INTROUVABLE);
      res = this.getCodesErreurs();
    }

    return res;
  }

  /**
   * Modifie les données d'un {@link gestion.membres.Membre}, si et seulement si toutes les
   * modifications sont des succès, de la liste des membres et de la base de données si présent.
   * 
   * <p>
   * Cherche le membre ayant pour pseudo {@code unPseudo} dans la liste des membres avec la méthode
   * {@link #getMembre(String)}. Si aucun membre n'est trouvé dans la liste des membres, alors
   * renvoie le code erreur {@code gestion.CodeErreur.MEMBRE_INTROUVABLE}. Si le membre est trouvé,
   * alors tente de modifier ses attributs. Si tous les attributs du membre ont été modifiés avec
   * succès, ne renvoie aucun code erreur, renvoie {@code null}, sinon renvoie la liste des codes
   * erreurs correspondantes.
   * 
   * <p>
   * La liste des codes erreurs sont ceux de l'énumération {@link gestion.CodeErreur} correspondants
   * à un {@link gestion.membres.Membre}.
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
   *         {@link CodeErreur} sinon
   */
  public ArrayList<CodeErreur> modifierMembre(String unPseudo, String unNom, String unPrenom,
      String unLieuNaissance, Date uneDateNaissance, String uneVille, String unMail,
      String unMotDePasse) {

    ArrayList<CodeErreur> res = null;

    Membre membreAModifier = this.getMembre(unPseudo);

    // Si le membre est dans la liste des événements
    if (membreAModifier != null) {

      for (int i = 0; i < listeMembres.size(); i++) {
        if (listeMembres.get(i).getPseudo().equals(unPseudo)) {
          Membre unMembre = creerMembre(unPseudo, unNom, unPrenom, unLieuNaissance,
              uneDateNaissance, uneVille, unMail, unMotDePasse);

          if (unMembre == null) {
            res = getCodesErreurs(); // modifications pas possibles
          } else {
            boolean modification = this.bdd.modifierMembre(unPseudo, unMembre);

            if (modification) {
              listeMembres.set(i, unMembre);

              // Modification du membre present dans la liste des participations
              for (Participation p : this.listeParticipations) {
                if (p.getMembre().getPseudo().equals(unPseudo)) {
                  p.setMembre(unMembre);
                }
              }
              this.codesErreurs.set(8, CodeErreur.NO_ERROR);
            } else {
              this.codesErreurs.set(8, CodeErreur.MODIFICATION_MEMBRE_IMPOSSIBLE);
            }
          }
        }
      }
    } else {
      this.codesErreurs.set(0, CodeErreur.MEMBRE_INTROUVABLE);
      res = getCodesErreurs();
    }

    return res;

  }

  /**
   * Permet de retrouver un {@link gestion.membres.Membre} grâce à son pseudo {@code unPseudo}.
   * 
   * <p>
   * Si le pseudo {@code unPseudo} n'est pas {@code null}, alors compare un par un le pseudo de
   * chacun des membres de la liste des membres avec celui passé en paramètre. Le parcours de la
   * liste s'arrête lorsque le membre ayant le même pseudo à été trouvé et renvoie le
   * {@link gestion.membres.Membre}, ou que la liste a été entièrement parcourue et renvoie
   * {@code null}.
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

  /**
   * Retourne la liste des participations.
   * 
   * @return la liste des participations
   */
  public ArrayList<Participation> getListeParticipations() {
    return listeParticipations;
  }

  /**
   * Instancie la liste des participations avec une liste passée en paramètre.
   * 
   * @param uneListeParticipations La liste des participations
   */
  public void setListeParticipations(ArrayList<Participation> uneListeParticipations) {
    this.listeParticipations = uneListeParticipations;
  }

  /**
   * Retourne la liste des participations qui à un événement avec id égale l'id passée paramètre.
   * 
   * @param unId identifiant d'un événement
   * @return la liste des participations qui à un événement avec id égale l'id passée paramètre
   */
  public ArrayList<Participation> getListeMembresParticipation(int unId) {
    ArrayList<Participation> res = new ArrayList<Participation>();
    for (Participation p : this.listeParticipations) {
      if (p.getEvenement().getId() == unId) {
        res.add(p);
      }
    }
    return res;
  }


}
