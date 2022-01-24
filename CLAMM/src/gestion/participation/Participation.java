package gestion.participation;

import gestion.evenements.Evenement;
import gestion.membres.Membre;

public class Participation {
  Evenement evenement;
  Membre membre;

  public Evenement getEvenement() {
    return evenement;
  }

  public void setEvenement(Evenement unEvenement) {
    this.evenement = unEvenement;
  }

  public Membre getMembre() {
    return membre;
  }

  public void setMembre(Membre unMembre) {
    this.membre = unMembre;
  }



}


