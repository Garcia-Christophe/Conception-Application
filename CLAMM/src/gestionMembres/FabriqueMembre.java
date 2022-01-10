package gestionMembres;

import java.util.ArrayList;

public class FabriqueMembre {

  private static FabriqueMembre fabriqueMembreInstance;

  private ArrayList<Membre> listeMembres;

  public static FabriqueMembre getInstance() {
    if (FabriqueMembre.fabriqueMembreInstance == null) {
      FabriqueMembre.fabriqueMembreInstance = new FabriqueMembre();
    }

    return FabriqueMembre.fabriqueMembreInstance;
  }

  /**
   * Constructeur de FabriqueMembre. Crée une liste vide de membres.
   */
  private FabriqueMembre() {
    this.setListeMembres(new ArrayList<>());
  }

  /**
   * @return la liste des membres
   */
  public ArrayList<Membre> getListeMembres() {
    return listeMembres;
  }

  /**
   * @param liste la liste des membres
   */
  public void setListeMembres(ArrayList<Membre> liste) {
    if (liste != null) {
      this.listeMembres = liste;
    }
  }

  public int ajouterMembre() {
    return 0;
  }

  public int supprimerMembre() {
    return 0;
  }

  public int modifierMembre() {
    return 0;
  }

  public Membre getMembre(String pseudo) {
    Membre membre = null;

    if (pseudo != null) {
      boolean membreTrouve = false;
      int i = 0;

      while (!membreTrouve && i < this.listeMembres.size()) {
        if (this.listeMembres.get(i).getPseudo().equals(pseudo)) {
          membre = this.listeMembres.get(i);
        }
      }
    }

    return membre;
  }

}
