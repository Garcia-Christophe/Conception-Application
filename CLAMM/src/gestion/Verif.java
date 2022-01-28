package gestion;

import gestion.evenements.TypeEvenement;
import gestion.membres.Membre;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe Verif permet de vérifier la validité des éléments de {@link gestion.membres.Membre} et
 * de {@link getsion.evenements.Evenement}.
 * 
 * <p>
 * Chaque méthode permet de vérifier un ou plusieurs attributs, suivant les règles établies pour
 * chacun. Les méthodes retournent {@code null} si le paramètre correspond aux régles, une erreur de
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
   * Taille maximum du nom d'un événement.
   */
  private static final int TAILLE_MAX_NOM_EVENEMENT = 50;

  /**
   * Taille maximum du descriptif d'un événement.
   */
  private static final int TAILLE_MAX_DESCRIPTIF_EVENEMENT = 500;

  /**
   * Taille maximum du lieu d'un événement.
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
   * Taille maximale du prénom d'un membre.
   */
  private static final int TAILLE_MAX_PRENOM_MEMBRE = 30;

  /**
   * Âge maximum du membre.
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
   * Vérifier la validité de l'identifiant de l'événement.
   * 
   * <p>
   * L'identifiant doit être supérieur à 0.
   * 
   * @param unId l'identifiant de l'évènement que l'on veut vérifier
   * @return {@code null} si l'id est conforme, sinon CodeErreur.ID_NEGATIF si l'id est inférieur ou
   *         égal à 0
   */
  public CodeErreur verifIdEvenement(int unId) {
    CodeErreur res = null;

    if (unId <= 0) {
      res = CodeErreur.ID_NEGATIF;
    }

    return res;
  }

  /**
   * Vérifier la validité du nom de l'événement.
   * 
   * <p>
   * Le nom ne doit pas être {@code null}, être vide, commencer ou terminer par un espace, et
   * dépasser {@code TAILLE_MAX_NOM_EVENEMENT}.
   * 
   * @param unNom le nom de l'événement que l'on veut vérifier
   * @return {@code null} si le nom est conforme, sinon CodeErreur.NOM_NULL si le nom est
   *         {@code null}, CodeErreur.NOM_VIDE si la taille du nom est égale à 0,
   *         CodeErreur.NOM_ESPACE_EN_TROP si un espace est présent au début ou à la fin,
   *         CodeErreur.NOM_TROP_LONG si le nom dépasse la taille maximum
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
   * Vérifier la validité du descriptif de l'événement.
   * 
   * <p>
   * Le descriptif ne doit pas être {@code null}, commencer ou terminer par un espace, et dépasser
   * {@code TAILLE_MAX_DESCRIPTIF}.
   * 
   * @param unDescriptif le descriptif de l'événement que l'on veut vérifier
   * @return {@code null} si le descriptif est conforme, sinon CodeErreur.DESCRIPTIF_NULL si le
   *         descriptif est {@code null}, CodeErreur.DESCRIPTIF_ESPACE_EN_TROP si un espace est
   *         présent au début ou à la fin, CodeErreur.DESCRIPTIF_TROP_LONG si le descriptif dépasse
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
   * Vérifier la validité de la date de l'événement.
   * 
   * <p>
   * La date ne doit pas être {@code null} ou antérieure à la date actuelle.
   * 
   * @param uneDate la date de l'événement que l'on veut vérifier
   * @return {@code null} si la date est conforme, sinon CodeErreur.DATE_NULL si la date est
   *         {@code null}, CodeErreur.DATE_PASSEE si la date est passée
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
   * Vérifier la validité du lieu de l'événement.
   * 
   * <p>
   * Le lieu ne doit pas être {@code null}, être vide, commencer ou terminer par un espace, et
   * dépasser {@code TAILLE_MAX_LIEU_EVENEMENT}.
   * 
   * @param unLieu le lieu de l'événement que l'on veut vérifier
   * @return {@code null} si le lieu est conforme, sinon CodeErreur.LIEU_NULL si le lieu est
   *         {@code null}, CodeErreur.LIEU_VIDE si la taille est égale à 0,
   *         CodeErreur.LIEU_ESPACE_EN_TROP si un espace est présent au début ou à la fin,
   *         CodeErreur.LIEU_TROP_LONG si le lieu dépasse la taille maximum
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
   * Vérifier la validité du nombre maximum de personnes autorisées de l'événement.
   * 
   * <p>
   * Le nombre doit être strictement supérieur à 1.
   * 
   * @param unNbMaxPersonnes le nombre maximum de personnes autorisées de l'événement que l'on veut
   *        vérifier
   * @return {@code null} si le nombre est conforme, sinon CodeErreur.NB_MAX_PERSONNES_TROP_PETIT si
   *         le nombre est trop petit (inférieur ou égal à 1)
   */
  public CodeErreur verifNbMaxPersonnesEvenement(int unNbMaxPersonnes) {
    CodeErreur res = null;
    if (unNbMaxPersonnes <= 1) {
      res = CodeErreur.NB_MAX_PERSONNES_TROP_PETIT;
    }

    return res;
  }

  /**
   * Vérifier la validité du type de l'événement.
   * 
   * <p>
   * Le type ne doit pas être {@code null}.
   * 
   * @param unType le type de l'événement que l'on veut vérifier
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
   * Vérifier la validité du pseudo du membre.
   * 
   * <p>
   * Le pseudo ne doit pas être {@code null}, être vide, dépasser {@code TAILLE_MAX_PSEUDO_MEMBRE},
   * et contenir : espace<>|:/\"*?.
   * 
   * @param unPseudo le pseudo du membre que l'on veut vérifier
   * @return {@code null} si le pseudo est conforme, sinon CodeErreur.PSEUDO_NULL si le pseudo est
   *         {@code null}, CodeErreur.PSEUDO_VIDE si le pseudo est vide, CodeErreur.PSEUDO_TROP_LONG
   *         si le pseudo dépasse la taille maximum, CodeErreur.PSEUDO_INVALIDE si le pseudo
   *         contient : espace<>|:/\"*?
   */
  public CodeErreur verifPseudoMembre(String unPseudo) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et le pseudo n'est pas vide
    if (unPseudo == null) {
      res = CodeErreur.PSEUDO_NULL;
    } else if (unPseudo.length() == 0) {
      res = CodeErreur.PSEUDO_VIDE;
    } else if (unPseudo.length() > Verif.TAILLE_MAX_PSEUDO_MEMBRE) {
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

    return res;
  }

  /**
   * Vérifier la validité du nom et du prénom du membre.
   * 
   * <p>
   * Le nom et le prénom ne doivent pas être {@code null}, être vides, dépasser
   * {@code TAILLE_MAX_NOM_MEMBRE} en cas du nom et {@code TAILLE_MAX_PRENOM_MEMBRE} en cas du
   * prénom, et doivent contenir que des lettres de l'alphabet.
   * 
   * @param designation le nom ou le prénom du membre que l'on veut vérifier
   * @param numero 0 si la désignation correspond au nom, 1 si la désignation correspond au prénom
   * @return {@code null} si la désignation est conforme, sinon CodeErreur.NOM_NULL ou
   *         CodeErreur.PRENOM_NULL si la désignation est {@code null}, CodeErreur.NOM_VIDE ou
   *         CodeErreur.PRENOM_VIDE si la désignation est vide, CodeErreur.NOM_TROP_LONG ou
   *         CodeErreur.PRENOM_TROP_GRAND si la désignation dépasse la taille maximum,
   *         CodeErreur.NOM_HORS_ALPHABET ou CodeErruer.PRENOM_HORS_ALPHABET si la désignation
   *         contient un caractère hors alphabet.
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
   * Vérifier la validité du lieu de naissance et de la ville du membre.
   * 
   * <p>
   * Le lieu de naissance et la ville ne doivent pas être {@code null}, être vides, dépasser
   * {@code TAILLE_MAX_LIEU_MEMBRE}, et doivent contenir que des lettres de l'alphabet ou des
   * espaces.
   * 
   * @param lieu le lieu de naissance ou la ville du membre que l'on veut vérifier
   * @param numero 0 si le lieu correspond au lieu de naissance, 1 si le lieu correspond à la ville
   * @return {@code null} si le lieu est conforme, sinon CodeErreur.LIEU_NULL ou
   *         CodeErreur.VILLE_NULL si le lieu est {@code null}, CodeErreur.LIEU_TAILLE_INCORRECTE ou
   *         CodeErreur.VILLE_TAILLE_INCORRECTE si le lieu est vide ou dépasse la taille maximum,
   *         CodeErreur.LIEU_NOM_INCORRECT ou CodeErreur.VILLE_NOM_INCORRECT si le lieu contient un
   *         caractère hors alphabet et espaces.
   */
  public CodeErreur verifLieuxMembre(String lieu, int numero) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et la ville n'est pas vide
    if (lieu == null) {
      res = numero == 0 ? CodeErreur.LIEU_NULL : CodeErreur.VILLE_NULL;
    } else if (lieu.length() <= Verif.TAILLE_MAX_LIEU_MEMBRE && lieu.length() > 0) {
      int i = 0;

      // Vérifie tous les cas interdits
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
   * Vérifier la validité de la date de naissance du membre.
   * 
   * <p>
   * La date ne doit pas être {@code null}, antérieure à la date actuelle -
   * {@code AGE_MAXIMUM_MEMBRE}, et postérieure à la date actuelle.
   * 
   * @param uneDateNaissance la date du membre que l'on veut vérifier
   * @return {@code null} si la date est conforme, sinon CodeErreur.DATE_NULL si la date est
   *         {@code null}, CodeErreur.DATE_IMPOSSIBLE si la date est future,
   *         CodeErreur.DATE_AGE_IMPOSSIBLE si la date actuelle - la date de naissance est
   *         supérieure à {@code AGE_MAXIMUM_MEMBRE}.
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
   * Vérifier la validité de l'adresse mail du membre.
   * 
   * <p>
   * La mail ne doit pas être {@code null}, être vide, il doit être valide et de taille inférieure à
   * {@code TAILLE_MAX_MAIL_MEMBRE}.
   * 
   * @param unMail l'adresse mail du membre que l'on veut vérifier
   * @return {@code null} si le mail est conforme, sinon CodeErreur.MAIL_NULL si le mail est
   *         {@code null}, CodeErreur.MAIL_VIDE si le mail est vide, CodeErreur.MAIL_INVALIDE si le
   *         mail est une adresse invalide, CodeErreur.MAIL_TROP_LONG si le mail dépasse le maximum.
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
      // Vérification de la validité de l'adresse mail
      Pattern pattern = Pattern.compile("^(.+)@(.+)$");
      Matcher matcher = pattern.matcher(unMail);

      if (!matcher.matches()) {
        res = CodeErreur.MAIL_INVALIDE;
      }
    }

    return res;
  }

  /**
   * Vérifier la validité du mot de passe du membre.
   * 
   * <p>
   * Le mot de passe ne doit pas être {@code null}, doit être compris entre
   * {@code TAILLE_MIN_MDP_MEMBRE} et {@code TAILLE_MAX_MDP_MEMBRE}, doit contenir au moins une
   * majuscule, une minusule, un chiffre et un caractère spécial.
   * 
   * @param unMotDePasse le mot de passe du membre que l'on veut vérifier
   * @return {@code null} si le mot de passe est conforme, sinon CodeErreur.MDP_NULL si le mot de
   *         passe est {@code null}, CodeErreur.MDP_TAILLE_INCORRECTE si le mot de passe n'est pas
   *         compris entre {@code TAILLE_MIN_MDP_MEMBRE} et {@code TAILLE_MAX_MDP_MEMBRE},
   *         CodeErreur.MDP_INCORRECT s'il manque une majuscule, une minuscule, un chiffre ou un
   *         caractère spécial
   */
  public CodeErreur verifMdpMembre(String unMotDePasse) {
    CodeErreur res = null;

    // Si le paramètre n'est pas null et le mot de passe n'est pas vide
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

      // Vérifie la contenance des caractères obligatoires
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
