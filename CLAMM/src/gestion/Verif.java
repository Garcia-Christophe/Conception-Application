package gestion;

import gestion.evenements.TypeEvenement;
import gestion.membres.Membre;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe Verif permet de v�rifier la validit� des �l�ments de {@link gestion.membres.Membre} et
 * de {@link getsion.evenements.Evenement}.
 * 
 * <p>
 * Chaque m�thode permet de v�rifier un ou plusieurs attributs, suivant les r�gles �tablies pour
 * chacun. Les m�thodes retournent {@code null} si le param�tre correspond aux r�gles, une erreur de
 * {@link CodeErreur} sinon.
 * 
 * @author Christophe
 * @version 2.00
 * @see gestion.membres.Membre
 * @see gestion.evenements.Evenement
 * @see CodeErreur
 */
public class Verif {

  /**
   * Taille maximum du nom d'un �v�nement.
   */
  private static final int TAILLE_MAX_NOM_EVENEMENT = 50;

  /**
   * Taille maximum du descriptif d'un �v�nement.
   */
  private static final int TAILLE_MAX_DESCRIPTIF_EVENEMENT = 500;

  /**
   * Taille maximum du lieu d'un �v�nement.
   */
  private static final int TAILLE_MAX_LIEU_EVENEMENT = 100;

  /**
   * Taille maximale du pseudo du membre.
   */
  private static final int TAILLE_MAX_PSEUDO_MEMBRE = 30;

  /**
   * Taille maximale du lieu de naissance et de la ville du membre.
   */
  private static final int TAILLE_MAX_LIEU_MEMBRE = 50;

  /**
   * Taille maximale du nom d'un membre.
   */
  private static final int TAILLE_MAX_NOM_MEMBRE = 30;

  /**
   * Taille maximale du pr�nom d'un membre.
   */
  private static final int TAILLE_MAX_PRENOM_MEMBRE = 30;

  /**
   * �ge maximum du membre.
   */
  private static final int AGE_MAXIMUM_MEMBRE = 130;

  /**
   * Taille maximale du mail d'un membre.
   */
  private static final int TAILLE_MAX_MAIL_MEMBRE = 320;

  /**
   * Taille maximale du mot de passe d'un membre.
   */
  private static final int TAILLE_MAX_MDP_MEMBRE = 30;

  /**
   * Taille minimale du mot de passe d'un membre.
   */
  private static final int TAILLE_MIN_MDP_MEMBRE = 6;

  /**
   * Constructeur de la classe {@code Verif}.
   */
  public Verif() {}

  /**
   * V�rifier la validit� de l'identifiant de l'�v�nement.
   * 
   * <p>
   * L'identifiant doit �tre sup�rieur � 0.
   * 
   * @param unId l'identifiant de l'�v�nement que l'on veut v�rifier
   * @return {@code null} si l'id est conforme, sinon CodeErreur.ID_NEGATIF si l'id est inf�rieur ou
   *         �gal � 0
   */
  public CodeErreur verifIdEvenement(int unId) {
    CodeErreur res = null;

    if (unId <= 0) {
      res = CodeErreur.ID_NEGATIF;
    }

    return res;
  }

  /**
   * V�rifier la validit� du nom de l'�v�nement.
   * 
   * <p>
   * Le nom ne doit pas �tre {@code null}, �tre vide, commencer ou terminer par un espace, et
   * d�passer {@code TAILLE_MAX_NOM_EVENEMENT}.
   * 
   * @param unNom le nom de l'�v�nement que l'on veut v�rifier
   * @return {@code null} si le nom est conforme, sinon CodeErreur.NOM_NULL si le nom est
   *         {@code null}, CodeErreur.NOM_VIDE si la taille du nom est �gale � 0,
   *         CodeErreur.NOM_ESPACE_EN_TROP si un espace est pr�sent au d�but ou � la fin,
   *         CodeErreur.NOM_TROP_LONG si le nom d�passe la taille maximum
   */
  public CodeErreur verifNomEvenement(String unNom) {
    CodeErreur res = null;

    if (unNom == null) {
      res = CodeErreur.NOM_NULL;
    } else if (unNom.length() == 0) {
      res = CodeErreur.NOM_VIDE;
    } else if (unNom.charAt(0) == ' ' || unNom.charAt(unNom.length() - 1) == ' ') {
      res = CodeErreur.NOM_ESPACE_EN_TROP;
    } else if (unNom.length() > Verif.TAILLE_MAX_NOM_EVENEMENT) {
      res = CodeErreur.NOM_TROP_LONG;
    }

    return res;
  }

