package front;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import gestion.membres.Membre;
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
 * La classe MembresView est l'une des pages de l'application, 
 * elle g�re l'affichage des membres.
 * Elle est de type GridPane pour pouvoir l'int�grer au stage de 
 * l'application par l'interm�diaire du controlleur App.
 *   
 * @author L�o Couedor
 * @version 1.00
 */
public class MembresView extends GridPane {
  
  /**
   * Element de la partie de droite de l'application, qui permet 
   * l'affichage des informations compl�mentaires du membre s�lectionn�.
   */
  HBox rightPart = new HBox();

  /**
   * Constructeur de la page MembresView, avec la cr�ation et le placement de tous les �l�ments.
   */
  public MembresView() {

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
    
    //header    
    HBox paneMembre = new HBox();
    paneMembre.setStyle("-fx-background-color: #85B9DE;");
    paneMembre.getStyleClass().add("hover");
    Label labelMembres = new Label("MEMBRES");
    labelMembres.getStyleClass().add("headLabel");
    paneMembre.getChildren().add(labelMembres);
    paneMembre.setAlignment(Pos.CENTER);
    
    HBox paneEvenements = new HBox();
    paneEvenements.setStyle("-fx-background-color: #94CEF7;");
    paneEvenements.getStyleClass().add("hover");
    Label labelEvenements = new Label("EVENEMENTS");
    labelEvenements.getStyleClass().add("headLabel");
    paneEvenements.getChildren().add(labelEvenements);
    paneEvenements.setAlignment(Pos.CENTER);
    
    //Changement de sc�ne : aller � �v�nement
    paneEvenements.setOnMouseClicked(e->{
      App.setScene(new EvenementsView());
    });
    
    //Cr�er image
    Image img = new Image("img/ic_action_add_person.png");
    ImageView view = new ImageView(img);
    view.setFitHeight(50);
    view.setPreserveRatio(true);
    
    HBox btnAjoutMembre = new HBox();
    btnAjoutMembre.getChildren().add(view);
    btnAjoutMembre.setAlignment(Pos.CENTER);
    btnAjoutMembre.getStyleClass().add("hover");
    btnAjoutMembre.setOnMouseClicked(e->{
      new CreerMembreView(); //Appel � la nouvelle sc�ne
    });
    
    HBox btnCreerCSV = new HBox();
    Label l = new Label("G�n�rer fichiers CSV");
    l.setStyle("-fx-font-size: 30px");
    btnCreerCSV.getChildren().add(l);
    btnCreerCSV.setAlignment(Pos.CENTER);
    btnCreerCSV.getStyleClass().add("hover");
    btnCreerCSV.setOnMouseClicked(e->{
      App.getGestion().creerCSV();
    });
    
    btnCreerCSV.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            btnCreerCSV.getStyleClass().add("btnAjoutMembreHovered");
          }
        });

    btnCreerCSV.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            btnCreerCSV.getStyleClass().remove("btnAjoutMembreHovered");
          }
        });
    
    btnAjoutMembre.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            btnAjoutMembre.getStyleClass().add("btnAjoutMembreHovered");
          }
        });

    btnAjoutMembre.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            btnAjoutMembre.getStyleClass().remove("btnAjoutMembreHovered");
          }
        });

    //Partie de gauche, le scroll pane
    ScrollPane leftScroll = new ScrollPane();
    leftScroll.setStyle("-fx-background-color:transparent; -fx-border-style: hidden solid hidden hidden; -fx-border-width: 2.5; -fx-border-color: black;"); //La moiti� de la s�paration du milieu
    VBox vScroll = new VBox(); //Le contenu unique du scrollPane
    leftScroll.setContent(vScroll);
    for (Membre m : App.getGestion().getListeMembres()) { //Ajout de tous les membres de l'arrayList des membres
      vScroll.getChildren().add(ajMembre(m));
    }
    vScroll.prefWidthProperty().bind(this.widthProperty().divide(2).subtract(10)); //Taille de la VBox en fonction de la taille de la sc�ne, divis� par 2 pour avoir la moiti�, moins 10 pour enlever les marges
    
    //Partie de droite pour l'affichage des infos pour chaque membre au clic
    rightPart.setStyle("-fx-border-style: hidden hidden hidden solid; -fx-border-width: 2.5; -fx-border-color: black;");
    rightPart.setAlignment(Pos.CENTER);

    // Ajout des �l�ments au corps principal
    add(paneMembre,0,0,1,1);
    add(paneEvenements, 1, 0, 1, 1);
    add(btnAjoutMembre, 0, 1, 1, 1);
    add(btnCreerCSV, 1, 1, 1, 1);
    add(leftScroll, 0, 2, 1, 1);
    add(rightPart, 1, 2, 1, 1);
  }
  
  /**
   * M�thode pour cr�er un bloc HBox, � ajouter � la cr�ation d'un membre pour l'affichage. 
   * Cela cr�� �galement sa fiche d'informations compl�mentaire
   * ainsi que les boutons permettant de supprimer et modifier ce membre.
   * 
   * @param m un membre pour lequel cr�er son bloc dans la liste d'affichage 
   *     et sa fiche d'informations
   * @return un �l�ment de type HBox
   */
  public HBox ajMembre(Membre m) {
    HBox elem = new HBox();
    elem.getChildren().addAll(new Label(m.getPseudo()), new Label(m.getPrenom()), new Label(m.getNom())); //Le pseudo, nom et pr�nom comme texte
    elem.setSpacing(50);  
    //Les event pour ajouter ou retirer la classe elemHovered pour l'animation au survol d'un membre dans la liste
    elem.addEventHandler(MouseEvent.MOUSE_ENTERED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            elem.getStyleClass().add("elemHovered");
          }
        });

    elem.addEventHandler(MouseEvent.MOUSE_EXITED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            elem.getStyleClass().remove("elemHovered");
          }
        });
    
    elem.getStyleClass().addAll("hover","font20"); //L'ajout des classes pour changer le curseur au survol et la taille de font
    elem.setPadding(new Insets(5,100,5,100)); //Les paddings
    elem.setAlignment(Pos.CENTER); //Le placement au milieu
    
    elem.setOnMouseClicked(e->{
      rightPart.getChildren().clear(); //on supprime la fiche d�j� affich�e au cas ou il y en avait d�j� une
      VBox fiche = new VBox();
      fiche.prefWidthProperty().bind(this.widthProperty().divide(3).subtract(10));
      rightPart.getChildren().add(fiche);
      
      Image img = new Image("img/ic_action_cancel.png");
      ImageView view = new ImageView(img);
      view.setFitHeight(50);
      view.setPreserveRatio(true);
      
      Button btnSuppr = new Button();
      btnSuppr.setGraphic(view);
      btnSuppr.setOnMouseClicked(a->{
        App.getGestion().supprimerMembre(m.getPseudo()); //Suppression par le pseudo
        App.setScene(new MembresView()); //Recr�er une nouvelle sc�ne pour actualiser l'affichage
      });
      
      img = new Image("img/ic_action_edit.png");
      view = new ImageView(img);
      view.setFitHeight(50);
      view.setPreserveRatio(true);
      
      Button btnModifier = new Button();
      btnModifier.setGraphic(view);
      btnModifier.setOnMouseClicked(a->{
        new CreerMembreView(m);
      });
      
      HBox hBoutons = new HBox();
      hBoutons.setAlignment(Pos.CENTER);
      hBoutons.getChildren().addAll(btnSuppr, btnModifier);
      
      String nomPrenom = m.getPrenom()+ " "+ m.getNom();
      //LocalDate dateNaissance = m.getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      //String age = Period.between(dateNaissance, LocalDate.now()).getYears()+ " ans";
      fiche.getChildren().addAll(new Label(m.getPseudo()), new Label(nomPrenom),new Label(m.getMail()), hBoutons);
      fiche.getStyleClass().add("font20");
      fiche.setAlignment(Pos.CENTER);
    });
    
    return elem;
  }

}
