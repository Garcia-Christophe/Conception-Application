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
  private int nbMaxPersonnes;

  /**
   * Type de l'évènement
   */
  private TypeEvenement type;
  
  private final int tailleMaxNom = 50;
  
  private final int tailleMaxDescriptif = 500;
  
  private final int tailleMaxLieu = 100;

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
   * @param id tl'identifiant de l'évènement
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
   * @param nom le nom de l'évènement
   */
  public int setNom(String unNom) {
    int res=-1;
    if(unNom!=null) {
      if(unNom!="" && unNom.charAt(0)!=' ' && unNom.charAt(unNom.length()-1)!=' '&& unNom.length()<this.tailleMaxNom) {
        this.nom = unNom;
        res=0;
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
   * @param descriptif le descriptif de l'évènement
   */
  public int setDescriptif(String unDescriptif) {
    int res=-1;
    if(unDescriptif!=null) {
      if(unDescriptif!="" && unDescriptif.charAt(0)!=' ' && unDescriptif.charAt(unDescriptif.length()-1)!=' ' && unDescriptif.length()<this.tailleMaxDescriptif) {
        this.descriptif = unDescriptif;
        res=0;
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
   * @param image l'image de l'évènement
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
   * @param date la date de l'évènement
   */
  public int setDate(Date uneDate) {
    int res=-1;
    if(uneDate!=null) {
      if(uneDate.compareTo(new Date())>0) {
        this.date = uneDate;
        res=0;
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
   * @param lieu le lieu de l'évènement
   */
  public int setLieu(String unLieu) {
    int res=-1;
    if(unLieu!=null) {
      if(unLieu!="" && unLieu.charAt(0)!=' ' && unLieu.charAt(unLieu.length()-1)!=' ' && unLieu.length()<this.tailleMaxLieu) {
        this.lieu = unLieu;
        res=0;
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
   * @param nbMaxPersonnes le nombre maximum de personnes de l'évènement
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
   * @param type le type de l'évènement
   */
  public int setType(TypeEvenement unType) {
    int res=-1;
    if(unType!=null) {
      this.type = unType;
      res=0;
    }
    return res;
  }

}
