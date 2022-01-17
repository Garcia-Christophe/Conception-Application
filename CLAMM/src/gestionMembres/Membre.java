package gestionMembres;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import gestion.CodeErreur;

/**
 * La classe Membre repr�sente un membre d�association, caract�ris� par son nom, son pr�nom, un
 * mail, un pseudo, un mot de passe, sa ville de r�sidence, sa date et son lieu de naissance. Le
 * pseudo est unique.
 * 
 * @author Mathis
 * @version 1.00
 */
public class Membre {

  /**
   * Taille maximale d'une ville
   */
  private final int TAILLE_MAX_VILLE = 50;

  /**
   * Taille maximale d'un nom
   */
  private final int TAILLE_MAX_NOM = 30;

  /**
   * Taille maximale d'un pr�nom
   */
  private final int TAILLE_MAX_PRENOM = 30;

  /**
   * �ge maximum d'un �tre humain
   */
  private final int AGE_MAXIMUM = 130;

  /**
   * Pseudo du membre
   */
  private String pseudo;

  /**
   * Nom de famille du membre
   */
  private String nom;

  /**
   * Pr�nom du membre
   */
  private String prenom;

  /**
   * Lieu de naissance du membre
   */
  private String lieuNaissance;

  /**
   * Date de naissance du membre
   */
  private Date dateNaissance;

  /**
   * Lieu de r�sidence du membre
   */
  private String ville;

  /**
   * Adresse mail du membre
   */
  private String mail;

  /**
   * Mot de passe du membre
   */
  private String motDePasse;

  /**
   * Constructeur de Membre.
   */
  public Membre() {}

  /**
   * @return le pseudo du membre
   */
  public String getPseudo() {
    return pseudo;
  }

  /**
   * Change le pseudo du membre par unPseudo si celui-ci n'est pas null ou vide, et que le pseudo ne
   * contient pas: espace,<,>,|,:,",*,?,/,\
   *
   * @param pseudo le nouveau pseudo du membre
   * @return null en cas de succ�s, CodeErreur.PSEUDO_NULL si le pseudo est null, Code_Erreur ou
   *         vide, et -2 si le pseudo contient: espace,<,>,|,:,",*,?,/,\
   */
  public CodeErreur setPseudo(String unPseudo) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et le pseudo n'est pas vide
    if (unPseudo == null) {
      res = CodeErreur.PSEUDO_NULL;
    } else if (unPseudo.length() == 0) {
      res = CodeErreur.PSEUDO_VIDE;
    } else {
      int i = 0;

      // V�rifie tous les cas interdits
      while (i < unPseudo.length()) {
        if (Character.isDigit(unPseudo.charAt(i)) || unPseudo.charAt(i) == ' '
            || unPseudo.charAt(i) == '<' || unPseudo.charAt(i) == '>' || unPseudo.charAt(i) == '|'
            || unPseudo.charAt(i) == ':' || unPseudo.charAt(i) == '"' || unPseudo.charAt(i) == '*'
            || unPseudo.charAt(i) == '?' || unPseudo.charAt(i) == '/'
            || unPseudo.charAt(i) == '\\') {
          res = CodeErreur.PSEUDO_INVALIDE;
        }

        i++;
      }
    }

