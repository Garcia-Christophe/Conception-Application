package gestion.evenements;

import java.util.Date;

/**
 * La classe Evenement réprésente un évènement qui est composé de son identifiant, son nom, son
 * descriptif, sa date, son lieu, son nombre maximum de personnes autorisées et son type. Il peut
 * également être composé d'une image facultative.
 * 
 * @author Alexia
 * @version 2.00
 */
public class Evenement {

  /**
   * Identifiant de l'évènement.
   */
  private int id;

  /**
   * Nom de l'évènement.
   */
  private String nom;

  /**
   * Descriptif de l'évènement.
   */
  private String descriptif;

  /**
   * Lien de l'image de l'évènement.
   */
  private String image;

  /**
   * Date de l'évènement.
   */
  private Date date;

  /**
   * Lieu de l'évènement.
   */
  private String lieu;

  /**
   * Nombre maximal de personnes autorisées à l'évènement.
   */
  private int nbMaxPersonnes;

  /**
   * Type de l'évènement.
   */
  private TypeEvenement type;

  /**
   * Constructeur de la classe Evenement.
   */
  public Evenement() {}

  /**
   * Retourne l'identifiant de l'évènement.
   * 
   * @return l'identifiant de l'évènement
   */
  public int getId() {
    return id;
  }

  /**
   * Change l'id d'un évènement.
   * 
   * @param unId l'identifiant de l'évènement que l'on veut lui attribuer
   */
  public void setId(int unId) {
    this.id = unId;
  }

  /**
   * Retourne le nom de l'événement.
   * 
   * @return le nom de l'évènement
   */
  public String getNom() {
    return nom;
  }

  /**
   * Change le nom d'un évènement.
   * 
   * @param unNom le nom de l'évènement que l'on veut lui attribuer
   */
  public void setNom(String unNom) {
    this.nom = unNom;
  }

  /**
   * Retourne le descriptif de l'évènement.
   * 
   * @return le descriptif de l'évènement
   */
  public String getDescriptif() {
    return descriptif;
  }

  /**
   * Change le descriptif d'un évènement.
   * 
   * @param unDescriptif le descriptif de l'évènement que l'on veut lui attribuer
   */
  public void setDescriptif(String unDescriptif) {
    this.descriptif = unDescriptif;
  }

  /**
   * Retourne l'image de l'évènement.
   * 
   * @return l'image de l'évènement
   */
  public String getImage() {
    return image;
  }

  /**
   * Change l'image d'un évènement.
   * 
   * @param uneImage l'image de l'évènement que l'on veut lui attribuer
   */
  public void setImage(String uneImage) {
    this.image = uneImage;
  }

  /**
   * Retourne la date de l'évènement.
   * 
   * @return la date de l'évènement
   */
  public Date getDate() {
    return date;
  }

  /**
   * Change la date d'un évènement.
   * 
   * @param uneDate la date de l'évènement que l'on veut lui attribuer
   */
  public void setDate(Date uneDate) {
    this.date = uneDate;
  }

  /**
   * Retourne le lieu de l'évènement.
   * 
   * @return le lieu de l'évènement
   */
  public String getLieu() {
    return lieu;
  }

  /**
   * Change le lieu d'un évènement.
   * 
   * @param unLieu le lieu de l'évenement que l'on veut lui attribuer
   */
  public void setLieu(String unLieu) {
    this.lieu = unLieu;
  }

  /**
   * Retourne le nombre maximum de personnes autorisées de l'évènement.
   * 
   * @return le nombre maximum de personnes autorisées de l'évènement
   */
  public int getNbMaxPersonnes() {
    return nbMaxPersonnes;
  }

  /**
   * Change le nombre maximum de personnes autorisées d'un évènement..
   * 
   * @param unNbMaxPersonnes le nombre maximum de personnes autorisées de l'évènement que l'on veut
   *        lui attribuer
   */
  public void setNbMaxPersonnes(int unNbMaxPersonnes) {
    this.nbMaxPersonnes = unNbMaxPersonnes;
  }

  /**
   * Retourne le type de l'évènement.
   * 
   * @return le type de l'évènement
   */
  public TypeEvenement getType() {
    return type;
  }

  /**
   * Change le type d'un évènement.
   * 
   * @param unType le type de l'évènement que l'on veut lui attribuer
   */
  public void setType(TypeEvenement unType) {
    this.type = unType;
  }

}
