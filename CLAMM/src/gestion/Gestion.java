package gestion;

import gestionEvenements.FabriqueEvenement;
import gestionMembres.FabriqueMembre;
import gestionMembres.Membre;

public class Gestion {
  private FabriqueMembre fabriqueMembre;
  private FabriqueEvenement fabriqueEvenement;
  
  public Gestion() {
    this.fabriqueMembre = FabriqueMembre.getInstance();
    this.fabriqueEvenement = FabriqueEvenement.getInstance();
  }
  
  
}
