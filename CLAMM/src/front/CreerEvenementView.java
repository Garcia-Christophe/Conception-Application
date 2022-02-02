package front;

import gestion.CodeErreur;
import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * La classe CreerEvenementView est l'une des pages de l'application, elle gère l'ajout et la
 * modification des évènements.
 * 
 * @author Léo Couedor
 * @version 1.00
 */
public class CreerEvenementView {

  /**
   * Constructeur de la page CreerEvenementView, avec la création et le placement de tous les
   * éléments.
   * 
   * @param m un nombre variable d'évènements. Si aucun évènement n'est passé en paramètre, il
   *        s'agit d'une création, sinon d'une modification. Lors du passage en paramètre de
   *        plusieurs évènements, seul le premier est utilisé pour la modification
   */
  public CreerEvenementView(Evenement... m) {

    // setup de la nouvelle fenêtre
    GridPane grid = new GridPane();

    Scene secondScene =
        new Scene(grid, App.getStage().getWidth() / 2, App.getStage().getHeight() / 2);
    // New window (Stage)
    Stage newWindow = new Stage();
    if (m.length == 0) {
      newWindow.setTitle("Ajouter un évènement");
    } else {
      newWindow.setTitle("Modifier l'évènement " + m[0].getNom());
    }
    newWindow.setScene(secondScene);

    newWindow.show();
    
    newWindow.setMinHeight(newWindow.getHeight()/1.5);
    newWindow.setMinWidth(newWindow.getWidth()/1.5);

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
    grid.getRowConstraints().addAll(row, row, row10, row10);

    // les éléments

    // éléments de gauche
    VBox vGauche = new VBox();
    vGauche.setPadding(new Insets(5));
    Label lNom = new Label("Nom de l'évènement");
    TextField textNom = new TextField();
    Label lAdresse = new Label("Adresse");
    TextField textAdresse = new TextField();
    Label lNombre = new Label("Nombre maximum de personnes");
    TextField textNombre = new TextField();
    textNombre.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        if (!newValue.matches("\\d*")) {
          textNombre.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });
    vGauche.setSpacing(10);
    vGauche.getChildren().addAll(lNom, textNom, lAdresse, textAdresse, lNombre, textNombre);

    // éléments de droite
    VBox vDroite = new VBox();
    vDroite.setPadding(new Insets(5));
    Label lDate = new Label("Date");
    DatePicker textDate = new DatePicker();
    Label lType = new Label("Type d'évènement");
    ComboBox<TypeEvenement> textType = new ComboBox<TypeEvenement>();
    textType.setItems(FXCollections.observableArrayList(TypeEvenement.AG, TypeEvenement.ANIMATION,
        TypeEvenement.CHANTIER, TypeEvenement.REPAS));
    textType.setValue(TypeEvenement.AG);
    vDroite.setSpacing(10);
    vDroite.getChildren().addAll(lDate, textDate, lType, textType);

    VBox vDescription = new VBox();
    vDescription.setPadding(new Insets(5));
    Label lDescription = new Label("Description");
    TextArea textDescription = new TextArea();
    vDescription.getChildren().addAll(lDescription, textDescription);


    if (m.length != 0) {
      textNom.setText(m[0].getNom());

      java.util.Date utilDate = new java.util.Date(m[0].getDate().getTime());

      LocalDate d = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

      textDate.setValue(d);
      textAdresse.setText(m[0].getLieu());
      textType.setValue(m[0].getType());
      textDescription.setText(m[0].getDescriptif());
      textNombre.setText(Integer.toString(m[0].getNbMaxPersonnes()));
    }


    // Footer
    Button BtnAjouter;
    if (m.length == 0) {
      BtnAjouter = new Button("Ajouter");
    } else {
      BtnAjouter = new Button("Modifier");
    }
    Button BtnAnnuler = new Button("Annuler");

