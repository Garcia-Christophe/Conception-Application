package front;

import gestion.evenements.Evenement;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class EvenementsView extends GridPane {

  HBox rightPart = new HBox();

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
    
    Image img = new Image("img/ic_action_group.png");
    ImageView view = new ImageView(img);
    view.setFitHeight(50);
    view.setPreserveRatio(true);

    // Bouton pour la nouvelle fenêtre ajouter un évènement
    Button BtnAjoutEvenement = new Button();
    BtnAjoutEvenement.setGraphic(view);
    BtnAjoutEvenement.setOnAction(e -> {
      new CreerEvenementView(); // Appel à la nouvelle scène
    });

    // Partie de gauche, le scroll pane
    ScrollPane leftScroll = new ScrollPane();
    leftScroll.setStyle(
        "-fx-border-style: hidden solid hidden hidden; -fx-border-width: 2.5; -fx-border-color: black;");
    VBox vScroll = new VBox(); // Le contenu unique du scrollPane
    leftScroll.setContent(vScroll);
    for (Evenement e : App.getGestion().getListeEvenements()) {
      vScroll.getChildren().add(ajEvenement(e));
    }
    vScroll.prefWidthProperty().bind(this.widthProperty().divide(2).subtract(10));

    // Partie de droite pour l'affichage des infos pour chaque membre au clic
    rightPart.setStyle(
        "-fx-border-style: hidden hidden hidden solid; -fx-border-width: 2.5; -fx-border-color: black;");
    rightPart.setAlignment(Pos.CENTER);

    // Ajout des éléments au corps principal
    add(paneMembre, 0, 0, 1, 1);
    add(paneEvenements, 1, 0, 1, 1);
    add(BtnAjoutEvenement, 0, 1, 1, 1);
    add(leftScroll, 0, 2, 1, 1);
    add(rightPart, 1, 2, 1, 1);
  }

  // Fonction pour créer un bloc à ajouter à la création d'un évènement pour l'affichage
  public HBox ajEvenement(Evenement m) {
    HBox elem = new HBox();
    elem.getChildren().addAll(new Label(m.getNom()));

    // Les event pour ajouter ou retirer la classe elemHovered pour l'animation au survol d'un évènement dans la liste
    elem.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        elem.getStyleClass().add("elemHovered");
      }
    });

    elem.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        elem.getStyleClass().remove("elemHovered");
      }
    });

    elem.getStyleClass().addAll("hover", "font20"); // L'ajout des classes pour changer le curseur
                                                    // au survol et la taille de font
    elem.setPadding(new Insets(5, 100, 5, 100)); // Les paddings
    elem.setAlignment(Pos.CENTER); // Le placement au milieu

    elem.setOnMouseClicked(e -> {
      rightPart.getChildren().clear(); // on supprime la fiche déjà affichée au cas ou il y en avait
                                       // déjà une
      VBox fiche = new VBox();
      fiche.prefWidthProperty().bind(this.widthProperty().divide(3).subtract(10));
      rightPart.getChildren().add(fiche);
      
      Image img = new Image("img/ic_action_cancel.png");
      ImageView view = new ImageView(img);
      view.setFitHeight(50);
      view.setPreserveRatio(true);

      Button btnSuppr = new Button();
      btnSuppr.setGraphic(view);
      btnSuppr.setOnMouseClicked(a -> {
        App.getGestion().supprimerEvenement(m.getId()); // Suppression par l'id
        App.setScene(new EvenementsView()); // Recréer une nouvelle scène pour actualiser l'affichage
      });
      
      img = new Image("img/ic_action_edit.png");
      view = new ImageView(img);
      view.setFitHeight(50);
      view.setPreserveRatio(true);

      Button btnModifier = new Button();
      btnModifier.setGraphic(view);
      btnModifier.setOnMouseClicked(a -> {
        new CreerEvenementView(m);
      });
      
      HBox hBoutons = new HBox();
      hBoutons.setAlignment(Pos.CENTER);
      hBoutons.getChildren().addAll(btnSuppr, btnModifier);

      fiche.getChildren().addAll(new Label(m.getNom()), new Label(m.getDate().toString()),
          new Label(m.getType().toString()), new Label(m.getLieu()), hBoutons);
      fiche.getStyleClass().add("font20");
      fiche.setAlignment(Pos.CENTER);
    });

    return elem;
  }

}
