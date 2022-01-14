package gestionEvenements;

import java.util.Date;

/**
 * La classe Evenement réprésente un évènement qui est composé de son identifiant, son nom, son
 * descriptif , sa date, son lieu, son nombre maximum de personnes autorisées et son type. Il peut
 * également être composé d'une image facultative.
 * 
 * @author Alexia
 * @version 1.00
 * @see {@link FabriqueEvenement}
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
  public Evenement() {

  }

  /**
   * @return l'identifiant de l'évènement
   */
  public int getId() {
    return id;
  }

  /**
   * Change l'id d'un évènement par unId si celui-ci est supérieur à 0.
   * 
   * @param unId l'identifiant de l'évènement que l'ont veut lui attribuer
   * @return 0 si la modification de l'id est réussi, sinon -1
   */
  public int setId(int unId) {
    int res = -1;
    if (this.id > 0) {
      this.id = unId;
      res = 0;
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
   * @return 0 si la modification du nom est réussi, sinon -1
   */
  public int setNom(String unNom) {
    int res = -1;
    if (unNom != null) {
      if (unNom != "" && unNom.charAt(0) != ' ' && unNom.charAt(unNom.length() - 1) != ' '
          && unNom.length() < this.TAILLE_MAX_NOM) {
        this.nom = unNom;
        res = 0;
      }
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
   * @param unDescriptif le descriptif de l'évènement que l'ont veut lui attribuer
   * @return 0 si la modification du descriptif est réussi, sinon -1
   */
  public int setDescriptif(String unDescriptif) {
    int res = -1;
    if (unDescriptif != null) {
      if (unDescriptif != "" && unDescriptif.charAt(0) != ' '
          && unDescriptif.charAt(unDescriptif.length() - 1) != ' '
          && unDescriptif.length() < this.TAILLE_MAX_DESCRIPTIF) {
        this.descriptif = unDescriptif;
        res = 0;
      }
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
   * @param uneImage l'image de l'évènement que l'ont veut lui attribuer
   * @return 0 si la modification de l'image est réussi, sinon -1
   */
  public int setImage(String uneImage) {
    this.image = uneImage;
    return 0;
  }

  /**
   * @return la date de l'évènement
   */
  public Date getDate() {
    return date;
  }

  /**
   * Change la date d'un évènement par uneDate si celle-ci n'est pas null et n'est pas une date
   * antérieur à la date actuelle.
   * 
   * @param uneDate la date de l'évènement que l'ont veut lui attribuer
   * @return 0 si la modification de la date est réussi, sinon -1
   */
  public int setDate(Date uneDate) {
    int res = -1;
    if (uneDate != null) {
      if (uneDate.compareTo(new Date()) > 0) {
        this.date = uneDate;
        res = 0;
      }
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
   * @param unLieu le lieu de l'évenement que l'ont veut lui attribuer
   * @return 0 si la modification du lieu est réussi, sinon -1
   */
  public int setLieu(String unLieu) {
    int res = -1;
    if (unLieu != null) {
      if (unLieu != "" && unLieu.charAt(0) != ' ' && unLieu.charAt(unLieu.length() - 1) != ' '
          && unLieu.length() < this.TAILLE_MAX_LIEU) {
        this.lieu = unLieu;
        res = 0;
      }
    }

    return res;
  }

  /**
   * @return le nombre maximum de personnes de l'évènement
   */
  public int getNbMaxPersonnes() {
    return nbMaxPersonnes;
  }

  /**
   * Change le nbMaxPersonnes d'un évènement par unNbMaxPersonnes si celui-ci est supérieur à 1.
   * 
   * @param unNbMaxPersonnes le nombre maximum de personnes de l'évènement que l'ont veut lui
   *        attribuer
   * @return 0 si la modification du nbMaxPersonnes est réussi, sinon -1
   */
  public int setNbMaxPersonnes(int unNbMaxPersonnes) {
    int res = -1;
    if (this.nbMaxPersonnes > 1) {
      this.nbMaxPersonnes = unNbMaxPersonnes;
      res = 0;
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
   * @param unType le type de l'évènement que l'ont veut lui attribuer
   * @return 0 si la modification du type est réussi, sinon -1
   */
  public int setType(TypeEvenement unType) {
    int res = -1;
    if (unType != null) {
      this.type = unType;
      res = 0;
    }
    return res;
  }

}