  /**
   * V�rifier la validit� du descriptif de l'�v�nement.
   * 
   * <p>
   * Le descriptif ne doit pas �tre {@code null}, commencer ou terminer par un espace, et d�passer
   * {@code TAILLE_MAX_DESCRIPTIF}.
   * 
   * @param unDescriptif le descriptif de l'�v�nement que l'on veut v�rifier
   * @return {@code null} si le descriptif est conforme, sinon CodeErreur.DESCRIPTIF_NULL si le
   *         descriptif est {@code null}, CodeErreur.DESCRIPTIF_ESPACE_EN_TROP si un espace est
   *         pr�sent au d�but ou � la fin, CodeErreur.DESCRIPTIF_TROP_LONG si le descriptif d�passe
   *         la taille maximum
   */
  public CodeErreur verifDescriptifEvenement(String unDescriptif) {
    CodeErreur res = null;

    if (unDescriptif == null) {
      res = CodeErreur.DESCRIPTIF_NULL;
    } else if (unDescriptif != "" && (unDescriptif.charAt(0) == ' '
        || unDescriptif.charAt(unDescriptif.length() - 1) == ' ')) {
      res = CodeErreur.DESCRIPTIF_ESPACE_EN_TROP;
    } else if (unDescriptif.length() > Verif.TAILLE_MAX_DESCRIPTIF_EVENEMENT) {
      res = CodeErreur.DESCRIPTIF_TROP_LONG;
    }

    return res;
  }

  /**
   * V�rifier la validit� de la date de l'�v�nement.
   * 
   * <p>
   * La date ne doit pas �tre {@code null} ou ant�rieure � la date actuelle.
   * 
   * @param uneDate la date de l'�v�nement que l'on veut v�rifier
   * @return {@code null} si la date est conforme, sinon CodeErreur.DATE_NULL si la date est
   *         {@code null}, CodeErreur.DATE_PASSEE si la date est pass�e
   */
  public CodeErreur verifDateEvenement(Date uneDate) {
    Date dateCourante = new Date();
    CodeErreur res = null;
    if (uneDate != null) {
      if (uneDate.before(dateCourante)) {
        res = CodeErreur.DATE_PASSEE;
      }
    } else {
      res = CodeErreur.DATE_NULL;
    }
    return res;
  }

  /**
   * V�rifier la validit� du lieu de l'�v�nement.
   * 
   * <p>
   * Le lieu ne doit pas �tre {@code null}, �tre vide, commencer ou terminer par un espace, et
   * d�passer {@code TAILLE_MAX_LIEU_EVENEMENT}.
   * 
   * @param unLieu le lieu de l'�v�nement que l'on veut v�rifier
   * @return {@code null} si le lieu est conforme, sinon CodeErreur.LIEU_NULL si le lieu est
   *         {@code null}, CodeErreur.LIEU_VIDE si la taille est �gale � 0,
   *         CodeErreur.LIEU_ESPACE_EN_TROP si un espace est pr�sent au d�but ou � la fin,
   *         CodeErreur.LIEU_TROP_LONG si le lieu d�passe la taille maximum
   */
  public CodeErreur verifLieuEvenement(String unLieu) {
    CodeErreur res = null;

    if (unLieu == null) {
      res = CodeErreur.LIEU_NULL;
    } else if (unLieu.length() == 0) {
      res = CodeErreur.LIEU_VIDE;
    } else if (unLieu.charAt(0) == ' ' || unLieu.charAt(unLieu.length() - 1) == ' ') {
      res = CodeErreur.LIEU_ESPACE_EN_TROP;
    } else if (unLieu.length() > Verif.TAILLE_MAX_LIEU_EVENEMENT) {
      res = CodeErreur.LIEU_TROP_LONG;
    }

    return res;
  }

