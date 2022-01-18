package front;

import gestionEvenements.Evenement;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class EvenementsView extends GridPane {

  HBox rightPart = new HBox();

  public EvenementsView() {

    // cr�ation des colonnes
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(50);
    getColumnConstraints().addAll(col, col);

    // cr�ation des lignes
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

    // Bouton pour la nouvelle fen�tre ajouter un �v�nement
    Button BtnAjoutEvenement = new Button("Ajout �v�nement");
    BtnAjoutEvenement.setOnAction(e -> {
      new CreerMembreView(); // Appel � la nouvelle sc�ne
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

    // Ajout des �l�ments au corps principal
    add(paneMembre, 0, 0, 1, 1);
    add(paneEvenements, 1, 0, 1, 1);
    add(BtnAjoutEvenement, 0, 1, 1, 1);
    add(leftScroll, 0, 2, 1, 1);
    add(rightPart, 1, 2, 1, 1);
  }

  // Fonction pour cr�er un bloc � ajouter � la cr�ation d'un �v�nement pour l'affichage
  public HBox ajEvenement(Evenement m) {
    HBox elem = new HBox();
    elem.getChildren().addAll(new Label(m.getNom()));

    // Les event pour ajouter ou retirer la classe elemHovered pour l'animation au survol d'un �v�nement dans la liste
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
      rightPart.getChildren().clear(); // on supprime la fiche d�j� affich�e au cas ou il y en avait
                                       // d�j� une
      VBox fiche = new VBox();
      fiche.prefWidthProperty().bind(this.widthProperty().divide(3).subtract(10));
      rightPart.getChildren().add(fiche);

      Button btnSuppr = new Button("Supprimer");
      btnSuppr.setOnMouseClicked(a -> {
        App.getGestion().supprimerEvenement(m.getId()); // Suppression par l'id
        App.setScene(new MembresView()); // Recr�er une nouvelle sc�ne pour actualiser l'affichage
      });

      Button btnModifier = new Button("Modifier");
      btnModifier.setOnMouseClicked(a -> {
        //new CreerMembreView(m);
      });

      fiche.getChildren().addAll(new Label(m.getNom()), new Label(m.getDate().toString()),
          new Label(m.getType().toString()), new Label(m.getLieu()), btnSuppr, btnModifier);
      fiche.getStyleClass().add("font20");
      fiche.setAlignment(Pos.CENTER);
    });

    return elem;
  }

}
