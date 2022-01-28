package gestion.membres;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import gestion.CodeErreur;

/**
 * La classe Membre repr�sente un membre d�association, caract�ris� par son nom, son pr�nom, un
 * mail, un pseudo, un mot de passe, sa ville de r�sidence, sa date et son lieu de naissance. Le
 * pseudo est unique.
 * 
 * @author Mathis
 * @version 2.00
 */
public class Membre {

  /**
   * Pseudo du membre.
   */
  private String pseudo;

  /**
   * Nom de famille du membre.
   */
  private String nom;

  /**
   * Pr�nom du membre.
   */
  private String prenom;

  /**
   * Lieu de naissance du membre.
   */
  private String lieuNaissance;

  /**
   * Date de naissance du membre.
   */
  private Date dateNaissance;

  /**
   * Lieu de r�sidence du membre.
   */
  private String ville;

  /**
   * Adresse mail du membre.
   */
  private String mail;

  /**
   * Mot de passe du membre.
   */
  private String motDePasse;

  /**
   * Constructeur de Membre.
   */
  public Membre() {}

  /**
   * Retourne le pseudo du membre.
   * 
   * @return le pseudo du membre
   */
  public String getPseudo() {
    return pseudo;
  }

  /**
   * Change le pseudo du membre.
   *
   * @param unPseudo le pseudo du membre
   */
  public void setPseudo(String unPseudo) {
    this.pseudo = unPseudo;
  }

  /**
   * Retourne le nom du membre.
   * 
   * @return le nom du membre
   */
  public String getNom() {
    return nom;
  }

  /**
   * Change le nom du membre.
   *
   * @param unNom le nom du membre
   */
  public void setNom(String unNom) {
    this.nom = unNom;
  }

  /**
   * Retourne le pr�nom du membre.
   * 
   * @return le pr�nom du membre
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * Change le pr�nom du membre.
   *
   * @param unPrenom le prenom du membre
   */
  public void setPrenom(String unPrenom) {
    this.prenom = unPrenom;
  }

  /**
   * Retourne le lieu de naissance du membre.
   * 
   * @return le lieu de naissance du membre
   */
  public String getLieuNaissance() {
    return lieuNaissance;
  }

  /**
   * Change le lieu de naissance d'un membre.
   * 
   * @param unLieuNaissance le lieu de naissance du membre
   */
  public void setLieuNaissance(String unLieuNaissance) {
    this.lieuNaissance = unLieuNaissance;
  }

  /**
   * Retourne la date de naissance du membre.
   * 
   * @return la date de naissance du membre
   */
  public Date getDateNaissance() {
    return dateNaissance;
  }

  /**
   * Change la date de naissance du membre.
   *
   * @param uneDateNaissance la date de naissance du membre
   */
  public void setDateNaissance(Date uneDateNaissance) {
    this.dateNaissance = uneDateNaissance;
  }

  /**
   * Retourne la ville du membre.
   * 
   * @return la ville du membre
   */
  public String getVille() {
    return ville;
  }

  /**
   * Change la ville de r�sidence d'un membre.
   * 
   * @param uneVille la ville du membre
   */
  public void setVille(String uneVille) {
    this.ville = uneVille;
  }

  /**
   * Retourne l'adresse mail du membre.
   * 
   * @return l'adresse mail du membre
   */
  public String getMail() {
    return mail;
  }

  /**
   * Change le mail du membre.
   *
   * @param unMail l'adresse mail du membre
   */
  public void setMail(String unMail) {
    this.mail = unMail;
  }

  /**
   * Retourne le mot du membre.
   * 
   * @return le mot de passe du membre
   */
  public String getMotDePasse() {
    return motDePasse;
  }

  /**
   * Change le motDePasse du membre.
   *
   * @param unMotDePasse le mot de passe du membre
   */
  public void setMotDePasse(String unMotDePasse) {
    this.motDePasse = unMotDePasse;
  }
}
