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
 * différentes scènes, et gère également l'instance de gestion, permettant la gestion 
 * des membres et des évènements, à savoir le lien entre l'interface graphique et les 
 * classes métier.
 * 
 * @author Léo Couedor
 * @version 1.00
 */

public class App extends Application {

  /**
   * Fenêtre principale de l'application.
   */
  private static Stage stage;
  
  /**
   * Instance de Gestion, pour la gestion des membres et évènements.
   */
   public static Gestion gestion;
  
  
  /**
   * Element principal de la fenêtre.
   */
  private static GridPane grid = new GridPane(); // le corps de la page

  /**
   * Méthode d'initialisation de la fenêtre principale, avec la définition des dimensions 
   * et de la structure de la page principale.
   * 
   * @param stage la fenêtre principale.
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
   * Méthode pour définir un nouvel élément à afficher dans la scene.
   * 
   * @param n le gridpane à mettre en avant dans la scene
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
   * Méthode de lancement de l'application.
   */
  public static void main(String[] args) {
    launch();
  }

}
