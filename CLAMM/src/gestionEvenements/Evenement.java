package gestionEvenements;

import java.util.Date;
import gestion.CodeErreur;

/**
 * La classe Evenement réprésente un évènement qui est composé de son identifiant, son nom, son
 * descriptif, sa date, son lieu, son nombre maximum de personnes autorisées et son type. Il peut
 * également être composé d'une image facultative.
 * 
 * @author Alexia
 * @version 1.00
 */
public class Evenement {

  /**
   * Identifiant de l'évènement
   */
  private int id;

  /**
   * Nom de l'évènement
   */
  private String nom;

  /**
   * Descriptif de l'évènement
   */
  private String descriptif;

  /**
   * Lien de l'image de l'évènement
   */
  private String image;

  /**
   * Date de l'évènement
   */
  private Date date;

  /**
   * Lieu de l'évènement
   */
  private String lieu;

  /**
   * Nombre maximal de personnes autorisées à l'évènement
   */
  private int nbMaxPersonnes;

  /**
   * Type de l'évènement
   */
  private TypeEvenement type;

  /**
   * Taille maximum du nom
   */
  private final int TAILLE_MAX_NOM = 50;

  /**
   * Taille maximum du descriptif
   */
  private final int TAILLE_MAX_DESCRIPTIF = 500;

  /**
   * Taille maximum du lieu
   */
  private final int TAILLE_MAX_LIEU = 100;

  /**
   * Constructeur de la classe Evenement.
   */
  public Evenement() {}

  /**
   * @return l'identifiant de l'évènement
   */
  public int getId() {
    return id;
  }

  /**
   * Change l'id d'un évènement par unId si celui-ci est supérieur ou égal à 0.
   * 
   * @param unId l'identifiant de l'évènement que l'on veut lui attribuer
   * @return null si la modification de l'id est réussie, sinon CodeErreur.ID_NEGATIF si unId est
   *         strictement inférieur à 0
   */
  public CodeErreur setId(int unId) {
    CodeErreur res = null;
    if (unId < 0) {
      res = CodeErreur.ID_NEGATIF;
    } else {
      this.id = unId;
    }

    return res;
  }

  /**
   * @return le nom de l'évènement
   */
  public String getNom() {
    return nom;
  }

  /**
   * Change le nom d'un évènement par unNom si celui-ci n'est pas null, ne commence pas et ne
   * termine pas par un espace, ne dépasse pas TAILLE_MAX_NOM.
   * 
   * @param unNom le nom de l'évènement que l'on veut lui attribuer
   * @return null si la modification du nom est réussie, sinon CodeErreur.NOM_NULL si unNom est
   *         null, CodeErreur.NOM_VIDE si la taille est égal à 0, CodeErreur.NOM_ESPACE_EN_TROP si
   *         unNom a un espace au début ou à la fin, CodeErreur.NOM_TROP_LONG si unNom dépasse la
   *         taille maximum
   */
  public CodeErreur setNom(String unNom) {
    CodeErreur res = null;

    if (unNom == null) {
      res = CodeErreur.NOM_NULL;
    } else if (unNom.length() == 0) {
      res = CodeErreur.NOM_VIDE;
    } else if (unNom.charAt(0) == ' ' || unNom.charAt(unNom.length() - 1) == ' ') {
      res = CodeErreur.NOM_ESPACE_EN_TROP;
    } else if (unNom.length() > this.TAILLE_MAX_NOM) {
      res = CodeErreur.NOM_TROP_LONG;
    } else {
      this.nom = unNom;
    }

    return res;
  }

  /**
   * @return le descriptif de l'évènement
   */
  public String getDescriptif() {
    return descriptif;
  }

  /**
   * Change le descriptif d'un évènement par unDescriptif si celui-ci n'est pas null, ne commence
   * pas et ne termine pas par un espace, ne dépasse pas TAILLE_MAX_DESCRIPTIF.
   * 
   * @param unDescriptif le descriptif de l'évènement que l'on veut lui attribuer
   * @return null si la modification du descriptif est réussie, sinon CodeErreur.DESCRIPTIF_NULL si
   *         unDescriptif est null, CodeErreur.DESCRIPTIF_VIDE si la taille est égale à 0,
   *         CodeErreur.DESCRIPTIF_ESPACE_EN_TROP si unDescriptif a un espace au début ou à la fin,
   *         CodeErreur.DESCRIPTIF_TROP_LONG si unDescriptif dépasse la taille maximum
   */
  public CodeErreur setDescriptif(String unDescriptif) {
    CodeErreur res = null;

    if (unDescriptif == null) {
      res = CodeErreur.DESCRIPTIF_NULL;
    } else if (unDescriptif.length() == 0) {
      res = CodeErreur.DESCRIPTIF_VIDE;
    } else if (unDescriptif.charAt(0) == ' '
        || unDescriptif.charAt(unDescriptif.length() - 1) == ' ') {
      res = CodeErreur.DESCRIPTIF_ESPACE_EN_TROP;
    } else if (unDescriptif.length() > this.TAILLE_MAX_DESCRIPTIF) {
      res = CodeErreur.DESCRIPTIF_TROP_LONG;
    } else {
      this.descriptif = unDescriptif;
    }

    return res;
  }

