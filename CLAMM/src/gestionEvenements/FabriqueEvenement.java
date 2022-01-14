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
   * @return l'object l'événement si création réussie, null si erreur de création
   */
  private Evenement creerEvenement(int unId, String unNom, String unDescriptif, String uneImage, Date uneDate, String unLieu, int unNbMaxPersonnes, TypeEvenement unType) {
    Evenement unEvenement=new Evenement();
    
    int resultats_set=0;
    
    resultats_set+=unEvenement.setId(unId);
    
    resultats_set+=unEvenement.setNom(unNom);

    resultats_set+=unEvenement.setDescriptif(unDescriptif);
    
    resultats_set+=unEvenement.setImage(uneImage);
    
    resultats_set+=unEvenement.setDate(uneDate);
    
    resultats_set+=unEvenement.setLieu(unLieu);
    
    resultats_set+=unEvenement.setNbMaxPersonnes(unNbMaxPersonnes);
    
    resultats_set+=unEvenement.setType(unType);
    
    if(resultats_set<0) {
      unEvenement=null;
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
    
    int res=-1;
    
    if(unEvenement!=null) {
      listeEvenements.add(unEvenement);
      
      nextId++; 
      
      res=0;
    }
    
    return res;
  }
  
  /**
   * Retourne l'événement de la liste des événements avec son identifiant.
   * 
   * @param unId Identifiant de l'événement
   * @return l'object Evenement si présent dans la liste, si non présent dans la liste
   */
  public Evenement getEvenement(int unId) {
    Evenement unEvenement = null;
    
    if(unId>0 || unId<nextId) {
      for(Evenement e : listeEvenements) {
        if(e.getId()==unId) {
          unEvenement=e;
        }
      }
    }
    
    return unEvenement;
  }
  
  /**
   * Supprime événement de la liste des événements avec son identifiant.
   * 
   * @param unId Identifiant de l'événement à supprimer
   * @return {@code 0} si la suppression est un succès, {@code -1} si l'événement correspondant à l'identifiant n'est pas présent dans la liste
   */
  public int supprimerEvenement(int unId) {
    Evenement unEvenement=getEvenement(unId);
    
    int res=-1;
    
    if(unEvenement!=null) {
      listeEvenements.remove(unEvenement);
      res=0;
    }
    
    return res;
  }
  
  /**
   * Modifie un événement de la liste des événements.
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
    int res=-1;
    
    if(unId>0 || unId<nextId) {
      
      for(int i=0; i<listeEvenements.size();i++) {
        if(listeEvenements.get(i).getId()==unId) {
          Evenement unEvenement=creerEvenement(unId, unNom, unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType);
        
          if(unEvenement==null) {
            res=1;
          }else {
            listeEvenements.set(i, unEvenement);
        
            res=0;
          }
        }
      }
    }
    
    return res;
  }
}