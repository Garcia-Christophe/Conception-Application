package gestionEvenements;

import java.util.ArrayList;

public class FabriqueEvenement {

  private static FabriqueEvenement fabriqueEvenementInstance;

  private ArrayList<Evenement> listeEvenements;

  public static FabriqueEvenement getInstance() {
    if (FabriqueEvenement.fabriqueEvenementInstance == null) {
      FabriqueEvenement.fabriqueEvenementInstance = new FabriqueEvenement();
    }

    return FabriqueEvenement.fabriqueEvenementInstance;
  }

  private FabriqueEvenement() {
    this.setListeEvenements(new ArrayList<>());
  }

  /**
   * @return la liste des évènements
   */
  public ArrayList<Evenement> getListeEvenements() {
    return listeEvenements;
  }

  /**
   * @param liste La liste des évènements
   */
  public void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  }

}
