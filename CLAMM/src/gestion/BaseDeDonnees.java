package gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDeDonnees {
  Connection connection;
  Statement sqlStatement;


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

  public static void main(String[] args) throws SQLException {
    BaseDeDonnees test = new BaseDeDonnees();
  }
}
