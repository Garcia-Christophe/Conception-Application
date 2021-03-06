package gestion;

import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;
import gestion.membres.Membre;
import gestion.participations.Participation;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * La classe base de donn?es permet de faire la liaison entre l'application Java et la base de
 * donn?es externe.
 * 
 * <p>La classe peut cr?er, ajouter, modifier et supprimer des membres, des ?v?nements et des 
 * participations au niveau de la base de donn?es.
 * 
 * @author Alexia, Christophe
 * @version 2.00
 */
public class BaseDeDonnees {

  /**
   * Connexion ? la base de donn?es.
   */
  Connection connection;

  /**
   * Permet d'ex?cuter les requ?tes SQL sur la base de donn?es connect?e.
   */
  Statement sqlStatement;

  /**
   * Nombre d'ann?es ? ajouter pour une date.
   */
  private static final int ANNEE_SUP = 1900;

  /**
   * Constructeur de la BaseDeDonnees.
   * 
   * <p>Permet de cr?er la connexion vers la base de donn?es ainsi que pr?parer les futures requ?tes
   * SQL.
   * 
   * @throws SQLException si la connexion ?choue
   */
  public BaseDeDonnees() throws SQLException {
    System.out.println("Connecting to a selected database...");
    try {
      String url = "jdbc:mariadb://obiwan2.univ-brest.fr:3306/zil3-zgoasguma";
      String user = "zgoasguma";
      String passwd = "hfe3a838";
      connection = DriverManager.getConnection(url, user, passwd);
      sqlStatement =
          connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

      System.out.println("Connexion OK");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Met ? jour la liste des membres ? partir de la base de donn?es.
   * 
   * @param g l'instance de Gestion de l'application (fa?ade)
   */
  public void updateMembres(Gestion g) {
    try {
      ResultSet lesMembres = sqlStatement.executeQuery("SELECT * FROM MEMBRE");
      g.clearListeMembres();
      while (lesMembres.next()) {
        g.miseAJourListeMembres(lesMembres.getString("pseudo"), lesMembres.getString("nom"),
            lesMembres.getString("prenom"), lesMembres.getString("lieuNaissance"),
            lesMembres.getDate("dateNaissance"), lesMembres.getString("ville"),
            lesMembres.getString("mail"), "Dommage, mdp1 crypt? !");
      }
      lesMembres.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Met ? jour la liste des ?v?nements ? partir de la base de donn?es.
   * 
   * @param g l'instance de Gestion de l'application (fa?ade)
   */
  public void updateEvenements(Gestion g) {
    try {
      ResultSet lesEvenements = sqlStatement.executeQuery("SELECT * FROM EVENEMENT");
      int max = -1;
      g.clearListeEvenements();
      while (lesEvenements.next()) {
        TypeEvenement t = TypeEvenement.valueOf(lesEvenements.getString("type"));
        if (lesEvenements.getInt("id") > max) {
          max = lesEvenements.getInt("id");
        }
        g.miseAJourListeEvenements(lesEvenements.getInt("id"), lesEvenements.getString("nom"),
            lesEvenements.getString("descriptif"), lesEvenements.getString("image"),
            lesEvenements.getDate("date"), lesEvenements.getString("lieu"),
            lesEvenements.getInt("nbMaxPersonnes"), t);
      }
      lesEvenements.close();
      g.setProchainIdEvenement(max + 1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Met ? jour la liste des participations ? partir de la base de donn?es.
   * 
   * @param g l'instance de Gestion de l'application (fa?ade)
   */
  public void updateParticipation(Gestion g) {
    try {
      ResultSet lesParticipations = sqlStatement.executeQuery("SELECT * FROM PARTICIPATION");
      g.clearListeParticipations();
      while (lesParticipations.next()) {
        Evenement env = null;
        ArrayList<Evenement> evenements = g.getListeEvenements();
        for (Evenement e : evenements) {
          if (e.getId() == lesParticipations.getInt("idEvenement")) {
            env = e;
          }
        }

        Membre mem = null;
        ArrayList<Membre> membres = g.getListeMembres();
        for (Membre m : membres) {
          if (m.getPseudo().equals(lesParticipations.getString("pseudoMembre"))) {
            mem = m;
          }
        }
        g.miseAJourListeParticipations(env, mem, lesParticipations.getInt("nbInscrit"),
            lesParticipations.getString("informations"));
      }
      lesParticipations.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Renvoie vrai ou faux suivant si un membre avec le pseudo fourni en param?tre existe dans la
   * liste.
   * 
   * @param unPseudo le pseudo du membre ? trouver
   * @return {@code true} si un membre de la liste a pour pseudo {@code unPseudo}, {@code false}
   *         sinon
   * @throws SQLException si la requ?te SQL provoque une erreur
   */
  public boolean membrePresent(String unPseudo) throws SQLException {
    ResultSet leMembre =
        sqlStatement.executeQuery("SELECT * FROM MEMBRE WHERE pseudo=" + '"' + unPseudo + '"');
    return leMembre.next();
  }


  /**
   * Ajoute un membre ? la base de donn?es, en ex?cutant une requ?te SQL "INSERT".
   * 
   * @param g instance de la gestion (fa?ade)
   * @param m le membre ? ajouter dans la base de donn?es
   * @return true si l'ajout ? la base de donn?es fonctionne sinon false.
   */
  public boolean ajouterMembre(Gestion g, Membre m) {
    boolean res = true;

    // Suffixe
    String pseudoReverse = "";
    for (int i = m.getPseudo().length() - 1; i >= 0; i--) {
      pseudoReverse += m.getPseudo().charAt(i);
    }

    // Pr?fixe + mdp + suffixe
    String mdp = m.getPseudo() + m.getMotDePasse() + pseudoReverse;
    String mdpSecurise = this.sha256(mdp);

    @SuppressWarnings("deprecation")
    String query =
        "INSERT INTO MEMBRE (pseudo,nom,prenom,lieuNaissance,dateNaissance,ville,mail,motDePasse)"
            + " VALUES (" + '"' + m.getPseudo() + '"' + "," + '"' + m.getNom() + '"' + "," + '"'
            + m.getPrenom() + '"' + "," + '"' + m.getLieuNaissance() + '"' + "," + "DATE_FORMAT("
            + '"' + (m.getDateNaissance().getYear() + ANNEE_SUP) + "-"
            + (m.getDateNaissance().getMonth() + 1) + "-" + m.getDateNaissance().getDate() + '"'
            + "," + '"' + "%Y-%m-%d" + '"' + ")," + '"' + m.getVille() + '"' + "," + '"'
            + m.getMail() + '"' + "," + '"' + mdpSecurise + '"' + ")";
    try {
      sqlStatement.executeUpdate(query);
      this.updateMembres(g);
      this.updateParticipation(g);
    } catch (SQLException e) {
      System.out.println("ajout impossible de " + m.getPseudo());
      e.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * S?curise le mot de passe avec l'algorithme SHA256.
   * 
   * @param base le mot de passe de base
   * @return le mot de passe hach? et sal? (s?curis?)
   */
  private String sha256(String base) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(base.getBytes("UTF-8"));
      StringBuffer hexString = new StringBuffer();

      for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }

      return hexString.toString();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Renvoie vrai ou faux suivant si un ?v?nement avec l'identifiant fourni en param?tre existe dans
   * la liste.
   * 
   * @param id l'identifiant de l'?v?nement ? trouver
   * @return {@code true} si un ?v?nement de la liste a pour identifiant {@code id}, {@code false}
   *         sinon
   * @throws SQLException si la requ?te SQL provoque une erreur
   */
  public boolean evenementPresent(int id) throws SQLException {
    ResultSet evenement =
        sqlStatement.executeQuery("SELECT * FROM EVENEMENT WHERE id=" + '"' + id + '"');
    return evenement.next();
  }

  /**
   * Ajoute un ?v?nement ? la base de donn?es, en ex?cutant une requ?te SQL "INSERT".
   * 
   * @param g instance de la classe Gestion (fa?ade)
   * @param e l'?v?nement ? ajouter dans la base de donn?es
   * @return true si l'ajout ? la base de donn?es fonctionne sinon false.
   */
  public boolean ajouterEvenement(Gestion g, Evenement e) {
    boolean res = true;
    @SuppressWarnings("deprecation")
    String query =
        "INSERT INTO EVENEMENT (id,nom,descriptif,image,date,lieu,nbMaxPersonnes,type) VALUES ("
            + '"' + e.getId() + '"' + "," + '"' + e.getNom() + '"' + "," + '"' + e.getDescriptif()
            + '"' + "," + '"' + e.getImage() + '"' + "," + "DATE_FORMAT(" + '"'
            + (e.getDate().getYear() + ANNEE_SUP) + "-" + (e.getDate().getMonth() + 1) + "-"
            + e.getDate().getDate() + " " + e.getDate().getHours() + ":" + e.getDate().getMinutes()
            + ":" + e.getDate().getSeconds() + '"' + "," + '"' + "%Y-%m-%d %H:%i:%s" + '"' + "),"
            + '"' + e.getLieu() + '"' + "," + '"' + e.getNbMaxPersonnes() + '"' + "," + '"'
            + e.getType().toString() + '"' + ")";
    try {
      sqlStatement.executeUpdate(query);
      this.updateEvenements(g);
      this.updateParticipation(g);
    } catch (SQLException er) {
      System.out.println("ajout impossible de " + e.getId());
      er.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Renvoie vrai ou faux suivant si une participation avec l'identifiant de l'?v?nement et le
   * pseudo du membre fournis en param?tre existe dans la liste.
   * 
   * @param id l'identifiant de l'?v?nement de la participation ? trouver
   * @param pseudo le pseudo du membre de la participation ? trouver
   * @return {@code true} si une particpation de la liste a pour identifiant {@code id} et pour
   *         pseudo {@code pseudo}, {@code false} sinon
   * @throws SQLException si la requ?te SQL provoque une erreur
   */
  public boolean participationPresente(int id, String pseudo) throws SQLException {
    ResultSet participation =
        sqlStatement.executeQuery("SELECT * FROM PARTICIPATION WHERE idEvenement=" + '"' + id + '"'
            + " and pseudoMembre=" + '"' + pseudo + '"');
    return participation.next();
  }

  /**
   * Ajoute une participation ? la base de donn?es, en ex?cutant une requ?te SQL "INSERT".
   * 
   * @param g instance de la classe Gestion (fa?ade)
   * @param p la participation ? ajouter dans la base de donn?es
   * @return true si l'ajout ? la base de donn?es fonctionne sinon false.
   */
  public boolean ajouterParticipation(Gestion g, Participation p) {
    boolean res = true;
    String query =
        "INSERT INTO PARTICIPATION (idEvenement,pseudoMembre,nbInscrit,informations) VALUES (" + '"'
            + p.getEvenement().getId() + '"' + "," + '"' + p.getMembre().getPseudo() + '"' + ","
            + '"' + p.getNbInscrit() + '"' + "," + '"' + p.getInformation() + '"' + ")";
    try {
      sqlStatement.executeUpdate(query);
      this.updateParticipation(g);
    } catch (SQLException er) {
      System.out.println(
          "ajout impossible de " + p.getEvenement().getId() + " " + p.getMembre().getPseudo());
      er.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Supprime un ?v?nement de la base de donn?es, en ex?cutant une requ?te SQL "DELETE".
   * 
   * @param g instance de la classe Gestion (fa?ade)
   * @param unId identifiant de l'?v?nement ? supprimer
   * @return true si la suppression ? la base de donn?es fonctionne sinon false.
   */
  public boolean supprimerEvenement(Gestion g, int unId) {
    boolean res = true;
    String query = "DELETE FROM EVENEMENT WHERE id=" + unId;
    String query2 = "DELETE FROM PARTICIPATION WHERE idEvenement=" + unId;
    try {
      sqlStatement.executeUpdate(query2);
      sqlStatement.executeUpdate(query);
      this.updateEvenements(g);
      this.updateParticipation(g);
    } catch (SQLException er) {
      System.out.println("supression impossible de " + unId);
      er.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Supprime un membre de la base de donn?es, en ex?cutant une requ?te SQL "DELETE".
   * 
   * @param g instance de la classe Gestion (fa?ade)
   * @param unPseudo pseudo du membre ? supprimer
   * @return true si la suppression ? la base de donn?es fonctionne sinon false.
   */
  public boolean supprimerMembre(Gestion g, String unPseudo) {
    boolean res = true;
    String query = "DELETE FROM MEMBRE WHERE pseudo=" + '"' + unPseudo + '"';
    String query2 = "DELETE FROM PARTICIPATION WHERE pseudoMembre=" + '"' + unPseudo + '"';
    try {
      sqlStatement.executeUpdate(query2);
      sqlStatement.executeUpdate(query);
      this.updateMembres(g);
      this.updateParticipation(g);
    } catch (SQLException er) {
      System.out.println("supression impossible de " + unPseudo);
      er.printStackTrace();
      res = false;
    }
    return res;
  }



  /**
   * Modifie un membre de la base de donn?es, en ex?cutant une requ?te SQL "UPDATE".
   * 
   * @param g instance de la classe GEstion (fa?ade)
   * @param m membre contenant les donn?es ? modifier
   * @param mdpPresent {@code true} si le mot de passe doit ?tre modifi?, {@code false} sinon
   * @return true si la modification ? la base de donn?es fonctionne sinon false.
   */
  @SuppressWarnings("deprecation")
  public boolean modifierMembre(Gestion g, Membre m, boolean mdpPresent) {
    boolean res = true;

    // Suffixe
    String pseudoReverse = "";
    for (int i = m.getPseudo().length() - 1; i >= 0; i--) {
      pseudoReverse += m.getPseudo().charAt(i);
    }

    // Pr?fixe + mdp + suffixe
    String mdp = m.getPseudo() + m.getMotDePasse() + pseudoReverse;
    String mdpSecurise = this.sha256(mdp);

    String query;
    query = "UPDATE MEMBRE SET nom=" + '"' + m.getNom() + '"' + ",prenom=" + '"' + m.getPrenom()
        + '"' + ",lieuNaissance=" + '"' + m.getLieuNaissance() + '"' + ",dateNaissance=DATE_FORMAT("
        + '"' + (m.getDateNaissance().getYear() + ANNEE_SUP) + "-" + m.getDateNaissance().getMonth()
        + "-" + m.getDateNaissance().getDate() + '"' + "," + '"' + "%Y-%m-%d" + '"' + "),ville="
        + '"' + m.getVille() + '"' + ",mail=" + '"' + m.getMail() + '"'
        + (mdpPresent ? (",motDePasse=\"" + mdpSecurise + '"') : "") + " WHERE pseudo=" + '"'
        + m.getPseudo() + '"';
    try {
      sqlStatement.executeUpdate(query);
      this.updateMembres(g);
      this.updateParticipation(g);
    } catch (SQLException e) {
      System.out.println("Modification impossible de " + m.getPseudo());
      e.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Modifie un ?v?nement de la base de donn?es, en ex?cutant une requ?te SQL "UPDATE".
   * 
   * @param g instance de la classe Gestion (fa?ade)
   * @param e ?v?nement contenant les donn?es ? modifier
   * @return true si la modification ? la base de donn?es fonctionne sinon false.
   */
  @SuppressWarnings("deprecation")
  public boolean modifierEvenement(Gestion g, Evenement e) {
    boolean res = true;
    String query;
    query = "UPDATE EVENEMENT SET nom='" + e.getNom() + "', descriptif='" + e.getDescriptif()
        + "', image='" + e.getImage() + "', date=DATE_FORMAT('"
        + (e.getDate().getYear() + ANNEE_SUP) + "-" + e.getDate().getMonth() + "-"
        + e.getDate().getDate() + "','%Y-%m-%d'), lieu='" + e.getLieu() + "', nbMaxPersonnes="
        + e.getNbMaxPersonnes() + ", type='" + e.getType() + "' WHERE id=" + e.getId();
    try {
      sqlStatement.executeUpdate(query);
      this.updateEvenements(g);
      this.updateParticipation(g);
    } catch (SQLException e2) {
      System.out.println("Modification impossible de " + e.getId());
      e2.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Modifie une participation dans la base de donn?es, en ex?cutant une requ?te SQL "UPDATE".
   * 
   * @param g instance de la classe Gestion (fa?ade)
   * @param p participation contenant les donn?es ? modifier
   * @return true si la modification ? la base de donn?es fonctionne sinon false.
   */
  public boolean modifierParticipation(Gestion g, Participation p) {
    boolean res = true;
    String query;
    query = "UPDATE PARTICIPATION SET idEvenement='" + p.getEvenement().getId()
        + "', pseudoMembre='" + p.getMembre().getPseudo() + "', nbInscrit='" + p.getNbInscrit()
        + "', informations='" + p.getInformation() + "' WHERE idEvenement="
        + p.getEvenement().getId() + " and pseudoMembre=" + '"' + p.getMembre().getPseudo() + '"';
    try {
      sqlStatement.executeUpdate(query);
      this.updateParticipation(g);
    } catch (SQLException e2) {
      System.out.println("Modification impossible");
      e2.printStackTrace();
      res = false;
    }
    return res;
  }


  /**
   * Supprime une participation de la base de donn?es, en ex?cutant une requ?te SQL "DELETE".
   * 
   * @param g instance de la classe Gestion (fa?ade)
   * @param p la participation ? supprimer de la base de donn?es
   * @return true si la suppression ? la base de donn?es fonctionne sinon false.
   */
  public boolean supprimerParticipation(Gestion g, Participation p) {
    boolean res = true;
    String query = "DELETE FROM PARTICIPATION WHERE pseudoMembre=" + '"' + p.getMembre().getPseudo()
        + '"' + " and idEvenement= " + p.getEvenement().getId();
    try {
      sqlStatement.executeUpdate(query);
      this.updateParticipation(g);
    } catch (SQLException er) {
      System.out.println("supression impossible de la participation (pseudoMembre:"
          + p.getMembre().getPseudo() + ", idEvenement:" + p.getEvenement().getId());
      er.printStackTrace();
      res = false;
    }
    return res;
  }
}
