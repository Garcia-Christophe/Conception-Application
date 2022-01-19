package gestion.membres;

import gestion.CodeErreur;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe Membre représente un membre d’association, caractérisé par son nom, son prénom, un
 * mail, un pseudo, un mot de passe, sa ville de résidence, sa date et son lieu de naissance. Le
 * pseudo est unique.
 * 
 * @author Mathis
 * @version 1.00
 */
public class Membre {

  /**
   * Taille maximale d'un pseudo.
   */
  private final int TAILLE_MAX_PSEUDO = 30;

  /**
   * Taille maximale d'une ville.
   */
  private final int TAILLE_MAX_VILLE = 50;

  /**
   * Taille maximale d'un nom.
   */
  private final int TAILLE_MAX_NOM = 30;

  /**
   * Taille maximale d'un prénom.
   */
  private final int TAILLE_MAX_PRENOM = 30;

  /**
   * Âge maximum d'un être humain.
   */
  private final int AGE_MAXIMUM = 130;

  /**
   * Taille maximale d'un Mot de passe.
   */
  private final int TAILLE_MAX_MDP = 30;

  /**
   * Taille minimale d'un Mot de passe.
   */
  private final int TAILLE_MIN_MDP = 6;

  /**
   * Pseudo du membre.
   */
  private String pseudo;

  /**
   * Nom de famille du membre.
   */
  private String nom;

  /**
   * Prénom du membre.
   */
  private String prenom;

  /**
   * Lieu de naissance du membre.
   */
  private String lieuNaissance;

  /**
   * Date de naissance du membre.
   */
  private Date dateNaissance;

  /**
   * Lieu de résidence du membre.
   */
  private String ville;

  /**
   * Adresse mail du membre.
   */
  private String mail;

  /**
   * Mot de passe du membre.
   */
  private String motDePasse;

  /**
   * Constructeur de Membre.
   */
  public Membre() {}

  /**
   * Retourne le pseudo du membre.
   * 
   * @return le pseudo du membre
   */
  public String getPseudo() {
    return pseudo;
  }

  /**
   * Change le pseudo du membre par unPseudo si celui-ci n'est pas null ou vide, et que le pseudo ne
   * contient pas: espace,<,>,|,:,",*,?,/,\ .
   *
   * @param unPseudo le nouveau pseudo du membre
   * @return null en cas de succès, PSEUDO_TROP_LONG si le pseudo est trop long,
   *         CodeErreur.PSEUDO_NULL si le pseudo est null, CodeErreur.PSEUDO_VIDE si le pseudo est
   *         vide et CodeErreur.PSEUDO_INVALIDE si le pseudo contient : espace,<,>,|,:,",*,?,/,\
   */
  public CodeErreur setPseudo(String unPseudo) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et le pseudo n'est pas vide
    if (unPseudo == null) {
      res = CodeErreur.PSEUDO_NULL;
    } else if (unPseudo.length() == 0) {
      res = CodeErreur.PSEUDO_VIDE;
    } else if (unPseudo.length() > this.TAILLE_MAX_PSEUDO) {
      res = CodeErreur.PSEUDO_TROP_LONG;
    } else {
      int i = 0;

      // Vérifie tous les cas interdits
      while (i < unPseudo.length()) {
        if (unPseudo.charAt(i) == ' ' || unPseudo.charAt(i) == '<' || unPseudo.charAt(i) == '>'
            || unPseudo.charAt(i) == '|' || unPseudo.charAt(i) == ':' || unPseudo.charAt(i) == '"'
            || unPseudo.charAt(i) == '*' || unPseudo.charAt(i) == '?' || unPseudo.charAt(i) == '/'
            || unPseudo.charAt(i) == '\\') {
          res = CodeErreur.PSEUDO_INVALIDE;
        }

        i++;
      }
    }

    if (res == null) {
      this.pseudo = unPseudo;
    }