  /**
   * V�rifier la validit� du nombre maximum de personnes autoris�es de l'�v�nement.
   * 
   * <p>
   * Le nombre doit �tre strictement sup�rieur � 1.
   * 
   * @param unNbMaxPersonnes le nombre maximum de personnes autoris�es de l'�v�nement que l'on veut
   *        v�rifier
   * @return {@code null} si le nombre est conforme, sinon CodeErreur.NB_MAX_PERSONNES_TROP_PETIT si
   *         le nombre est trop petit (inf�rieur ou �gal � 1)
   */
  public CodeErreur verifNbMaxPersonnesEvenement(int unNbMaxPersonnes) {
    CodeErreur res = null;
    if (unNbMaxPersonnes <= 1) {
      res = CodeErreur.NB_MAX_PERSONNES_TROP_PETIT;
    }

    return res;
  }

  /**
   * V�rifier la validit� du type de l'�v�nement.
   * 
   * <p>
   * Le type ne doit pas �tre {@code null}.
   * 
   * @param unType le type de l'�v�nement que l'on veut v�rifier
   * @return {@code null} si le type est conforme, sinon CodeErreur.TYPE_NULL si le type est
   *         {@code null}
   */
  public CodeErreur verifTypeEvenement(TypeEvenement unType) {
    CodeErreur res = null;
    if (unType == null) {
      res = CodeErreur.TYPE_NULL;
    }
    return res;
  }

  /**
   * V�rifier la validit� du pseudo du membre.
   * 
   * <p>
   * Le pseudo ne doit pas �tre {@code null}, �tre vide, d�passer {@code TAILLE_MAX_PSEUDO_MEMBRE},
   * et contenir : espace<>|:/\"*?.
   * 
   * @param unPseudo le pseudo du membre que l'on veut v�rifier
   * @return {@code null} si le pseudo est conforme, sinon CodeErreur.PSEUDO_NULL si le pseudo est
   *         {@code null}, CodeErreur.PSEUDO_VIDE si le pseudo est vide, CodeErreur.PSEUDO_TROP_LONG
   *         si le pseudo d�passe la taille maximum, CodeErreur.PSEUDO_INVALIDE si le pseudo
   *         contient : espace<>|:/\"*?
   */
  public CodeErreur verifPseudoMembre(String unPseudo) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et le pseudo n'est pas vide
    if (unPseudo == null) {
      res = CodeErreur.PSEUDO_NULL;
    } else if (unPseudo.length() == 0) {
      res = CodeErreur.PSEUDO_VIDE;
    } else if (unPseudo.length() > Verif.TAILLE_MAX_PSEUDO_MEMBRE) {
      res = CodeErreur.PSEUDO_TROP_LONG;
    } else {
      int i = 0;

      // V�rifie tous les cas interdits
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

    return res;
  }

  /**
   * V�rifier la validit� du nom et du pr�nom du membre.
   * 
   * <p>
   * Le nom et le pr�nom ne doivent pas �tre {@code null}, �tre vides, d�passer
   * {@code TAILLE_MAX_NOM_MEMBRE} en cas du nom et {@code TAILLE_MAX_PRENOM_MEMBRE} en cas du
   * pr�nom, et doivent contenir que des lettres de l'alphabet.
   * 
   * @param designation le nom ou le pr�nom du membre que l'on veut v�rifier
   * @param numero 0 si la d�signation correspond au nom, 1 si la d�signation correspond au pr�nom
   * @return {@code null} si la d�signation est conforme, sinon CodeErreur.NOM_NULL ou
   *         CodeErreur.PRENOM_NULL si la d�signation est {@code null}, CodeErreur.NOM_VIDE ou
   *         CodeErreur.PRENOM_VIDE si la d�signation est vide, CodeErreur.NOM_TROP_LONG ou
   *         CodeErreur.PRENOM_TROP_GRAND si la d�signation d�passe la taille maximum,
   *         CodeErreur.NOM_HORS_ALPHABET ou CodeErruer.PRENOM_HORS_ALPHABET si la d�signation
   *         contient un caract�re hors alphabet.
   */
  public CodeErreur verifIdentiteMembre(String designation, int numero) {
    CodeErreur res = null;

    if (designation == null) {
      res = numero == 0 ? CodeErreur.NOM_NULL : CodeErreur.PRENOM_NULL;
    } else if (designation.length() == 0) {
      res = numero == 0 ? CodeErreur.NOM_VIDE : CodeErreur.PRENOM_VIDE;
    } else if (designation
        .length() > (numero == 0 ? Verif.TAILLE_MAX_NOM_MEMBRE : Verif.TAILLE_MAX_PRENOM_MEMBRE)) {
      res = numero == 0 ? CodeErreur.NOM_TROP_GRAND : CodeErreur.PRENOM_TROP_GRAND;
    } else if (!designation.matches("[a-zA-Z]+")) {
      res = numero == 0 ? CodeErreur.NOM_HORS_ALPHABET : CodeErreur.PRENOM_HORS_ALPHABET;
    }

    return res;
  }

