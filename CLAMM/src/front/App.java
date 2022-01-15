package front;

import gestion.Gestion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {

  private static Stage stage;
  private static Gestion gestion;
  private static GridPane grid = new GridPane(); //le corps de la page

  @Override
  public void start(Stage stage) throws Exception {
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
