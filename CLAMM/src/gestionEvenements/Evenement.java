package gestionEvenements;

import java.util.Date;

public class Evenement {

  /**
   * Identifiant de l'�v�nement
   */
  private int id;

  /**
   * Nom de l'�v�nement
   */
  private String nom;

  /**
   * Descriptif de l'�v�nement
   */
  private String descriptif;

  /**
   * Lien de l'image de l'�v�nement
   */
  private String image;

  /**
   * Date de l'�v�nement
   */
  private Date date;

  /**
   * Lieu de l'�v�nement
   */
  private String lieu;

  /**
   * Nombre maximal de personnes pr�sentes � l'�v�nement
   */
  private int NbMaxPersonnes;

  /**
   * Type de l'�v�nement
   */
  private TypeEvenement type;

  /**
   * Constructeur de la classe Evenement.
   */
  public Evenement() {}

  /**
   * @return l'identifiant de l'�v�nement
   */
  public int getId() {
    return id;
  }

  /**
   * @param id tl'identifiant de l'�v�nement
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return le nom de l'�v�nement
   */
  public String getNom() {
    return nom;
  }

  /**
   * @param nom le nom de l'�v�nement
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * @return le descriptif de l'�v�nement
   */
  public String getDescriptif() {
    return descriptif;
  }

  /**
   * @param descriptif le descriptif de l'�v�nement
   */
  public void setDescriptif(String descriptif) {
    this.descriptif = descriptif;
  }

  /**
   * @return l'image de l'�v�nement
   */
  public String getImage() {
    return image;
  }

  /**
   * @param image l'image de l'�v�nement
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * @return la date de l'�v�nement
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date la date de l'�v�nement
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return le lieu de l'�v�nement
   */
  public String getLieu() {
    return lieu;
  }

  /**
   * @param lieu le lieu de l'�v�nement
   */
  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  /**
   * @return le nombre maximum de personnes de l'�v�nement
   */
  public int getNbMaxPersonnes() {
    return NbMaxPersonnes;
  }

  /**
   * @param nbMaxPersonnes le nombre maximum de personnes de l'�v�nement
   */
  public void setNbMaxPersonnes(int nbMaxPersonnes) {
    NbMaxPersonnes = nbMaxPersonnes;
  }

  /**
   * @return le type de l'�v�nement
   */
  public TypeEvenement getType() {
    return type;
  }

  /**
   * @param type le type de l'�v�nement
   */
  public void setType(TypeEvenement type) {
    this.type = type;
  }

}
