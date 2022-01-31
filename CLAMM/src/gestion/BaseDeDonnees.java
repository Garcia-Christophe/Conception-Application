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
   * <p>Permet de créer la connexion vers la base de données ainsi que préparer les futures requêtes
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
            lesMembres.getString("mail"), lesMembres.getString("motDePasse"));
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
   * Initialise la liste des participations à partir de la base de données.
   * 
   * @param g l'instance de Gestion de l'application (façade)
   */
  public void initParticipation(Gestion g) {
    try {
      ResultSet lesParticipations = sqlStatement.executeQuery("SELECT * FROM PARTICIPATION");
      while (lesParticipations.next()) {
        Evenement env = null;
        for (Evenement e : g.getListeEvenements()) {
          if (e.getId() == lesParticipations.getInt("idEvenement")) {
            env = e;
          }
        }

        Membre mem = null;
        for (Membre m : g.getListeMembres()) {
          if (m.getPseudo().equals(lesParticipations.getString("pseudoMembre"))) {
            mem = m;
          }
        }

        Participation p = new Participation(env, mem, lesParticipations.getInt("nbInscrit"),
            lesParticipations.getString("informations"));
        g.getListeParticipations().add(p);
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
   * @param m le membre à ajouter dans la base de données
   */
  public void ajouterMembre(Membre m) {
    // TODO regarder affichage erreur membre déjà présent si possible
    @SuppressWarnings("deprecation")
    String query =
        "INSERT INTO MEMBRE (pseudo,nom,prenom,lieuNaissance,dateNaissance,ville,mail,motDePasse)"
            + " VALUES (" + '"' + m.getPseudo() + '"' + "," + '"' + m.getNom() + '"' + "," + '"'
            + m.getPrenom() + '"' + "," + '"' + m.getLieuNaissance() + '"' + "," + "DATE_FORMAT("
            + '"' + (m.getDateNaissance().getYear() + ANNEE_SUP) + "-"
            + m.getDateNaissance().getMonth() + "-" + m.getDateNaissance().getDate() + '"' + ","
            + '"' + "%Y-%m-%d" + '"' + ")," + '"' + m.getVille() + '"' + "," + '"' + m.getMail()
            + '"' + "," + '"' + m.getMotDePasse() + '"' + ")";
    try {
      sqlStatement.executeUpdate(query);
    } catch (SQLException e) {
      System.out.println("ajout impossible de " + m.getPseudo());
      e.printStackTrace();
    }
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
   * @param e l'événement à ajouter dans la base de données
   */
  public void ajouterEvenement(Evenement e) {
    // TODO regarder affichage erreur evenement déjà présent si possible
    @SuppressWarnings("deprecation")
    String query =
        "INSERT INTO EVENEMENT (id,nom,descriptif,image,date,lieu,nbMaxPersonnes,type) VALUES ("
            + '"' + e.getId() + '"' + "," + '"' + e.getNom() + '"' + "," + '"' + e.getDescriptif()
            + '"' + "," + '"' + e.getImage() + '"' + "," + "DATE_FORMAT(" + '"'
            + (e.getDate().getYear() + ANNEE_SUP) + "-" + e.getDate().getMonth() + "-"
            + e.getDate().getDate() + " " + e.getDate().getHours() + ":" + e.getDate().getMinutes()
            + ":" + e.getDate().getSeconds() + '"' + "," + '"' + "%Y-%m-%d %H:%i:%s" + '"' + "),"
            + '"' + e.getLieu() + '"' + "," + '"' + e.getNbMaxPersonnes() + '"' + "," + '"'
            + e.getType().toString() + '"' + ")";
    try {
      sqlStatement.executeUpdate(query);
    } catch (SQLException er) {
      System.out.println("ajout impossible de " + e.getId());
      er.printStackTrace();
    }
  }

  /**
   * Supprime un événement de la base de données, en exécutant une requête SQL "DELETE".
   * 
   * @param unId identifiant de l'événement à supprimer
   */
  public void supprimerEvenement(int unId) {
    // TODO test erreur de suppression
    String query = "DELETE FROM EVENEMENT WHERE id=" + unId;
    String query2 = "DELETE FROM PARTICIPATION WHERE idEvenement=" + unId;
    try {
      sqlStatement.executeUpdate(query2);
      sqlStatement.executeUpdate(query);

    } catch (SQLException er) {
      System.out.println("supression impossible de " + unId);
      er.printStackTrace();
    }
  }

  /**
   * Supprime un membre de la base de données, en exécutant une requête SQL "DELETE".
   * 
   * @param unPseudo pseudo du membre à supprimer
   */
  public void supprimerMembre(String unPseudo) {
    // TODO test erreur de suppression
    String query = "DELETE FROM MEMBRE WHERE pseudo=" + '"' + unPseudo + '"';
    String query2 = "DELETE FROM PARTICIPATION WHERE pseudoMembre=" + '"' + unPseudo + '"';
    try {
      sqlStatement.executeUpdate(query2);
      sqlStatement.executeUpdate(query);

    } catch (SQLException er) {
      System.out.println("supression impossible de " + unPseudo);
      er.printStackTrace();
    }
  }



  /**
   * Modifie un membre de la base de données, en exécutant une requête SQL "UPDATE".
   * 
   * @param pseudo pseudo du membre à modifier
   * @param m membre contenant les données à modifier
   */
  @SuppressWarnings("deprecation")
  public void modifierMembre(String pseudo, Membre m) {
    String query;
    query = "UPDATE MEMBRE SET pseudo=" + '"' + m.getPseudo() + '"' + ", nom=" + '"' + m.getNom()
        + '"' + ",prenom=" + '"' + m.getPrenom() + '"' + ",lieuNaissance=" + '"'
        + m.getLieuNaissance() + '"' + ",dateNaissance=DATE_FORMAT(" + '"'
        + (m.getDateNaissance().getYear() + ANNEE_SUP) + "-" + m.getDateNaissance().getMonth() + "-"
        + m.getDateNaissance().getDate() + '"' + "," + '"' + "%Y-%m-%d" + '"' + "),ville=" + '"'
        + m.getVille() + '"' + ",mail=" + '"' + m.getMail() + '"' + ",motDePasse=" + '"'
        + m.getMotDePasse() + '"' + " WHERE pseudo=" + '"' + pseudo + '"';
    try {
      sqlStatement.executeUpdate(query);
    } catch (SQLException e) {
      System.out.println("supression impossible de " + m.getPseudo());
      e.printStackTrace();
    }
  }

  /**
   * Modifie un événement de la base de données, en exécutant une requête SQL "UPDATE".
   * 
   * @param id identifiant de l'événement à modifier
   * @param e événement contenant les données à modifier
   */
  @SuppressWarnings("deprecation")
  public void modifierEvenement(int id, Evenement e) {
    String query;
    query = "UPDATE EVENEMENT SET id=" + e.getId() + ", nom='" + e.getNom() + "', descriptif='"
        + e.getDescriptif() + "', image='" + e.getImage() + "', date=DATE_FORMAT('"
        + (e.getDate().getYear() + ANNEE_SUP) + "-" + e.getDate().getMonth() + "-"
        + e.getDate().getDate() + "','%Y-%m-%d'), lieu='" + e.getLieu() + "', nbMaxPersonnes="
        + e.getNbMaxPersonnes() + ", type='" + e.getType() + "' WHERE id=" + id;
    try {
      sqlStatement.executeUpdate(query);
    } catch (SQLException e2) {
      System.out.println("supression impossible de evenement " + e.getId());
      e2.printStackTrace();
    }
  }

}
