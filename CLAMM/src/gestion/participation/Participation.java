package gestion.participation;

import gestion.evenements.Evenement;
import gestion.membres.Membre;

/**
 * La classe Participation représente un couple entre un évémenment et un membre qui est inscrit à
 * cet événement.
 * 
 * @author Alexia
 * @version 2.00
 */
public class Participation {

  /**
   * Evenement auquel est inscrit this.membre
   */
  private Evenement evenement;

  /**
   * Membre qui est inscrit à this.evenement
   */
  private Membre membre;

  /**
   * Nombre de personnes vennant avec le membre inscrit à this.evenement
   */
  private int nbInscrit;

  /**
   * Information supplémentaire concernant la participation
   */
  private String information;


  
  
  public Participation(Evenement unEvenement, Membre unMembre, int unNbInscrit, String uneInformation) {
    this.evenement = unEvenement;
    this.membre = unMembre;
    this.nbInscrit = unNbInscrit;
    this.information = uneInformation;
  }

  /**
   * Retourne l'événement de la participation.
   * 
   * @return l'événement de la participation
   */
  public Evenement getEvenement() {
    return evenement;
  }

  /**
   * Change l'événement d'une participation par unEvenement.
   * 
   * @param unEvenement l'événement de la participation que l'on veut lui attribuer
   */
  public void setEvenement(Evenement unEvenement) {
    this.evenement = unEvenement;
  }

  /**
   * Retourne le membre de la participation.
   * 
   * @return le membre de la participation
   */
  public Membre getMembre() {
    return membre;
  }

  /**
   * Change le membre d'une participation par unMembre.
   * 
   * @param unMembre le membre de la participation que l'on veut lui attribuer
   */
  public void setMembre(Membre unMembre) {
    this.membre = unMembre;
  }

  public int getNbInscrit() {
    return nbInscrit;
  }

  /**
   * Change le nombre de participant d'une participation par unNbInscrit.
   * 
   * @param unNbInscrit le nombre de participant de la participation que l'on veut lui attribuer
   */
  public void setNbInscrit(int unNbInscrit) {
    this.nbInscrit = unNbInscrit;
  }

  public String getInformation() {
    return information;
  }

  /**
   * Change l'information supplémentaire d'une participation par uneInformation.
   * 
   * @param uneInformation l'information supplémentaire de la participation que l'on veut lui attribuer
   */
  public void setInformation(String uneInformation) {
    this.information = uneInformation;
  }


}


