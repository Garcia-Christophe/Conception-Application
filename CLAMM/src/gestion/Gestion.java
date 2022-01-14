package gestion;

import java.util.ArrayList;
import java.util.Date;
import gestionEvenements.Evenement;
import gestionEvenements.FabriqueEvenement;
import gestionEvenements.TypeEvenement;
import gestionMembres.FabriqueMembre;

public class Gestion {
  /**
   * Unique instance de la classe FabriqueMembre 
   */
  private FabriqueMembre fabriqueMembre;
  
  /**
   * Unique instance de la classe FabriqueEvenement (Singleton)
   */
  private FabriqueEvenement fabriqueEvenement;
  
  /**
   * Constructeur de l'interface Gestion
   */
  public Gestion() {
    this.fabriqueMembre = FabriqueMembre.getInstance();
    this.fabriqueEvenement = FabriqueEvenement.getInstance();
  }
  
  /**
  * @return la liste des �v�nements
  */
 public ArrayList<Evenement> getListeEvenements() {
   return fabriqueEvenement.getListeEvenements();
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
   return fabriqueEvenement.ajouterEvenement(unNom, unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType);
 }
 
 /**
  * Retourne l'�v�nement de la liste des �v�nements avec son identifiant.
  * 
  * @param unId Identifiant de l'�v�nement
  * @return l'object Evenement si pr�sent dans la liste, si non pr�sent dans la liste
  */
 public Evenement getEvenement(int unId) {
   return fabriqueEvenement.getEvenement(unId);
 }
 
 /**
  * Supprime �v�nement de la liste des �v�nements avec son identifiant.
  * 
  * @param unId Identifiant de l'�v�nement � supprimer
  * @return {@code 0} si la suppression est un succ�s, {@code -1} si l'�v�nement correspondant � l'identifiant n'est pas pr�sent dans la liste
  */
 public int supprimerEvenement(int unId) {
   return fabriqueEvenement.supprimerEvenement(unId);
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
   return fabriqueEvenement.modifierEvenement(unId, unNom, unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType);
 }
}
