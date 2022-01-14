package gestionMembres;

import java.util.Date;

public class Membre implements Comparable {

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
  public int setPseudo(String pseudo) {
    this.pseudo = pseudo;
    return 1;
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
  public int setNom(String nom) {
    this.nom = nom;
    return 1;
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
  public int setPrenom(String prenom) {
    this.prenom = prenom;
    return 1;
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
  public int setLieuNaissance(String lieuNaissance) {
    this.lieuNaissance = lieuNaissance;
    return 1;
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
  public int setDateNaissance(Date dateNaissance) {
    this.dateNaissance = dateNaissance;
    return 1;
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
  public int setVille(String ville) {
    this.ville = ville;
    return 1;
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
  public int setMail(String mail) {
    this.mail = mail;
    return 1;
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
  public int setMotDePasse(String motDePasse) {
    this.motDePasse = motDePasse;
    return 1;
  }

  @Override
  public int compareTo(Object o) {
    int res = -1;

    // Si le paramètre n'est pas null
    if (o != null) {
      // Si l'objet est une instance de Membre
      if (o instanceof Membre) {
        // Si le membre n'est pas le même que ce membre-ci
        if (o != this) {
          // Compare le pseudo des 2 membres
          res = this.pseudo.compareTo(((Membre) o).getPseudo());
        } else {
          res = 0;
        }
      }
    }

    return res;
  }

}