    return res;
  }

  /**
   * Retourne le nom du membre.
   * 
   * @return le nom du membre
   */
  public String getNom() {
    return nom;
  }

  /**
   * Change le nom du membre par unNom si celui-ci n'est pas null ou vide, et que le nom contient
   * uniquement les lettres de l'alphabet.
   *
   * @param unNom le nom du membre
   * @return null en cas de succès, CodeErreur.NOM_TROP_GRAND si le nom est trop grand,
   *         CodeErreur.NOM_NULL si le nom est null, CodeErreur.NOM_VIDE si le nom est vide et
   *         CodeErreur.NOM_HORS_ALPHABET si le nom contient des caractères autres que les lettres
   *         de l'alphabet
   */
  public CodeErreur setNom(String unNom) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et le nom n'est pas vide
    if (unNom == null) {
      res = CodeErreur.NOM_NULL;
    } else if (unNom.length() == 0) {
      res = CodeErreur.NOM_VIDE;
    } else if (unNom.length() <= TAILLE_MAX_NOM) {
      int i = 0;

      // Vérifie tous les cas autorisés
      while (i < unNom.length()) {
        if (Character.isDigit(unNom.charAt(i))) {
          res = CodeErreur.NOM_HORS_ALPHABET;
        }

        i++;
      }

      if (!unNom.matches("[a-zA-Z]+")) {
        res = CodeErreur.NOM_HORS_ALPHABET;
      }
    } else {
      res = CodeErreur.NOM_TROP_GRAND;
    }

    if (res == null) {
      this.nom = unNom;
    }

    return res;
  }

  /**
   * Retourne le prénom du membre.
   * 
   * @return le prénom du membre
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * Change le prénom du membre par unPrenom si celui-ci n'est pas null ou vide, et que le prénom
   * contient uniquement les lettres de l'alphabet.
   *
   * @param unPrenom le prenom du membre
   * @return null en cas de succès, CodeErreur.PRENOM_TROP_GRAND si le prenom est trop grand,
   *         CodeErreur.PRENOM_NULL si le prenom est null, et CodeErreur.PRENOM_HORS_ALPHABET si le
   *         prenom contient des caractères autres que les lettres de l'alphabet
   */
  public CodeErreur setPrenom(String unPrenom) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et le prenom n'est pas vide
    if (unPrenom == null) {
      res = CodeErreur.PRENOM_NULL;
    } else if (unPrenom.length() == 0) {
      res = CodeErreur.PRENOM_VIDE;
    } else if (unPrenom.length() <= TAILLE_MAX_PRENOM) {
      int i = 0;

      // Vérifie tous les cas autorisés
      while (i < unPrenom.length()) {
        if (Character.isDigit(unPrenom.charAt(i))) {
          res = CodeErreur.PRENOM_HORS_ALPHABET;
        }

        i++;
      }

      if (!unPrenom.matches("[a-zA-Z]+")) {
        res = CodeErreur.PRENOM_HORS_ALPHABET;
      }
    } else {
      res = CodeErreur.PRENOM_TROP_GRAND;
    }

    if (res == null) {
      this.prenom = unPrenom;
    }

    return res;
  }

  /**
   * Retourne le lieu de naissance du membre.
   * 
   * @return le lieu de naissance du membre
   */
  public String getLieuNaissance() {
    return lieuNaissance;
  }

  /**
   * Change le lieu de naissance d'un membre par unLieuNaissance si celle-ci n'est pas null, sinon
   * il est ajouté.
   * 
   * @param unLieuNaissance le lieu de naissance du membre
   * @return null si la définition du lieu de naissance est un succès, CodeErreur.LIEU_NULL si le
   *         lieu de naissance est null, CodeErreur.LIEU_NOM_INCORRECT si le nom du lieu de
   *         naissance comporte un caractère spécial, CodeErreur.LIEU_TAILLE_INCORRECTE si le lieu
   *         de naissance ne comprend pas le nombre de caractères requis
   */
  public CodeErreur setLieuNaissance(String unLieuNaissance) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et la ville n'est pas vide
    if (unLieuNaissance == null) {
      res = CodeErreur.LIEU_NULL;
    } else if (unLieuNaissance.length() <= TAILLE_MAX_VILLE && unLieuNaissance.length() > 0) {
      int i = 0;

      // Vérifie tous les cas interdits
      while (i < unLieuNaissance.length()) {
        if (Character.isDigit(unLieuNaissance.charAt(i)) || unLieuNaissance.charAt(i) == ' '
            || unLieuNaissance.charAt(i) == '<' || unLieuNaissance.charAt(i) == '>'
            || unLieuNaissance.charAt(i) == '|' || unLieuNaissance.charAt(i) == ':'
            || unLieuNaissance.charAt(i) == '"' || unLieuNaissance.charAt(i) == '*'
            || unLieuNaissance.charAt(i) == '?' || unLieuNaissance.charAt(i) == '/'
            || unLieuNaissance.charAt(i) == '\\') {
          res = CodeErreur.LIEU_NOM_INCORRECT;
        }

        i++;
      }
    } else {
      res = CodeErreur.LIEU_TAILLE_INCORRECTE;
    }

    if (res == null) {
      this.lieuNaissance = unLieuNaissance;
    }

    return res;
  }

  /**
   * Retourne la date de naissance du membre.
   * 
   * @return la date de naissance du membre
   */
  public Date getDateNaissance() {
    return dateNaissance;
  }

  /**
   * Change la date de naissance du membre par uneDateNaissance si celle-ci n'est pas null, sinon
   * elle est ajoutée.
   *
   * @param uneDateNaissance la date de naissance du membre
   * @return null si la définition de la date de naissance est un succès, Code_Erreur.DATE_NULL si
   *         la date est null, CodeErreur.DATE_IMPOSSIBLE si la date de naissance est plus grande
   *         que la date courante, CodeErreur.DATE_AGE_IMPOSSIBLE si l'âge du membre est supérieur à
   *         AGE_MAXIMUM ans
   */
  public CodeErreur setDateNaissance(Date uneDateNaissance) {
    CodeErreur res = null;
    Date dateCourante = new Date();

    if (uneDateNaissance == null) {
      res = CodeErreur.DATE_NULL;
    } else if (uneDateNaissance.after(dateCourante)) {
      res = CodeErreur.DATE_IMPOSSIBLE;
    } else if (dateCourante.getYear() - uneDateNaissance.getYear() > this.AGE_MAXIMUM) {
      res = CodeErreur.DATE_AGE_IMPOSSIBLE;
    }

    if (res == null) {
      this.dateNaissance = uneDateNaissance;
    }

    return res;
  }

  /**
   * Retourne la ville du membre.
   * 
   * @return la ville du membre
   */
  public String getVille() {
    return ville;
  }

  /**
   * Change la ville de résidence d'un membre par uneVille si celle-ci n'est pas null, sinon elle
   * est ajoutée.
   * 
   * @param uneVille la ville du membre
   * @return null si la définition de la ville est un succès, CodeErreur.VILLE_NOM_NULL si la ville
   *         est null, CodeErreur.VILLE_NOM_INCORRECT si le nom de la ville comporte un caractère
   *         spécial, CodeErreur.VILLE_TAILLE_INCORRECTE si la ville ne comprend pas le nombre de
   *         caractères requis
   */
  public CodeErreur setVille(String uneVille) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et la ville n'est pas vide
    if (uneVille == null) {
      res = CodeErreur.VILLE_NULL;
    } else if (uneVille.length() <= TAILLE_MAX_VILLE && uneVille.length() > 0) {
      int i = 0;

      // Vérifie tous les cas interdits
      while (i < uneVille.length()) {
        if (Character.isDigit(uneVille.charAt(i)) || uneVille.charAt(i) == ' '
            || uneVille.charAt(i) == '<' || uneVille.charAt(i) == '>' || uneVille.charAt(i) == '|'
            || uneVille.charAt(i) == ':' || uneVille.charAt(i) == '"' || uneVille.charAt(i) == '*'
            || uneVille.charAt(i) == '?' || uneVille.charAt(i) == '/'
            || uneVille.charAt(i) == '\\') {
          res = CodeErreur.VILLE_NOM_INCORRECT;
        }

        i++;
      }
    } else {
      res = CodeErreur.VILLE_TAILLE_INCORRECTE;
    }

    if (res == null) {
      this.ville = uneVille;
    }

    return res;
  }

  /**
   * Retourne l'adresse mail du membre.
   * 
   * @return l'adresse mail du membre
   */
  public String getMail() {
    return mail;
  }

  /**
   * Change le mail du membre par unMail si celui-ci n'est pas null, sinon il est ajouté.
   *
   * @param unMail l'adresse mail du membre
   * @return null si la définition du mail est un succès, CodeErreur.MAIL_NULL le mail est null,
   *         CodeErreur.MAIL_VIDE si le mail est vide et CodeErreur.MAIL_INVALIDE si le mail ne
   *         correspond pas à une adresse mail
   */
  public CodeErreur setMail(String unMail) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et le mail n'est pas vide
    if (unMail == null) {
      res = CodeErreur.MAIL_NULL;
    } else if (unMail.length() == 0) {
      res = CodeErreur.MAIL_VIDE;
    } else {
      // Vérification de la validité de l'adresse mail
      Pattern pattern = Pattern.compile("^(.+)@(.+)$");
      Matcher matcher = pattern.matcher(unMail);

      if (!matcher.matches()) {
        res = CodeErreur.MAIL_INVALIDE;
      }
    }

    if (res == null) {
      this.mail = unMail;
    }

    return res;
  }

  /**
   * Retourne le mot du membre.
   * 
   * @return le mot de passe du membre
   */
  public String getMotDePasse() {
    return motDePasse;
  }

  /**
   * Change le motDePasse du membre par unMotDePasse si celui-ci n'est pas null, sinon il est
   * ajouté.
   *
   * @param unMotDePasse le mot de passe du membre
   * @return null si la définition du mot de passe est un succès, CodeErreur.MDP_NULL si le mot de
   *         passe est null, CodeErreur.MDP_VIDE si le mot de passe n'est composé d'aucun caractère,
   *         Code_Erreur.MDP_TAILLE_INCORRECTE si le mot de passe est trop court ou trop long
   */
  public CodeErreur setMotDePasse(String unMotDePasse) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et le mot de passe n'est pas vide
    if (unMotDePasse == null) {
      res = CodeErreur.MDP_NULL;
    } else if (unMotDePasse.length() == 0) {
      res = CodeErreur.MDP_VIDE;
    } else if (unMotDePasse.length() < TAILLE_MIN_MDP || unMotDePasse.length() > TAILLE_MAX_MDP) {
      res = CodeErreur.MDP_TAILLE_INCORRECTE;
    }

    if (res == null) {
      this.motDePasse = unMotDePasse;
    }

    return res;
  }
}
