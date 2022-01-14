package front;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
  private static Stage stage;

  @Override
  public void start(Stage stage) throws Exception {  
    App.stage = stage;
    Scene scene = new Scene(new MembresView());
    
    // grid.getStylesheets().add("style.css");
    stage.setScene(scene);
    
    stage.show();
    stage.setTitle("CLAMM");
    stage.setMaximized(true);
  }

  public static Stage getStage() {
    return stage;
  }

  public static void main(String[] args) {
    launch();
  }

}
