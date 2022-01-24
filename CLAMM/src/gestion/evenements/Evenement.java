package gestion.evenements;

import java.util.Date;

/**
 * La classe Evenement r�pr�sente un �v�nement qui est compos� de son identifiant, son nom, son
 * descriptif, sa date, son lieu, son nombre maximum de personnes autoris�es et son type. Il peut
 * �galement �tre compos� d'une image facultative.
 * 
 * @author Alexia
 * @version 2.00
 */
public class Evenement {

  /**
   * Identifiant de l'�v�nement.
   */
  private int id;

  /**
   * Nom de l'�v�nement.
   */
  private String nom;

  /**
   * Descriptif de l'�v�nement.
   */
  private String descriptif;

  /**
   * Lien de l'image de l'�v�nement.
   */
  private String image;

  /**
   * Date de l'�v�nement.
   */
  private Date date;

  /**
   * Lieu de l'�v�nement.
   */
  private String lieu;

  /**
   * Nombre maximal de personnes autoris�es � l'�v�nement.
   */
  private int nbMaxPersonnes;

  /**
   * Type de l'�v�nement.
   */
  private TypeEvenement type;

  /**
   * Constructeur de la classe Evenement.
   */
  public Evenement() {}

  /**
   * Retourne l'identifiant de l'�v�nement.
   * 
   * @return l'identifiant de l'�v�nement
   */
  public int getId() {
    return id;
  }

  /**
   * Change l'id d'un �v�nement.
   * 
   * @param unId l'identifiant de l'�v�nement que l'on veut lui attribuer
   */
  public void setId(int unId) {
    this.id = unId;
  }

  /**
   * Retourne le nom de l'�v�nement.
   * 
   * @return le nom de l'�v�nement
   */
  public String getNom() {
    return nom;
  }

  /**
   * Change le nom d'un �v�nement.
   * 
   * @param unNom le nom de l'�v�nement que l'on veut lui attribuer
   */
  public void setNom(String unNom) {
    this.nom = unNom;
  }

  /**
   * Retourne le descriptif de l'�v�nement.
   * 
   * @return le descriptif de l'�v�nement
   */
  public String getDescriptif() {
    return descriptif;
  }

  /**
   * Change le descriptif d'un �v�nement.
   * 
   * @param unDescriptif le descriptif de l'�v�nement que l'on veut lui attribuer
   */
  public void setDescriptif(String unDescriptif) {
    this.descriptif = unDescriptif;
  }

  /**
   * Retourne l'image de l'�v�nement.
   * 
   * @return l'image de l'�v�nement
   */
  public String getImage() {
    return image;
  }

  /**
   * Change l'image d'un �v�nement.
   * 
   * @param uneImage l'image de l'�v�nement que l'on veut lui attribuer
   */
  public void setImage(String uneImage) {
    this.image = uneImage;
  }

  /**
   * Retourne la date de l'�v�nement.
   * 
   * @return la date de l'�v�nement
   */
  public Date getDate() {
    return date;
  }

  /**
   * Change la date d'un �v�nement.
   * 
   * @param uneDate la date de l'�v�nement que l'on veut lui attribuer
   */
  public void setDate(Date uneDate) {
    this.date = uneDate;
  }

  /**
   * Retourne le lieu de l'�v�nement.
   * 
   * @return le lieu de l'�v�nement
   */
  public String getLieu() {
    return lieu;
  }

  /**
   * Change le lieu d'un �v�nement.
   * 
   * @param unLieu le lieu de l'�venement que l'on veut lui attribuer
   */
  public void setLieu(String unLieu) {
    this.lieu = unLieu;
  }

  /**
   * Retourne le nombre maximum de personnes autoris�es de l'�v�nement.
   * 
   * @return le nombre maximum de personnes autoris�es de l'�v�nement
   */
  public int getNbMaxPersonnes() {
    return nbMaxPersonnes;
  }

  /**
   * Change le nombre maximum de personnes autoris�es d'un �v�nement..
   * 
   * @param unNbMaxPersonnes le nombre maximum de personnes autoris�es de l'�v�nement que l'on veut
   *        lui attribuer
   */
  public void setNbMaxPersonnes(int unNbMaxPersonnes) {
    this.nbMaxPersonnes = unNbMaxPersonnes;
  }

  /**
   * Retourne le type de l'�v�nement.
   * 
   * @return le type de l'�v�nement
   */
  public TypeEvenement getType() {
    return type;
  }

  /**
   * Change le type d'un �v�nement.
   * 
   * @param unType le type de l'�v�nement que l'on veut lui attribuer
   */
  public void setType(TypeEvenement unType) {
    this.type = unType;
  }

}
