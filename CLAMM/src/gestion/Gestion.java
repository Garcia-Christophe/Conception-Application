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
  * @return la liste des évènements
  */
 public ArrayList<Evenement> getListeEvenements() {
   return fabriqueEvenement.getListeEvenements();
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
   return fabriqueEvenement.ajouterEvenement(unNom, unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType);
 }
 
 /**
  * Retourne l'événement de la liste des événements avec son identifiant.
  * 
  * @param unId Identifiant de l'événement
  * @return l'object Evenement si présent dans la liste, si non présent dans la liste
  */
 public Evenement getEvenement(int unId) {
   return fabriqueEvenement.getEvenement(unId);
 }
 
 /**
  * Supprime événement de la liste des événements avec son identifiant.
  * 
  * @param unId Identifiant de l'événement à supprimer
  * @return {@code 0} si la suppression est un succès, {@code -1} si l'événement correspondant à l'identifiant n'est pas présent dans la liste
  */
 public int supprimerEvenement(int unId) {
   return fabriqueEvenement.supprimerEvenement(unId);
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
   return fabriqueEvenement.modifierEvenement(unId, unNom, unDescriptif, uneImage, uneDate, unLieu, unNbMaxPersonnes, unType);
 }
}
