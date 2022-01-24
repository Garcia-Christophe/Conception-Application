package gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDonnees {
  String url;
  Connection con;
  public BaseDeDonnees() throws SQLException {
    url="";
    this.con = DriverManager.getConnection(url);;
  }
  
  
}
