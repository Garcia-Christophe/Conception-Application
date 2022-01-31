package front;

import java.util.Date;
import gestion.Gestion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * La class App est la classe controlleur de l'application. Elle permet l'affichage des 
 * diff�rentes sc�nes, et g�re �galement l'instance de gestion, permettant la gestion 
 * des membres et des �v�nements, � savoir le lien entre l'interface graphique et les 
 * classes m�tier.
 * 
 * @author L�o Couedor
 * @version 1.00
 */

public class App extends Application {

  /**
   * Fen�tre principale de l'application.
   */
  private static Stage stage;
  
  /**
   * Instance de Gestion, pour la gestion des membres et �v�nements.
   */
   public static Gestion gestion;
  
  
  /**
   * Element principal de la fen�tre.
   */
  private static GridPane grid = new GridPane(); // le corps de la page

  /**
   * M�thode d'initialisation de la fen�tre principale, avec la d�finition des dimensions 
   * et de la structure de la page principale.
   * 
   * @param stage la fen�tre principale.
   */
  @Override
  public void start(Stage stage) throws Exception {
    gestion =  new Gestion();
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

  /**
   * Retourne le stage de l'application.
   * 
   * @return le stage de l'application
   */
  public static Stage getStage() {
    return stage;
  }

  /**
   * M�thode pour d�finir un nouvel �l�ment � afficher dans la scene.
   * 
   * @param n le gridpane � mettre en avant dans la scene
   */
  public static void setScene(GridPane n) {
    grid.getChildren().clear();
    grid.add(n, 0, 0);
  }

  /**
   * Retourne l'instance de gestion.
   * 
   * @return l'instance de gestion
   */
  public static Gestion getGestion() {
    return gestion;
  }

  /**
   * M�thode de lancement de l'application.
   */
  public static void main(String[] args) {
    launch();
  }

}
