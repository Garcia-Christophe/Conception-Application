package gestionEvenements;

import java.util.ArrayList;
import java.util.Date;

public class FabriqueEvenement {

  /**
   * Instance de la fabrique
   */
  private static FabriqueEvenement fabriqueEvenementInstance;

  /**
   * Liste des �v�nements
   */
  private ArrayList<Evenement> listeEvenements;

  /**
   * Prochain identifiant
   */
  private int nextId;
  
  
  /**
   * Cr�er l'instance de la fabrique si elle n'existe pas et la retourne
   * @return l'instance de la fabrique
   */
  public static FabriqueEvenement getInstance() {
    if (FabriqueEvenement.fabriqueEvenementInstance == null) {
      FabriqueEvenement.fabriqueEvenementInstance = new FabriqueEvenement();
    }

    return FabriqueEvenement.fabriqueEvenementInstance;
  }

  /**
   * Constructeur de FabriqueEvenement. Cr�e une liste vide d'�v�nements.
   */
  private FabriqueEvenement() {
    this.setListeEvenements(new ArrayList<>());
    this.nextId=1;
  }

  /**
   * @return la liste des �v�nements
   */
  public ArrayList<Evenement> getListeEvenements() {
    return listeEvenements;
  }

  /**
   * @param liste La liste des �v�nements
   */
  private void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  } 
  
  /**
   * Cr�er un �v�nement.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement 
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return l'object Evenement si cr�ation r�ussie, null si erreur de cr�ation
   */
  private Evenement creerEvenement(int unId, String unNom, String unDescriptif, String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    Evenement unEvenement=new Evenement();
    
    if(unEvenement.setId(unId) == -1) {
      return null;
    }
    
    if(unEvenement.setNom(unNom) == -1) {
      return null;
    }
    
    if(unEvenement.setDescriptif(unDescriptif) == -1) {
      return null;
    }
    
    if(unEvenement.setImage(uneImage) == -1) {
      return null;
    }
    
    if(unEvenement.setDate(uneDate) == -1) {
      return null;
    }
    
    if(unEvenement.setLieu(unLieu); == -1) {
      return null;
    }
    
    if(unEvenement.setNbMaxPersonnes(unNbMaxPersonnes) == -1) {
      return null;
    }
    
    if(unEvenement.setType(unType) == -1) {
      return null;
    }
    
    return unEvenement;
  }

  /**
   * Ajoute un �v�nement � la liste des �v�nements.
   * 
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement 
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code 0} si l'ajout est un succ�s, {@code -1} si erreur de cr�ation de l'�v�nement
   */
  public int ajouterEvenement(String unNom, String unDescriptif, String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    
    Evenement unEvenement=creerEvenement(nextId, unNom, unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType);
    
    if(unEvenement==null) {
      return -1;
    }
    
    listeEvenements.add(unEvenement);
    
    nextId++; 
    
    return 0;
  }
  
  /**
   * Retourne l'�v�nement de la liste des �v�nements avec son identifiant.
   * 
   * @param unId Identifiant de l'�v�nement
   * @return l'object Evenement si pr�sent dans la liste, si non pr�sent dans la liste
   */
  public Evenement getEvenement(int unId) {
    if(unId<0 || unId>=nextId) {
      return null;
    }
    
    for(Evenement e : listeEvenements) {
      if(e.getId()==unId) {
        return e;
      }
    }
    
    return null;
  }
  
  /**
   * Supprime �v�nement de la liste des �v�nements avec son identifiant.
   * 
   * @param unId Identifiant de l'�v�nement � supprimer
   * @return {@code 0} si la suppression est un succ�s, {@code -1} si l'�v�nement correspondant � l'identifiant n'est pas pr�sent dans la liste
   */
  public int supprimerEvenement(int unId) {
    Evenement unEvenement=getEvenement(unId);
    
    if(unEvenement==null) {
      return -1;
    }
    
    listeEvenements.remove(unEvenement);
    return 0;
  }
  
  /**
   * Modifie un �v�nement � la liste des �v�nements.
   * 
   * @param unId Identifiant de l'�v�nement
   * @param unNom Nom de l'�v�nement
   * @param unDescriptif Description de l'�v�nement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'�l�vement 
   * @param unlieu Lieu de l'�v�nement
   * @param unNbMaxPersonnes Nombre maximum de personnes autoris�es � l'�v�nement
   * @param unType Type de l'�venement
   * @return {@code 0} si la modification est un succ�s, {@code -1} si l'�v�nement correspondant � l'identifiant n'est pas pr�sent dans la liste, {@code 1} si erreur de modification de l'�v�nement
   */
  public int ajouterEvenement(int unId, String unNom, String unDescriptif, String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    if(unId<0 || unId>=nextId) {
      return -1;
    }
    
    for(int i=0; i<listeEvenements.size();i++) {
      if(listeEvenements.get(i).getId()==unId) {
        Evenement unEvenement=creerEvenement(unId, unNom, unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType);
        
        if(unEvenement==null) {
          return 1;
        }
        
        listeEvenements.set(i, unEvenement);
        
        return 0;
      }
    }
    
    return -1;
  }
}