  /**
   * V�rifier la validit� du lieu de naissance et de la ville du membre.
   * 
   * <p>
   * Le lieu de naissance et la ville ne doivent pas �tre {@code null}, �tre vides, d�passer
   * {@code TAILLE_MAX_LIEU_MEMBRE}, et doivent contenir que des lettres de l'alphabet ou des
   * espaces.
   * 
   * @param lieu le lieu de naissance ou la ville du membre que l'on veut v�rifier
   * @param numero 0 si le lieu correspond au lieu de naissance, 1 si le lieu correspond � la ville
   * @return {@code null} si le lieu est conforme, sinon CodeErreur.LIEU_NULL ou
   *         CodeErreur.VILLE_NULL si le lieu est {@code null}, CodeErreur.LIEU_TAILLE_INCORRECTE ou
   *         CodeErreur.VILLE_TAILLE_INCORRECTE si le lieu est vide ou d�passe la taille maximum,
   *         CodeErreur.LIEU_NOM_INCORRECT ou CodeErreur.VILLE_NOM_INCORRECT si le lieu contient un
   *         caract�re hors alphabet et espaces.
   */
  public CodeErreur verifLieuxMembre(String lieu, int numero) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et la ville n'est pas vide
    if (lieu == null) {
      res = numero == 0 ? CodeErreur.LIEU_NULL : CodeErreur.VILLE_NULL;
    } else if (lieu.length() <= Verif.TAILLE_MAX_LIEU_MEMBRE && lieu.length() > 0) {
      int i = 0;

      // V�rifie tous les cas interdits
      while (i < lieu.length()) {
        if (lieu.charAt(i) != ' ' && (lieu.charAt(i) < 'a' && lieu.charAt(i) > 'z')
            && (lieu.charAt(i) < 'A' && lieu.charAt(i) > 'Z')) {
          res = numero == 0 ? CodeErreur.LIEU_NOM_INCORRECT : CodeErreur.VILLE_NOM_INCORRECT;
        }

        i++;
      }
    } else {
      res = numero == 0 ? CodeErreur.LIEU_TAILLE_INCORRECTE : CodeErreur.VILLE_TAILLE_INCORRECTE;
    }

