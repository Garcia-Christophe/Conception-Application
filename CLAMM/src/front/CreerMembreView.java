package front;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class CreerMembreView {

  public CreerMembreView() {
    
    //setup de la nouvelle fenêtre
    GridPane grid = new GridPane();
    grid.setGridLinesVisible(true);
    
    Scene secondScene =new Scene(grid, App.getStage().getWidth() / 2, App.getStage().getHeight() / 2);
    // New window (Stage)
    Stage newWindow = new Stage();
    newWindow.setTitle("Ajouter un membre");
    newWindow.setScene(secondScene);

    newWindow.show();

    // création des colonnes
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(50);
    grid.getColumnConstraints().addAll(col, col);

    // création des lignes
    RowConstraints row20 = new RowConstraints();
    row20.setPercentHeight(20);
    RowConstraints row = new RowConstraints();
    row.setVgrow(Priority.ALWAYS);
    RowConstraints row10 = new RowConstraints();
    row10.setPercentHeight(10);
    grid.getRowConstraints().addAll(row20, row, row10);

    // les éléments
    HBox header = new HBox();
    header.setStyle("-fx-background-color: #85B9DE;");
    Label labelHead = new Label("AJOUTER UN MEMBRE");
    labelHead.getStyleClass().add("headLabel");
    header.getChildren().add(labelHead);
    header.setAlignment(Pos.CENTER);

    Button BtnAjouter = new Button("Ajouter");
    Button BtnAnnuler = new Button("Annuler");
    
    BtnAjouter.setPrefHeight(Integer.MAX_VALUE);
    BtnAjouter.setPrefWidth(Integer.MAX_VALUE);
    BtnAnnuler.setPrefHeight(Integer.MAX_VALUE);
    BtnAnnuler.setPrefWidth(Integer.MAX_VALUE);

    HBox PaneButtons = new HBox();
    PaneButtons.getChildren().addAll(BtnAnnuler, BtnAjouter);
    PaneButtons.setAlignment(Pos.CENTER);

    grid.add(header, 0, 0, 2, 1);
    grid.add(PaneButtons, 1, 2, 1, 1);

    BtnAjouter.setOnAction(e -> {
      System.out.println("Ajout du membre si valide");
      newWindow.close();
    });

    BtnAnnuler.setOnAction(e -> {
      System.out.println("Annulation...");
      newWindow.close();
    });
  }

}
