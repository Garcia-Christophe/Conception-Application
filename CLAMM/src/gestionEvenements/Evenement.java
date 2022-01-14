package gestionEvenements;

import java.util.Date;

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
   * Nombre maximal de personnes présentes à l'évènement
   */
  private int NbMaxPersonnes;

  /**
   * Type de l'évènement
   */
  private TypeEvenement type;

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
   * @param id tl'identifiant de l'évènement
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return le nom de l'évènement
   */
  public String getNom() {
    return nom;
  }

  /**
   * @param nom le nom de l'évènement
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * @return le descriptif de l'évènement
   */
  public String getDescriptif() {
    return descriptif;
  }

  /**
   * @param descriptif le descriptif de l'évènement
   */
  public void setDescriptif(String descriptif) {
    this.descriptif = descriptif;
  }

  /**
   * @return l'image de l'évènement
   */
  public String getImage() {
    return image;
  }

  /**
   * @param image l'image de l'évènement
   */
  public void setImage(String image) {
    this.image = image;
  }

  /**
   * @return la date de l'évènement
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date la date de l'évènement
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return le lieu de l'évènement
   */
  public String getLieu() {
    return lieu;
  }

  /**
   * @param lieu le lieu de l'évènement
   */
  public void setLieu(String lieu) {
    this.lieu = lieu;
  }

  /**
   * @return le nombre maximum de personnes de l'évènement
   */
  public int getNbMaxPersonnes() {
    return NbMaxPersonnes;
  }

  /**
   * @param nbMaxPersonnes le nombre maximum de personnes de l'évènement
   */
  public void setNbMaxPersonnes(int nbMaxPersonnes) {
    NbMaxPersonnes = nbMaxPersonnes;
  }

  /**
   * @return le type de l'évènement
   */
  public TypeEvenement getType() {
    return type;
  }

  /**
   * @param type le type de l'évènement
   */
  public void setType(TypeEvenement type) {
    this.type = type;
  }

}
