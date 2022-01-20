package front;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import gestion.CodeErreur;
import gestion.evenements.Evenement;
import gestion.evenements.TypeEvenement;
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
 * La classe CreerEvenementView est l'une des pages de l'application, celle qui gère l'ajout et la modification des évènements.
 *   
 * @author Léo Couedor
 * @version 1.00
 */
public class CreerEvenementView {

  /**
   * Constructeur de la page CreerEvenementView, avec la création et le placement de tous les éléments.
   * 
   * @param un nombre variable d'évènements. Si aucun évènement n'est passé en paramètre, il s'agit d'une création, sinon d'une modification. Lors du passage en paramètre de plusieurs évènements, seul le premier est utilisé pour la modification
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
    grid.getRowConstraints().addAll(row20, row, row, row10, row10);

    // les éléments
    HBox header = new HBox();
    header.setStyle("-fx-background-color: #85B9DE;");
    Label labelHead;
    if (m.length == 0) {
      labelHead = new Label("AJOUTER UN EVENEMENT");
    } else {
      labelHead = new Label("MODIFIER L'EVENEMENT " + m[0].getNom());
    }
    labelHead.getStyleClass().add("headLabel");
    header.getChildren().add(labelHead);
    header.setAlignment(Pos.CENTER);

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
      LocalDate d = m[0].getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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

    grid.add(header, 0, 0, 2, 1);
    grid.add(vGauche, 0, 1);
    grid.add(vDroite, 1, 1);
    grid.add(PaneButtons, 1, 4, 1, 1);
    grid.add(hErreurs, 0, 3, 2, 1);
    grid.add(vDescription, 0, 2, 2, 1);

    if (m.length == 0) {
      BtnAjouter.setOnAction(e -> {

        if (textNombre.getText() != "") {
          if (textDate.getValue() != null) {
            Date date =
                Date.from(textDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            if ((App.getGestion().ajouterEvenement(textNom.getText(), textDescription.getText(),
                "rien", date, textAdresse.getText(), Integer.parseInt(textNombre.getText()),
                textType.getValue())) != null) {

              hErreurs.getChildren().clear();
              hErreurs.getChildren().add(lErreur);
              for (CodeErreur err : App.getGestion().getCodesErreurs()) {
                String s = err.toString();
                String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
                s = newS.replace("_", " ");
                Label l = new Label(s + " | ");
                l.setStyle("-fx-text-fill : red");
                hErreurs.getChildren().add(l);
              }
            } else {
              App.setScene(new EvenementsView());
              newWindow.close();
            }
          } else {
            hErreurs.getChildren().clear();
            hErreurs.getChildren().add(lErreur);
            Label lTxtErrDate = new Label("Erreur saisie de la date |");
            lTxtErrDate.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(lTxtErrDate);
          }
        } else {
          hErreurs.getChildren().clear();
          hErreurs.getChildren().add(lErreur);
          Label lTxtErrDate = new Label("Nombre maximal de personnes manquant |");
          lTxtErrDate.setStyle("-fx-text-fill : red");
          hErreurs.getChildren().add(lTxtErrDate);
        }

      });

    } else {
      BtnAjouter.setOnAction(e -> {
        if (textNombre.getText() != "") {
          Date date =
              Date.from(textDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
          App.getGestion().modifierEvenement(m[0].getId(), textNom.getText(),
              textDescription.getText(), "rien", date, textAdresse.getText(),
              Integer.parseInt(textNombre.getText()), textType.getValue());
          
          App.setScene(new EvenementsView());
          newWindow.close();
        } else {
          hErreurs.getChildren().clear();
          hErreurs.getChildren().add(lErreur);
          Label lTxtErrDate = new Label("Nombre maximal de personnes manquant |");
          lTxtErrDate.setStyle("-fx-text-fill : red");
          hErreurs.getChildren().add(lTxtErrDate);
        }
      });
    }

    BtnAnnuler.setOnAction(e -> {
      newWindow.close();
    });

  }

}
