package gestion.participation;

import gestion.evenements.Evenement;
import gestion.membres.Membre;

/**
 * La classe Participation repr�sente un couple entre un �v�menment et un membre qui est inscrit �
 * cet �v�nement.
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
   * Membre qui est inscrit � this.evenement
   */
  private Membre membre;

  /**
   * Nombre de personnes vennant avec le membre inscrit � this.evenement
   */
  private int nbInscrit;

  /**
   * Information suppl�mentaire concernant la participation
   */
  private String information;


  
  
  public Participation(Evenement unEvenement, Membre unMembre, int unNbInscrit, String uneInformation) {
    this.evenement = unEvenement;
    this.membre = unMembre;
    this.nbInscrit = unNbInscrit;
    this.information = uneInformation;
  }

  /**
   * Retourne l'�v�nement de la participation.
   * 
   * @return l'�v�nement de la participation
   */
  public Evenement getEvenement() {
    return evenement;
  }

  /**
   * Change l'�v�nement d'une participation par unEvenement.
   * 
   * @param unEvenement l'�v�nement de la participation que l'on veut lui attribuer
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
   * Change l'information suppl�mentaire d'une participation par uneInformation.
   * 
   * @param uneInformation l'information suppl�mentaire de la participation que l'on veut lui attribuer
   */
  public void setInformation(String uneInformation) {
    this.information = uneInformation;
  }


}


