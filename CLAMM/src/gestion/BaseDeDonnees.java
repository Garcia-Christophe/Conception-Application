package gestion;

import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;
import gestion.membres.Membre;
import gestion.participation.Participation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * La classe base de données permet de faire la liaison entre l'application Java et la base de
 * données externe.
 * 
 * <p>
 * La classe peut créer, ajouter, modifier et supprimer des membres et des évènements au niveau de
 * la base de données.
 * 
 * @author Alexia, Christophe
 * @version 2.00
 */
public class BaseDeDonnees {

  /**
   * Connexion à la base de données.
   */
  Connection connection;

  /**
   * Permet d'exécuter les requêtes SQL sur la base de données connectée.
   */
  Statement sqlStatement;

  /**
   * Nombre d'années à ajouter pour une date.
   */
  private static final int ANNEE_SUP = 1900;

  /**
   * Constructeur de la BaseDeDonnées.
   * 
   * <p>
   * Permet de créer la connexion vers la base de données ainsi que préparer les futures requêtes
   * SQL.
   * 
   * @throws SQLException si la connexion échoue
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
   * Initialise la liste des membres à partir de la base de données.
   * 
   * @param g l'instance de Gestion de l'application (façade)
   */
  public void initMembre(Gestion g) {
    try {
      ResultSet lesMembres = sqlStatement.executeQuery("SELECT * FROM MEMBRE");
      while (lesMembres.next()) {
        g.ajouterMembre(lesMembres.getString("pseudo"), lesMembres.getString("nom"),
            lesMembres.getString("prenom"), lesMembres.getString("lieuNaissance"),
            lesMembres.getDate("dateNaissance"), lesMembres.getString("ville"),
            lesMembres.getString("mail"), "Dommage, mdp crypté !");
      }
      lesMembres.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Met à jour la liste des membres à partir de la base de données.
   * 
   * @param g l'instance de Gestion de l'application (façade)
   */
  public void updateMembres(Gestion g) {
    try {
      ResultSet lesMembres = sqlStatement.executeQuery("SELECT * FROM MEMBRE");
      g.clearListeMembres();
      while (lesMembres.next()) {
        g.miseAJourListeMembres(lesMembres.getString("pseudo"), lesMembres.getString("nom"),
            lesMembres.getString("prenom"), lesMembres.getString("lieuNaissance"),
            lesMembres.getDate("dateNaissance"), lesMembres.getString("ville"),
            lesMembres.getString("mail"), "Dommage, mdp crypté !");
      }
      lesMembres.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Initialise la liste des événements à partir de la base de données.
   * 
   * @param g l'instance de Gestion de l'application (façade)
   */
  public void initEvenement(Gestion g) {
    try {
      ResultSet lesEvenements = sqlStatement.executeQuery("SELECT * FROM EVENEMENT");
      int max = -1;
      while (lesEvenements.next()) {
        TypeEvenement t = TypeEvenement.valueOf(lesEvenements.getString("type"));
        if (lesEvenements.getInt("id") > max) {
          max = lesEvenements.getInt("id");
        }
        g.ajouterEvenement(lesEvenements.getInt("id"), lesEvenements.getString("nom"),
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
   * Met à jour la liste des événements à partir de la base de données.
   * 
   * @param g l'instance de Gestion de l'application (façade)
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
   * Initialise la liste des participations à partir de la base de données.
   * 
   * @param g l'instance de Gestion de l'application (façade)
   */
  public void initParticipation(Gestion g) {
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

        Participation p = new Participation(env, mem, lesParticipations.getInt("nbInscrit"),
            lesParticipations.getString("informations"));
        g.ajouterParticipation(p);
      }
      lesParticipations.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Renvoie vria ou faux suivant si un membre avec le pseudo fourni en paramètre existe dans la
   * liste.
   * 
   * @param unPseudo le pseudo du membre à trouver
   * @return {@code true} si un membre de la liste a pour pseudo {@code unPseudo}, {@code false}
   *         sinon
   * @throws SQLException si la requête SQL provoque une erreur
   */
  public boolean membrePresent(String unPseudo) throws SQLException {
    ResultSet leMembre =
        sqlStatement.executeQuery("SELECT * FROM MEMBRE WHERE pseudo=" + '"' + unPseudo + '"');
    return leMembre.next();
  }


  /**
   * Ajoute un membre à la base de données, en exécutant une requête SQL "INSERT".
   * 
   * @param g instance de la gestion (façade)
   * @param m le membre à ajouter dans la base de données
   * @return true si l'ajout à la base de données fonctionne sinon false.
   */
  public boolean ajouterMembre(Gestion g, Membre m) {
    boolean res = true;
    @SuppressWarnings("deprecation")
    String query =
        "INSERT INTO MEMBRE (pseudo,nom,prenom,lieuNaissance,dateNaissance,ville,mail,motDePasse)"
            + " VALUES (" + '"' + m.getPseudo() + '"' + "," + '"' + m.getNom() + '"' + "," + '"'
            + m.getPrenom() + '"' + "," + '"' + m.getLieuNaissance() + '"' + "," + "DATE_FORMAT("
            + '"' + (m.getDateNaissance().getYear() + ANNEE_SUP) + "-"
            + (m.getDateNaissance().getMonth() + 1) + "-" + m.getDateNaissance().getDate() + '"'
            + "," + '"' + "%Y-%m-%d" + '"' + ")," + '"' + m.getVille() + '"' + "," + '"'
            + m.getMail() + '"' + "," + '"' + m.getMotDePasse() + '"' + ")";
    try {
      sqlStatement.executeUpdate(query);
      this.updateMembres(g);
      this.initParticipation(g);
    } catch (SQLException e) {
      System.out.println("ajout impossible de " + m.getPseudo());
      e.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Renvoie vria ou faux suivant si un événement avec l'identifiant fourni en paramètre existe dans
   * la liste.
   * 
   * @param id l'identifiant de l'événement à trouver
   * @return {@code true} si un événement de la liste a pour identifiant {@code id}, {@code false}
   *         sinon
   * @throws SQLException si la requête SQL provoque une erreur
   */
  public boolean evenementPresent(int id) throws SQLException {
    ResultSet evenement =
        sqlStatement.executeQuery("SELECT * FROM EVENEMENT WHERE id=" + '"' + id + '"');
    return evenement.next();
  }

  /**
   * Ajoute un événement à la base de données, en exécutant une requête SQL "INSERT".
   * 
   * @param g instance de la classe Gestion (façade)
   * @param e l'événement à ajouter dans la base de données
   * @return true si l'ajout à la base de données fonctionne sinon false.
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
      this.initParticipation(g);
    } catch (SQLException er) {
      System.out.println("ajout impossible de " + e.getId());
      er.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Supprime un événement de la base de données, en exécutant une requête SQL "DELETE".
   * 
   * @param g instance de la classe Gestion (façade)
   * @param unId identifiant de l'événement à supprimer
   * @return true si la suppression à la base de données fonctionne sinon false.
   */
  public boolean supprimerEvenement(Gestion g, int unId) {
    boolean res = true;
    String query = "DELETE FROM EVENEMENT WHERE id=" + unId;
    String query2 = "DELETE FROM PARTICIPATION WHERE idEvenement=" + unId;
    try {
      sqlStatement.executeUpdate(query2);
      sqlStatement.executeUpdate(query);
      this.updateEvenements(g);
      this.initParticipation(g);
    } catch (SQLException er) {
      System.out.println("supression impossible de " + unId);
      er.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Supprime un membre de la base de données, en exécutant une requête SQL "DELETE".
   * 
   * @param g instance de la classe Gestion (façade)
   * @param unPseudo pseudo du membre à supprimer
   * @return true si la suppression à la base de données fonctionne sinon false.
   */
  public boolean supprimerMembre(Gestion g, String unPseudo) {
    boolean res = true;
    String query = "DELETE FROM MEMBRE WHERE pseudo=" + '"' + unPseudo + '"';
    String query2 = "DELETE FROM PARTICIPATION WHERE pseudoMembre=" + '"' + unPseudo + '"';
    try {
      sqlStatement.executeUpdate(query2);
      sqlStatement.executeUpdate(query);
      this.updateMembres(g);
      this.initParticipation(g);
    } catch (SQLException er) {
      System.out.println("supression impossible de " + unPseudo);
      er.printStackTrace();
      res = false;
    }
    return res;
  }



  /**
   * Modifie un membre de la base de données, en exécutant une requête SQL "UPDATE".
   * 
   * @param g instance de la classe GEstion (façade)
   * @param m membre contenant les données à modifier
   * @param mdpPresent {@code true} si le mot de passe doit être modifié, {@code false} sinon
   * @return true si la modification à la base de données fonctionne sinon false.
   */
  @SuppressWarnings("deprecation")
  public boolean modifierMembre(Gestion g, Membre m, boolean mdpPresent) {
    boolean res = true;
    String query;
    query = "UPDATE MEMBRE SET nom=" + '"' + m.getNom() + '"' + ",prenom=" + '"' + m.getPrenom()
        + '"' + ",lieuNaissance=" + '"' + m.getLieuNaissance() + '"' + ",dateNaissance=DATE_FORMAT("
        + '"' + (m.getDateNaissance().getYear() + ANNEE_SUP) + "-" + m.getDateNaissance().getMonth()
        + "-" + m.getDateNaissance().getDate() + '"' + "," + '"' + "%Y-%m-%d" + '"' + "),ville="
        + '"' + m.getVille() + '"' + ",mail=" + '"' + m.getMail() + '"'
        + (mdpPresent ? (",motDePasse=\"" + m.getMotDePasse() + '"') : "") + " WHERE pseudo=" + '"'
        + m.getPseudo() + '"';
    try {
      sqlStatement.executeUpdate(query);
      this.updateMembres(g);
      this.initParticipation(g);
    } catch (SQLException e) {
      System.out.println("Modification impossible de " + m.getPseudo());
      e.printStackTrace();
      res = false;
    }
    return res;
  }

  /**
   * Modifie un événement de la base de données, en exécutant une requête SQL "UPDATE".
   * 
   * @param g instance de la classe Gestion (façade)
   * @param e événement contenant les données à modifier
   * @return true si la modification à la base de données fonctionne sinon false.
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
      this.initParticipation(g);
    } catch (SQLException e2) {
      System.out.println("Modification impossible de " + e.getId());
      e2.printStackTrace();
      res = false;
    }
    return res;
  }

}