  /**
   * @return l'image de l'évènement
   */
  public String getImage() {
    return image;
  }

  /**
   * Change l'image d'un évènement par uneImage.
   * 
   * @param uneImage l'image de l'évènement que l'on veut lui attribuer
   * @return null si la modification de l'image est réussie
   */
  public CodeErreur setImage(String uneImage) {
    this.image = uneImage;
    return null;
  }

  /**
   * @return la date de l'évènement
   */
  public Date getDate() {
    return date;
  }

  /**
   * Change la date d'un évènement par uneDate si celle-ci n'est pas null et n'est pas une date
   * antérieure à la date actuelle.
   * 
   * @param uneDate la date de l'évènement que l'on veut lui attribuer
   * @return null si la modification de la date est réussie, sinon CodeErreur.DATE_NULL si uneDate
   *         est null ou CodeErreur.DATE_PASSEE si la date est passée
   */
  public CodeErreur setDate(Date uneDate) {
    CodeErreur res = null;
    if (uneDate != null) {
      if (uneDate.compareTo(new Date()) > 0) {
        this.date = uneDate;
      } else {
        res = CodeErreur.DATE_PASSEE;
      }
    } else {
      res = CodeErreur.DATE_NULL;
    }
    return res;
  }

  /**
   * @return le lieu de l'évènement
   */
  public String getLieu() {
    return lieu;
  }

  /**
   * Change le lieu d'un évènement par unLieu si celui-ci n'est pas null, ne commence pas ou ne
   * termine pas par un espace, ne dépasse pas TAILLE_MAX_LIEU.
   * 
   * @param unLieu le lieu de l'évenement que l'on veut lui attribuer
   * @return null si la modification du lieu est réussie, sinon CodeErreur.LIEU_NULL si unLieu est
   *         null, CodeErreur.LIEU_VIDE si la taille est égale à 0, CodeErreur.LIEU_ESPACE_EN_TROP
   *         si unLieu a un espace au début ou à la fin, CodeErreur.LIEU_TROP_LONG si unLieu dépasse
   *         la taille maximum
   * 
   */
  public CodeErreur setLieu(String unLieu) {
    CodeErreur res = null;

    if (unLieu == null) {
      res = CodeErreur.LIEU_NULL;
    } else if (unLieu.length() == 0) {
      res = CodeErreur.LIEU_VIDE;
    } else if (unLieu.charAt(0) == ' ' || unLieu.charAt(unLieu.length() - 1) == ' ') {
      res = CodeErreur.LIEU_ESPACE_EN_TROP;
    } else if (unLieu.length() > this.TAILLE_MAX_LIEU) {
      res = CodeErreur.LIEU_TROP_LONG;
    } else {
      this.lieu = unLieu;
    }

    return res;
  }

  /**
   * @return le nombre maximum de personnes autorisées de l'évènement
   */
  public int getNbMaxPersonnes() {
    return nbMaxPersonnes;
  }

  /**
   * Change le nombre maximum de personnes autorisées d'un évènement par unNbMaxPersonnes si
   * celui-ci est strictement supérieur à 1.
   * 
   * @param unNbMaxPersonnes le nombre maximum de personnes autorisées de l'évènement que l'on veut
   *        lui attribuer
   * @return null si la modification du nbMaxPersonnes est réussie, sinon
   *         CodeErreur.NB_MAX_PERSONNES_TROP_PETIT si unNbMaxPersonnes est inférieur ou égal à 1
   */
  public CodeErreur setNbMaxPersonnes(int unNbMaxPersonnes) {
    CodeErreur res = null;
    if (unNbMaxPersonnes > 1) {
      this.nbMaxPersonnes = unNbMaxPersonnes;
    } else {
      res = CodeErreur.NB_MAX_PERSONNES_TROP_PETIT;
    }

    return res;
  }

  /**
   * @return le type de l'évènement
   */
  public TypeEvenement getType() {
    return type;
  }

  /**
   * Change le type d'un évènement par unType si celui-ci n'est pas null.
   * 
   * @param unType le type de l'évènement que l'on veut lui attribuer
   * @return null si la modification du type est réussie, sinon CodeErreur.TYPE_NULL si unType est
   *         null
   * 
   */
  public CodeErreur setType(TypeEvenement unType) {
    CodeErreur res = null;
    if (unType != null) {
      this.type = unType;
    } else {
      res = CodeErreur.TYPE_NULL;
    }
    return res;
  }

}
