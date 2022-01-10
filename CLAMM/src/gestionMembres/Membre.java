package gestionMembres;

import java.util.Date;

public class Membre {

  /**
   * Pseudo du membre
   */
  private String pseudo;

  /**
   * Nom de famille du membre
   */
  private String nom;

  /**
   * Prénom du membre
   */
  private String prenom;

  /**
   * Lieu de naissance du membre
   */
  private String lieuNaissance;

  /**
   * Date de naissance du membre
   */
  private Date dateNaissance;

  /**
   * Lieu de résidence du membre
   */
  private String ville;

  /**
   * Adresse mail du membre
   */
  private String mail;

  /**
   * Mot de passe du membre
   */
  private String motDePasse;

  /**
   * Constructeur de Membre.
   */
  public Membre() {}

  /**
   * @return le pseudo du membre
   */
  public String getPseudo() {
    return pseudo;
  }

  /**
   * @param pseudo le pseudo du membre
   */
  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  /**
   * @return le nom du membre
   */
  public String getNom() {
    return nom;
  }

  /**
   * @param nom le nom du membre
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * @return le prénom du membre
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * @param prenom le prénom du membre
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * @return le lieu de naissance du membre
   */
  public String getLieuNaissance() {
    return lieuNaissance;
  }

  /**
   * @param lieuNaissance le lieu de naissance du membre
   */
  public void setLieuNaissance(String lieuNaissance) {
    this.lieuNaissance = lieuNaissance;
  }

  /**
   * @return la date de naissance du membre
   */
  public Date getDateNaissance() {
    return dateNaissance;
  }

  /**
   * @param dateNaissance la date de naissance du membre
   */
  public void setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
  }

  /**
   * @return la ville du membre
   */
  public String getVille() {
    return ville;
  }

  /**
   * @param ville la ville du membre
   */
  public void setVille(String ville) {
    this.ville = ville;
  }

  /**
   * @return l'adresse mail du membre
   */
  public String getMail() {
    return mail;
  }

  /**
   * @param mail l'adresse mail du membre
   */
  public void setMail(String mail) {
    this.mail = mail;
  }

  /**
   * @return le mot de passe du membre
   */
  public String getMotDePasse() {
    return motDePasse;
  }

  /**
   * @param motDePasse le mot de passe du membre
   */
  public void setMotDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
  }

}