    return res;
  }

  /**
   * V�rifier la validit� de la date de naissance du membre.
   * 
   * <p>
   * La date ne doit pas �tre {@code null}, ant�rieure � la date actuelle -
   * {@code AGE_MAXIMUM_MEMBRE}, et post�rieure � la date actuelle.
   * 
   * @param uneDateNaissance la date du membre que l'on veut v�rifier
   * @return {@code null} si la date est conforme, sinon CodeErreur.DATE_NULL si la date est
   *         {@code null}, CodeErreur.DATE_IMPOSSIBLE si la date est future,
   *         CodeErreur.DATE_AGE_IMPOSSIBLE si la date actuelle - la date de naissance est
   *         sup�rieure � {@code AGE_MAXIMUM_MEMBRE}.
   */
  public CodeErreur verifDateMembre(Date uneDateNaissance) {
    CodeErreur res = null;
    Date dateCourante = new Date();

    if (uneDateNaissance == null) {
      res = CodeErreur.DATE_NULL;
    } else if (uneDateNaissance.after(dateCourante)) {
      res = CodeErreur.DATE_IMPOSSIBLE;
    } else if (dateCourante.getYear() - uneDateNaissance.getYear() > Verif.AGE_MAXIMUM_MEMBRE) {
      res = CodeErreur.DATE_AGE_IMPOSSIBLE;
    }

    return res;
  }

  /**
   * V�rifier la validit� de l'adresse mail du membre.
   * 
   * <p>
   * La mail ne doit pas �tre {@code null}, �tre vide, il doit �tre valide et de taille inf�rieure �
   * {@code TAILLE_MAX_MAIL_MEMBRE}.
   * 
   * @param unMail l'adresse mail du membre que l'on veut v�rifier
   * @return {@code null} si le mail est conforme, sinon CodeErreur.MAIL_NULL si le mail est
   *         {@code null}, CodeErreur.MAIL_VIDE si le mail est vide, CodeErreur.MAIL_INVALIDE si le
   *         mail est une adresse invalide, CodeErreur.MAIL_TROP_LONG si le mail d�passe le maximum.
   */
  public CodeErreur verifMailMembre(String unMail) {
    CodeErreur res = null;

    if (unMail == null) {
      res = CodeErreur.MAIL_NULL;
    } else if (unMail.length() == 0) {
      res = CodeErreur.MAIL_VIDE;
    } else if (unMail.length() > Verif.TAILLE_MAX_MAIL_MEMBRE) {
      res = CodeErreur.MAIL_TROP_LONG;
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
   * V�rifier la validit� du mot de passe du membre.
   * 
   * <p>
   * Le mot de passe ne doit pas �tre {@code null}, doit �tre compris entre
   * {@code TAILLE_MIN_MDP_MEMBRE} et {@code TAILLE_MAX_MDP_MEMBRE}, doit contenir au moins une
   * majuscule, une minusule, un chiffre et un caract�re sp�cial.
   * 
   * @param unMotDePasse le mot de passe du membre que l'on veut v�rifier
   * @return {@code null} si le mot de passe est conforme, sinon CodeErreur.MDP_NULL si le mot de
   *         passe est {@code null}, CodeErreur.MDP_TAILLE_INCORRECTE si le mot de passe n'est pas
   *         compris entre {@code TAILLE_MIN_MDP_MEMBRE} et {@code TAILLE_MAX_MDP_MEMBRE},
   *         CodeErreur.MDP_INCORRECT s'il manque une majuscule, une minuscule, un chiffre ou un
   *         caract�re sp�cial
   */
  public CodeErreur verifMdpMembre(String unMotDePasse) {
    CodeErreur res = null;

    // Si le param�tre n'est pas null et le mot de passe n'est pas vide
    if (unMotDePasse == null) {
      res = CodeErreur.MDP_NULL;
    } else if (unMotDePasse.length() < Verif.TAILLE_MIN_MDP_MEMBRE
        || unMotDePasse.length() > Verif.TAILLE_MAX_MDP_MEMBRE) {
      res = CodeErreur.MDP_TAILLE_INCORRECTE;
    } else {
      int i = 0;
      boolean minuscule = false;
      boolean majuscule = false;
      boolean chiffre = false;
      boolean caractereSpecial = false;

      // V�rifie la contenance des caract�res obligatoires
      while (i < unMotDePasse.length()) {
        if (unMotDePasse.charAt(i) >= 'a' && unMotDePasse.charAt(i) <= 'z') {
          minuscule = true;
        } else if (unMotDePasse.charAt(i) >= 'A' && unMotDePasse.charAt(i) <= 'Z') {
          majuscule = true;
        } else if (Character.isDigit(unMotDePasse.charAt(i))) {
          chiffre = true;
        } else {
          caractereSpecial = true;
        }

        i++;
      }

      if (!minuscule || !majuscule || !chiffre || !caractereSpecial) {
        res = CodeErreur.MDP_INCORRECT;
      }
    }

    return res;
  }

}
