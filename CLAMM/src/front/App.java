package front;

import java.text.SimpleDateFormat;
import java.util.Date;
import gestion.Gestion;
import gestionEvenements.TypeEvenement;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

  private static Stage stage;
  public static Gestion gestion = new Gestion();
  private static GridPane grid = new GridPane(); //le corps de la page
  
  public Date d = new Date();

  @Override
  public void start(Stage stage) throws Exception {
  //TODO à supprimer : ajout provisoires de membres pour tester
    gestion.ajouterMembre("Hb", "Durand", "Habib", "Tourcoing", d, "Tourcoing", "habibDe@Tourcoing.com","TourcoingCestSympa");
    gestion.ajouterMembre("AA", "Test", "Habab", "Tourcoing", d, "Tourcoing", "habibDe@ng.com","PouetPouet");
    gestion.ajouterMembre("Ba", "Durand", "Haboub", "TourcoingAussi", d, "Tourcoing", "Haboub@Tourcoing.com","Bababa");
    
    gestion.ajouterEvenement("Soirée Crêpes", "On mange des crêpes chez bapt dimanche", "rien", new Date(2022-1900,11,25,19,30), "Chez bapt", 42, TypeEvenement.REPAS);
    gestion.ajouterEvenement("Paintball", "Paintball entre collègues", "rien", new Date(2022-1900,11,25,19,30), "3 rue des billes", 15, TypeEvenement.ANIMATION);
    
    App.stage = stage;
    
    Scene scene = new Scene(grid);
    setScene(new MembresView());
    
    stage.setScene(scene);

    grid.getStylesheets().add("front/style.css");
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(100);
    grid.getColumnConstraints().add(col);
    RowConstraints row = new RowConstraints();
    row.setPercentHeight(100);
    grid.getRowConstraints().add(row);

    stage.show();
    stage.setTitle("CLAMM");
    stage.setMaximized(true);
  }

  public static Stage getStage() {
    return stage;
  }
  
  //définir une nouvelle page, chaque page étant un gridpane
  public static void setScene(GridPane n) {
    grid.getChildren().clear();
    grid.add(n, 0, 0);
  }

  
  public static Gestion getGestion() {
    return gestion;
  }
  
  public static void main(String[] args) {
    launch();
  }

}
