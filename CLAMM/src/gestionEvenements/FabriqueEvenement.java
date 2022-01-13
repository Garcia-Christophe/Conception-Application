package gestionEvenements;

import java.util.ArrayList;
import java.util.Date;

public class FabriqueEvenement {

  /**
   * Instance de la fabrique
   */
  private static FabriqueEvenement fabriqueEvenementInstance;

  /**
   * Liste des événements
   */
  private ArrayList<Evenement> listeEvenements;

  /**
   * Prochain identifiant
   */
  private int nextId;
  
  
  /**
   * Créer l'instance de la fabrique si elle n'existe pas et la retourne
   * @return l'instance de la fabrique
   */
  public static FabriqueEvenement getInstance() {
    if (FabriqueEvenement.fabriqueEvenementInstance == null) {
      FabriqueEvenement.fabriqueEvenementInstance = new FabriqueEvenement();
    }

    return FabriqueEvenement.fabriqueEvenementInstance;
  }

  /**
   * Constructeur de FabriqueEvenement. Crée une liste vide d'événements.
   */
  private FabriqueEvenement() {
    this.setListeEvenements(new ArrayList<>());
    this.nextId=1;
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
  private void setListeEvenements(ArrayList<Evenement> liste) {
    if (liste != null) {
      this.listeEvenements = liste;
    }
  } 
  
  /**
   * Créer un événement.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement 
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return l'object Evenement si création réussie, null si erreur de création
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
   * Ajoute un événement à la liste des événements.
   * 
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement 
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code 0} si l'ajout est un succès, {@code -1} si erreur de création de l'événement
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
   * Retourne l'événement de la liste des événements avec son identifiant.
   * 
   * @param unId Identifiant de l'événement
   * @return l'object Evenement si présent dans la liste, si non présent dans la liste
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
   * Supprime événement de la liste des événements avec son identifiant.
   * 
   * @param unId Identifiant de l'événement à supprimer
   * @return {@code 0} si la suppression est un succès, {@code -1} si l'événement correspondant à l'identifiant n'est pas présent dans la liste
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
   * Modifie un événement à la liste des événements.
   * 
   * @param unId Identifiant de l'événement
   * @param unNom Nom de l'événement
   * @param unDescriptif Description de l'événement
   * @param uneImage URL de l'image
   * @param uneDate Date de l'élévement 
   * @param unlieu Lieu de l'événement
   * @param unNbMaxPersonnes Nombre maximum de personnes autorisées à l'événement
   * @param unType Type de l'évenement
   * @return {@code 0} si la modification est un succès, {@code -1} si l'événement correspondant à l'identifiant n'est pas présent dans la liste, {@code 1} si erreur de modification de l'événement
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