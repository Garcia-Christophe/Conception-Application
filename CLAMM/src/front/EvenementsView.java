package front;

import java.text.SimpleDateFormat;
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

/**
 * La classe EvenementView est l'une des pages de l'application, 
 * elle gère l'affichage des évènements.
 * Elle est de type GridPane pour pouvoir l'intégrer au stage de 
 * l'application par l'intermédiaire du controlleur App.
 *   
 * @author Léo Couedor
 * @version 1.00
 */
public class EvenementsView extends GridPane {

  /**
   * Element de la partie de droite de l'application, qui permet 
   * l'affichage des informations complémentaires du membre sélectionné.
   */
  HBox rightPart = new HBox();

  /**
   * Constructeur de la page EvenementsView, avec la création et le placement de tous les éléments.
   */
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

    HBox btnAjoutEvenement= new HBox();
    btnAjoutEvenement.getChildren().add(view);
    btnAjoutEvenement.setAlignment(Pos.CENTER);
    btnAjoutEvenement.getStyleClass().add("hover");
    btnAjoutEvenement.setOnMouseClicked(e->{
      new CreerEvenementView(); //Appel à la nouvelle scène
    });
    
    btnAjoutEvenement.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            btnAjoutEvenement.getStyleClass().add("btnAjoutMembreHovered");
          }
        });

    btnAjoutEvenement.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            btnAjoutEvenement.getStyleClass().remove("btnAjoutMembreHovered");
          }
        });

    // Partie de gauche, le scroll pane
    ScrollPane leftScroll = new ScrollPane();
    leftScroll.setStyle("-fx-background-color:transparent; -fx-border-style: hidden solid hidden hidden; -fx-border-width: 2.5; -fx-border-color: black;");
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
    add(btnAjoutEvenement, 0, 1, 1, 1);
    add(leftScroll, 0, 2, 1, 1);
    add(rightPart, 1, 2, 1, 1);
  }

  /**
   * Méthode pour créer un bloc HBox, à ajouter à la création d'un évènement pour l'affichage. 
   * Cela créé également sa fiche d'informations complémentaire
   * ainsi que les boutons permettant de supprimer et modifier cet évènement.
   * 
   * @param m un évènement pour lequel créer son bloc dans la liste d'affichage 
   *     et sa fiche d'informations
   * @return un élément de type HBox
   */
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
      
      img = new Image("img/ic_action_group.png");
      view = new ImageView(img);
      view.setFitHeight(50);
      view.setPreserveRatio(true);
      
      Button btnParticipants = new Button();
      btnParticipants.setGraphic(view);
      
      btnParticipants.setOnMouseClicked(a -> {
        new ParticipationsView(m);
      });

      Button btnModifier = new Button();
      btnModifier.setGraphic(view);
      btnModifier.setOnMouseClicked(a -> {
        new CreerEvenementView(m);
      });
      
      HBox hBoutons = new HBox();
      hBoutons.setAlignment(Pos.CENTER);
      hBoutons.getChildren().addAll(btnSuppr, btnModifier);

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
      String date = simpleDateFormat.format(m.getDate()); 
      
      String nbPers = m.getNbMaxPersonnes()+ " personnes au maximum";
      
      fiche.getChildren().addAll(new Label(m.getNom()), new Label(m.getType().toString()), new Label(date), new Label(m.getLieu()), new Label(nbPers), new Label(m.getDescriptif()), btnParticipants, hBoutons);
      fiche.getStyleClass().add("font20");
      fiche.setAlignment(Pos.CENTER);
    });

    return elem;
  }

}
