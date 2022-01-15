package front;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class EvenementsView extends GridPane {

  public EvenementsView() {

    // création des colonnes
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(50);
    getColumnConstraints().addAll(col, col);

    // création des lignes
    RowConstraints row10 = new RowConstraints();
    row10.setPercentHeight(10);
    RowConstraints row20 = new RowConstraints();
    row20.setPercentHeight(20);
    RowConstraints row70 = new RowConstraints();
    row70.setPercentHeight(70);
    getRowConstraints().addAll(row20, row10, row70);

    // setGridLinesVisible(true);

    // header
    HBox paneMembre = new HBox();
    paneMembre.setStyle("-fx-background-color: #94CEF7;");
    paneMembre.getStyleClass().add("hover");
    Label labelMembres = new Label("MEMBRES");
    labelMembres.getStyleClass().add("headLabel");
    paneMembre.getChildren().add(labelMembres);
    paneMembre.setAlignment(Pos.CENTER);

    HBox paneEvenements = new HBox();
    paneEvenements.setStyle("-fx-background-color: #85B9DE;");
    paneEvenements.getStyleClass().add("hover");
    Label labelEvenements = new Label("EVENEMENTS");
    labelEvenements.getStyleClass().add("headLabel");
    paneEvenements.getChildren().add(labelEvenements);
    paneEvenements.setAlignment(Pos.CENTER);

    paneMembre.setOnMouseClicked(e -> {
      App.setScene(new MembresView());
    });

    Button BtnAjoutEvent = new Button("Créer un évènement");

    ScrollPane leftScroll = new ScrollPane();
    leftScroll.setStyle(
        "-fx-border-style: hidden solid hidden hidden; -fx-border-width: 2.5; -fx-border-color: black;");

    Pane rightPart = new Pane();
    rightPart.setStyle(
        "-fx-border-style: hidden hidden hidden solid; -fx-border-width: 2.5; -fx-border-color: black;");

    // Ajout des éléments au corps principal
    add(paneMembre, 0, 0, 1, 1);
    add(paneEvenements, 1, 0, 1, 1);
    add(BtnAjoutEvent, 0, 1, 1, 1);
    add(leftScroll, 0, 2, 1, 1);
    add(rightPart, 1, 2, 1, 1);
  }

}
