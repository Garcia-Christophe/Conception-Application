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
   * @return l'object l'�v�nement si cr�ation r�ussie, null si erreur de cr�ation
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
    
    int res=-1;
    
    if(unEvenement!=null) {
      listeEvenements.add(unEvenement);
      
      nextId++; 
      
      res=0;
    }
    
    return res;
  }
  
  /**
   * Retourne l'�v�nement de la liste des �v�nements avec son identifiant.
   * 
   * @param unId Identifiant de l'�v�nement
   * @return l'object Evenement si pr�sent dans la liste, si non pr�sent dans la liste
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
   * Supprime �v�nement de la liste des �v�nements avec son identifiant.
   * 
   * @param unId Identifiant de l'�v�nement � supprimer
   * @return {@code 0} si la suppression est un succ�s, {@code -1} si l'�v�nement correspondant � l'identifiant n'est pas pr�sent dans la liste
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
   * Modifie un �v�nement de la liste des �v�nements.
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