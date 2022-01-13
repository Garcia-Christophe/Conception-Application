package gestionMembres;

import java.util.ArrayList;
import gestionEvenements.FabriqueEvenement;

/**
 * La classe FabriqueMembre permet de centraliser la création des membres, et de rendre meur
 * instanciation plus flexible que l'utilisation de l'opérateur d'instanciation {@code new}.
 * 
 * La classe est déclarée {@code final} car la construction d'objet est privatisée, interdisant
 * ainsi l'héritage.
 * 
 * @author Christophe
 * @version 1.00
 * @see {@link Membre}, {@link FabriqueEvenement}
 */
public final class FabriqueMembre {

  /**
   * Unique instance de la classe FabriqueMembre (Singleton)
   */
  private static FabriqueMembre fabriqueMembreInstance;

  /**
   * Liste des membres
   */
  private ArrayList<Membre> listeMembres;

  /**
   * Crée l'instance du singleton que si elle est requise.
   * 
   * @return l'unique instance de FabriqueMembre
   */
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
    this.setListeMembres(new ArrayList<Membre>());
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

  /**
   * Ajoute un membre à la liste des membres.
   * 
   * @return {@code 1} si l'ajout est un succès, {@code 0} s'il a fallu faire des modifications pour
   *         que ce soit un succès, {@code -1} sinon
   */
  public int ajouterMembre() {
    return 0;
  }

  /**
   * Supprime un membre de la liste des membres.
   * 
   * @return {@code 1} si l'ajout est un succès, {@code 0} s'il a fallu faire des modifications pour
   *         que ce soit un succès, {@code -1} sinon
   */
  public int supprimerMembre() {
    return 0;
  }

  /**
   * Modifie les données d'un membre de la liste des membres.
   * 
   * @return {@code 1} si l'ajout est un succès, {@code 0} s'il a fallu faire des modifications pour
   *         que ce soit un succès, {@code -1} sinon
   */
  public int modifierMembre() {
    return 0;
  }

  /**
   * Permet de retrouver un membre grâce à son pseudo.
   * 
   * @return le membre correspondant au pseudo, {@code null} sinon
   */
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