    return res;
  }

  /**
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
   * @return null en cas de succ�s, CodeErreur.NOM_TROP_GRAND si le nom est trop grand,
   *         CodeErreur.NOM_NULL si le nom est null, et CodeErreur.NOM_HORS_ALPHABET si le nom
   *         contient des caract�res autres que les lettres de l'alphabet
   */
  public CodeErreur setNom(String unNom) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et le nom n'est pas vide
    if (unNom == null) {
      res = CodeErreur.NOM_NULL;
    } else if (unNom.length() <= TAILLE_MAX_NOM) {
      int i = 0;

      // V�rifie tous les cas autoris�s
      while (i < unNom.length()) {
        if (unNom.charAt(i) != '-' && ((unNom.charAt(i) >= 'a' && unNom.charAt(i) <= 'z')
            || (unNom.charAt(i) >= 'A' && unNom.charAt(i) <= 'Z'))) {
          res = CodeErreur.NOM_HORS_ALPHABET;
        }

        i++;
      }
    } else {
      res = CodeErreur.NOM_TROP_GRAND;
    }

    return res;
  }

  /**
   * @return le pr�nom du membre
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * Change le pr�nom du membre par unPrenom si celui-ci n'est pas null ou vide, et que le pr�nom
   * contient uniquement les lettres de l'alphabet.
   *
   * @param unPrenom le prenom du membre
   * @return null en cas de succ�s, CodeErreur.PRENOM_TROP_GRAND si le prenom est trop grand,
   *         CodeErreur.PRENOM_NULL si le prenom est null, et CodeErreur.PRENOM_HORS_ALPHABET si le
   *         prenom contient des caract�res autres que les lettres de l'alphabet
   */
  public CodeErreur setPrenom(String unPrenom) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et le prenom n'est pas vide
    if (unPrenom == null) {
      res = CodeErreur.PRENOM_NULL;
    } else if (unPrenom.length() <= TAILLE_MAX_PRENOM) {
      int i = 0;

      // V�rifie tous les cas autoris�s
      while (i < unPrenom.length()) {
        if (unPrenom.charAt(i) != '-' && ((unPrenom.charAt(i) >= 'a' && unPrenom.charAt(i) <= 'z')
            || (unPrenom.charAt(i) >= 'A' && unPrenom.charAt(i) <= 'Z'))) {
          res = CodeErreur.PRENOM_HORS_ALPHABET;
        }

        i++;
      }
    } else {
      res = CodeErreur.PRENOM_TROP_GRAND;
    }

    return res;
  }

  /**
   * @return le lieu de naissance du membre
   */
  public String getLieuNaissance() {
    return lieuNaissance;
  }

  /**
   * Change le lieu de naissance d'un membre par unLieuNaissance si celle-ci n'est pas null, sinon
   * il est ajout�.
   * 
   * @param unLieuNaissance le lieu de naissance du membre
   * @return null si la d�finition du lieu de naissance est un succ�s, CodeErreur.LIEU_NULL si le
   *         lieu de naissance est null, CodeErreur.LIEU_NOM_INCORRECT si le nom du lieu de
   *         naissance comporte un caract�re sp�cial, CodeErreur.LIEU_TAILLE_INCORRECTE si le lieu
   *         de naissance ne comprend pas le nombre de caract�res requis
   */
  public CodeErreur setLieuNaissance(String unLieuNaissance) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et la ville n'est pas vide
    if (unLieuNaissance == null) {
      res = CodeErreur.LIEU_NULL;
    } else if (unLieuNaissance.length() <= TAILLE_MAX_VILLE && unLieuNaissance.length() > 0) {
      int i = 0;

      // V�rifie tous les cas interdits
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

    return res;
  }

  /**
   * @return la date de naissance du membre
   */
  public Date getDateNaissance() {
    return dateNaissance;
  }

  /**
   * Change la date de naissance du membre par uneDateNaissance si celle-ci n'est pas null, sinon
   * elle est ajout�e.
   *
   * @param uneDateNaissance la date de naissance du membre
   * @return null si la d�finition de la date de naissance est un succ�s, Code_Erreur.DATE_NULL si
   *         la date est null, CodeErreur.DATE_IMPOSSIBLE si la date de naissance est plus grande
   *         que la date courante, CodeErreur.DATE_AGE_IMPOSSIBLE si l'�ge du membre est sup�rieur �
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

    return res;
  }

  /**
   * @return la ville du membre
   */
  public String getVille() {
    return ville;
  }

  /**
   * Change la ville de r�sidence d'un membre par uneVille si celle-ci n'est pas null, sinon elle
   * est ajout�e.
   * 
   * @param uneVille la ville du membre
   * @return null si la d�finition de la ville est un succ�s, CodeErreur.VILLE_NOM_NULL si la ville
   *         est null, CodeErreur.VILLE_NOM_INCORRECT si le nom de la ville comporte un caract�re
   *         sp�cial, CodeErreur.VILLE_TAILLE_INCORRECTE si la ville ne comprend pas le nombre de
   *         caract�res requis
   */
  public CodeErreur setVille(String uneVille) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et la ville n'est pas vide
    if (uneVille == null) {
      res = CodeErreur.VILLE_NULL;
    } else if (uneVille.length() <= TAILLE_MAX_VILLE && uneVille.length() > 0) {
      int i = 0;

      // V�rifie tous les cas interdits
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
    return res;
  }

  /**
   * @return l'adresse mail du membre
   */
  public String getMail() {
    return mail;
  }

  /**
   * Change le mail du membre par unMail si celui-ci n'est pas null, sinon il est ajout�
   *
   * @param unMail l'adresse mail du membre
   * @return null si la d�finition du mail est un succ�s, CodeErreur.MAIL_NULL le mail est null,
   *         CodeErreur.MAIL_VIDE si le mail est vide et CodeErreur.MAIL_INVALIDE si le mail ne
   *         correspond pas � une adresse mail
   */
  public CodeErreur setMail(String unMail) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et le mail n'est pas vide
    if (unMail == null) {
      res = CodeErreur.MAIL_NULL;
    } else if (unMail.length() == 0) {
      res = CodeErreur.MAIL_VIDE;
    } else {
      // V�rification de la validit� de l'adresse mail
      Pattern pattern = Pattern.compile("^(.+)@(.+)$");
      Matcher matcher = pattern.matcher(unMail);

      if (!matcher.matches()) {
        res = CodeErreur.MAIL_INVALIDE;
      }
    }

    return res;
  }

  /**
   * @return le mot de passe du membre
   */
  public String getMotDePasse() {
    return motDePasse;
  }

  /**
   * Change le motDePasse du membre par unMotDePasse si celui-ci n'est pas null, sinon il est
   * ajout�.
   *
   * @param unMotDePasse le mot de passe du membre
   * @return null si la d�finition du mot de passe est un succ�s, CodeErreur.MDP_NULL si le mot de
   *         passe est null, CodeErreur.MDP_VIDE si le mot de passe n'est compos� d'aucun caract�re
   */
  public CodeErreur setMotDePasse(String unMotDePasse) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et le mot de passe n'est pas vide
    if (unMotDePasse == null) {
      res = CodeErreur.MDP_NULL;
    } else if (unMotDePasse.length() < 0) {
      res = CodeErreur.MDP_VIDE;
    }

    return res;
  }
}