    BtnAjouter.setPrefHeight(Integer.MAX_VALUE);
    BtnAjouter.setPrefWidth(Integer.MAX_VALUE);
    BtnAnnuler.setPrefHeight(Integer.MAX_VALUE);
    BtnAnnuler.setPrefWidth(Integer.MAX_VALUE);

    HBox hErreurs = new HBox();
    hErreurs.setAlignment(Pos.CENTER_LEFT);
    hErreurs.setPadding(new Insets(5));
    Label lErreur = new Label("Erreur lors de la création : | ");
    lErreur.setStyle("-fx-text-fill : red; -fx-font-weight: bold");

    HBox PaneButtons = new HBox();
    PaneButtons.getChildren().addAll(BtnAnnuler, BtnAjouter);
    PaneButtons.setAlignment(Pos.CENTER);

    grid.add(vGauche, 0, 0);
    grid.add(vDroite, 1, 0);
    grid.add(PaneButtons, 1, 3, 1, 1);
    grid.add(hErreurs, 0, 2, 2, 1);
    grid.add(vDescription, 0, 1, 2, 1);


    BtnAjouter.setOnAction(e -> {

      int nbPers = 0;
      Date date = null;

      if (textNombre.getText() != "") { // si pas de valeur saisie, mise à 0 pour le parsing en
                                        // int
        nbPers = Integer.parseInt(textNombre.getText());
      }
      if (textDate.getValue() != null) { // si une date, parse en type date, sinon null par défaut
        date = Date.from(textDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
      }

      if(m.length==0) {
        App.getGestion().ajouterEvenement(-1, textNom.getText(), textDescription.getText(), "rien",
            date, textAdresse.getText(), nbPers, textType.getValue());
      }else {
        App.getGestion().modifierEvenement(m[0].getId(), textNom.getText(),textDescription.getText(),
            "rien", date, textAdresse.getText(),nbPers,textType.getValue());
      }

      Boolean error = false;

      ArrayList<String> erreurs = new ArrayList<String>();

      for (CodeErreur err : App.getGestion().getCodesErreurs()) {
        erreurs.add(err.toString());
        if (err.toString() != "NO_ERROR") {
          error = true;
        }
      }

      if (error) {
        hErreurs.getChildren().clear();
        if (erreurs.get(1) != "NO_ERROR") {
          textNom.setStyle("-fx-border-color: red");
          String s = erreurs.get(1).toString();
          String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
          s = newS.replace("_", " ");
          Label l = new Label(s + " | ");
          l.setStyle("-fx-text-fill : red");
          hErreurs.getChildren().add(l);
        } else {
          textNom.setStyle("-fx-border-color: black");
        }
        if (erreurs.get(4) != "NO_ERROR") {
          textDate.setStyle("-fx-border-color: red");
          String s = erreurs.get(4).toString();
          String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
          s = newS.replace("_", " ");
          Label l = new Label(s + " | ");
          l.setStyle("-fx-text-fill : red");
          hErreurs.getChildren().add(l);
        } else {
          textDate.setStyle("-fx-border-color: black");
        }
        if (erreurs.get(5) != "NO_ERROR") {
          textAdresse.setStyle("-fx-border-color: red");
          String s = erreurs.get(5).toString();
          String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
          s = newS.replace("_", " ");
          Label l = new Label(s + " | ");
          l.setStyle("-fx-text-fill : red");
          hErreurs.getChildren().add(l);
        } else {
          textAdresse.setStyle("-fx-border-color: black");
        }
        if (erreurs.get(6) != "NO_ERROR") {
          textNombre.setStyle("-fx-border-color: red");
          String s = erreurs.get(6).toString();
          String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
          s = newS.replace("_", " ");
          Label l = new Label(s + " | ");
          l.setStyle("-fx-text-fill : red");
          hErreurs.getChildren().add(l);
        } else {
          textNombre.setStyle("-fx-border-color: black");
        }
      } else {
        App.setScene(new EvenementsView());
        newWindow.close();
      }

    });

    BtnAnnuler.setOnAction(e ->

    {
      newWindow.close();
    });

  }

}
