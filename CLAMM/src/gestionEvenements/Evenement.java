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
  public Evenement() {

  }

  /**
   * @return l'identifiant de l'�v�nement
   */
  public int getId() {
    return id;
  }

  /**
   * @param id tl'identifiant de l'�v�nement
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
   * @return le nom de l'�v�nement
   */
  public String getNom() {
    return nom;
  }

  /**
   * @param nom le nom de l'�v�nement
   */
  public int setNom(String unNom) {
    int res=-1;
    if(unNom==null || unNom=="") {
      this.nom = unNom;
      res=0;
    }
    
    return res;
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
  public int setDescriptif(String unDescriptif) {
    this.descriptif = unDescriptif;
    return 0;
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
  public int setImage(String uneImage) {
    this.image = uneImage;
    return 0;
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
  public void setDate(Date uneDate) {
    this.date = uneDate;
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
  public int setLieu(String unLieu) {
    this.lieu = unLieu;
    return 0;
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
  public int setNbMaxPersonnes(int unNbMaxPersonnes) {
    NbMaxPersonnes = unNbMaxPersonnes;
    return 0;
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
  public int setType(TypeEvenement unType) {
    this.type = unType;
    return 0;
  }

}
