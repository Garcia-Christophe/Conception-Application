package gestionMembres;

import java.util.ArrayList;
import gestionEvenements.FabriqueEvenement;

/**
 * La classe FabriqueMembre permet de centraliser la cr�ation des membres, et de rendre meur
 * instanciation plus flexible que l'utilisation de l'op�rateur d'instanciation {@code new}.
 * 
 * La classe est d�clar�e {@code final} car la construction d'objet est privatis�e, interdisant
 * ainsi l'h�ritage.
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
   * Cr�e l'instance du singleton que si elle est requise.
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
   * Constructeur de FabriqueMembre. Cr�e une liste vide de membres.
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
   * Ajoute un membre � la liste des membres.
   * 
   * @return {@code 1} si l'ajout est un succ�s, {@code 0} s'il a fallu faire des modifications pour
   *         que ce soit un succ�s, {@code -1} sinon
   */
  public int ajouterMembre() {
    return 0;
  }

  /**
   * Supprime un membre de la liste des membres.
   * 
   * @return {@code 1} si l'ajout est un succ�s, {@code 0} s'il a fallu faire des modifications pour
   *         que ce soit un succ�s, {@code -1} sinon
   */
  public int supprimerMembre() {
    return 0;
  }

  /**
   * Modifie les donn�es d'un membre de la liste des membres.
   * 
   * @return {@code 1} si l'ajout est un succ�s, {@code 0} s'il a fallu faire des modifications pour
   *         que ce soit un succ�s, {@code -1} sinon
   */
  public int modifierMembre() {
    return 0;
  }

  /**
   * Permet de retrouver un membre gr�ce � son pseudo.
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
