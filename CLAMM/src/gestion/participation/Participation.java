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



}


