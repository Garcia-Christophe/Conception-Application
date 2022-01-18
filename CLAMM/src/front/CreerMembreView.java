package front;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import gestion.CodeErreur;
import gestionMembres.Membre;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreerMembreView {

  public CreerMembreView(Membre... m) {

    // setup de la nouvelle fenêtre
    GridPane grid = new GridPane();

    Scene secondScene =
        new Scene(grid, App.getStage().getWidth() / 2, App.getStage().getHeight() / 2);
    // New window (Stage)
    Stage newWindow = new Stage();
    if (m.length == 0) {
      newWindow.setTitle("Ajouter un membre");
    } else {
      newWindow.setTitle("Modifier le membre " + m[0].getPseudo());
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
    grid.getRowConstraints().addAll(row20, row, row10, row10);

    // les éléments
    HBox header = new HBox();
    header.setStyle("-fx-background-color: #85B9DE;");
    Label labelHead;
    if (m.length == 0) {
      labelHead = new Label("AJOUTER UN MEMBRE");
    } else {
      labelHead = new Label("MODIFIER LE MEMBRE " + m[0].getPseudo());
    }
    labelHead.getStyleClass().add("headLabel");
    header.getChildren().add(labelHead);
    header.setAlignment(Pos.CENTER);

    // éléments de gauche
    VBox vGauche = new VBox();
    vGauche.setPadding(new Insets(5));
    Label lPseudo = new Label("Pseudo");
    TextField textPseudo = new TextField();
    Label lNom = new Label("Nom");
    TextField textNom = new TextField();
    Label lDate = new Label("Date de naissance");
    DatePicker textDate = new DatePicker();
    Label lMdp = new Label("Mot de passe");
    TextField textMdp = new TextField();
    vGauche.setSpacing(10);
    vGauche.getChildren().addAll(lPseudo, textPseudo, lNom, textNom, lDate, textDate, lMdp,
        textMdp);

    // éléments de droite
    VBox vDroite = new VBox();
    vDroite.setPadding(new Insets(5));
    Label lAdresse = new Label("Adresse Mail");
    TextField textAdresse = new TextField();
    Label lPrenom = new Label("Prénom");
    TextField textPrenom = new TextField();
    Label lVille = new Label("Ville");
    TextField textVille = new TextField();
    Label lConfirm = new Label("Confirmation de mot de passe");
    TextField textConfirm = new TextField();
    vDroite.setSpacing(10);
    vDroite.getChildren().addAll(lAdresse, textAdresse, lPrenom, textPrenom, lVille, textVille,
        lConfirm, textConfirm);

    if (m.length != 0) {
      textPseudo.setText(m[0].getPseudo());
      textNom.setText(m[0].getNom());
      textPrenom.setText(m[0].getPrenom());
      LocalDate d = m[0].getDateNaissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      textDate.setValue(d);
      textAdresse.setText(m[0].getMail());
      textVille.setText(m[0].getVille());
      textMdp.setText(m[0].getMotDePasse());
      textConfirm.setText(m[0].getMotDePasse());
    }

    // Footer
    Button BtnAjouter;
    if(m.length==0) {
      BtnAjouter = new Button("Ajouter");
    }else {
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
    grid.add(PaneButtons, 1, 3, 1, 1);
    grid.add(hErreurs, 0, 2, 2, 1);

    if(m.length==0) {
      BtnAjouter.setOnAction(e -> {
        if (textMdp.getText().equals(textConfirm.getText())) {
          if (textDate.getValue() != null) {
            Date date =
                Date.from(textDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            if ((App.getGestion().ajouterMembre(textPseudo.getText(), textNom.getText(),
                textPrenom.getText(), textVille.getText(), date, textVille.getText(),
                textAdresse.getText(), textMdp.getText())) != null) {

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
              App.setScene(new MembresView());
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
          Label lTxtErrMdp = new Label("Erreur confirmation mot de passe |");
          lTxtErrMdp.setStyle("-fx-text-fill : red");
          hErreurs.getChildren().add(lTxtErrMdp);
        }
      });
    }else {
      BtnAjouter.setOnAction(e->{
        if(textMdp.getText().equals(textConfirm.getText())) {
          App.getGestion().getMembre(m[0].getPseudo()).setPseudo(textPseudo.getText());
          App.getGestion().getMembre(m[0].getPseudo()).setNom(textNom.getText());
          App.getGestion().getMembre(m[0].getPseudo()).setPrenom(textPrenom.getText());
          App.getGestion().getMembre(m[0].getPseudo()).setMotDePasse(textMdp.getText());
          App.getGestion().getMembre(m[0].getPseudo()).setVille(textVille.getText());
          App.getGestion().getMembre(m[0].getPseudo()).setMail(textAdresse.getText());
          Date date = Date.from(textDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
          App.getGestion().getMembre(m[0].getPseudo()).setDateNaissance(date); //TODO la MAJ ne se fait pas sur la date, pourquoi ?
          
          App.setScene(new MembresView());
          newWindow.close();
        }
      });
    }

    BtnAnnuler.setOnAction(e -> {
      newWindow.close();
    });
  }

}
