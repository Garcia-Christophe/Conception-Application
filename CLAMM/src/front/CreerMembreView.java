package front;

import gestion.CodeErreur;
import gestion.membres.Membre;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * La classe CreerMembreView est l'une des pages de l'application, elle gère l'ajout et la
 * modification des membres.
 * 
 * @author Léo Couedor
 * @version 1.00
 */
public class CreerMembreView {

  /**
   * Variable contenant l'ancien pseudo du membre, utilisée dans le cas de la modification d'un
   * membre pour pouvoir accéder au membre et faire les modifications.
   */
  String oldPseudo;

  /**
   * Constructeur de la page CreerMembresView, avec la création et le placement de tous les
   * éléments.
   * 
   * @param m un nombre variable de Membres. Si aucun membre n'est passé en paramètre, il s'agit
   *        d'une création, sinon d'une modification. Lors du passage en paramètre de plusieurs
   *        membres, seul le premier est utilisé pour la modification.
   */
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
    grid.getRowConstraints().addAll(row, row10, row10);

    // les éléments
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
    PasswordField textMdp = new PasswordField();
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
    PasswordField textConfirm = new PasswordField();
    vDroite.setSpacing(10);
    vDroite.getChildren().addAll(lAdresse, textAdresse, lPrenom, textPrenom, lVille, textVille,
        lConfirm, textConfirm);

    if (m.length != 0) {
      textPseudo.setText(m[0].getPseudo());
      textPseudo.setStyle("-fx-background-color : #C0C0C0");
      textPseudo.setEditable(false);
      textNom.setText(m[0].getNom());
      textPrenom.setText(m[0].getPrenom());

      java.util.Date utilDate = new java.util.Date(m[0].getDateNaissance().getTime());

      LocalDate d = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

      textDate.setValue(d);
      textAdresse.setText(m[0].getMail());
      textVille.setText(m[0].getVille());
      textMdp.setText(m[0].getMotDePasse());
      textConfirm.setText(m[0].getMotDePasse());
      oldPseudo = textPseudo.getText();
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
    grid.add(PaneButtons, 1, 2, 1, 1);
    grid.add(hErreurs, 0, 1, 2, 1);

    BtnAjouter.setOnAction(e -> {
      if (textMdp.getText().equals(textConfirm.getText())) {
        textConfirm.setStyle("-fx-border-color: black");

        Date date = null;
        if (textDate.getValue() != null) { // si une date, parse en type date, sinon null par
                                           // défaut
          date = Date.from(textDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        if(m.length == 0) {
          App.getGestion().ajouterMembre(textPseudo.getText(), textNom.getText(),
              textPrenom.getText(), textVille.getText(), date, textVille.getText(),
              textAdresse.getText(), textMdp.getText());
        }else {
          App.getGestion().modifierMembre(textPseudo.getText(), textNom.getText(),textPrenom.getText(),
              textVille.getText(), date, textVille.getText(),textAdresse.getText(), textMdp.getText());
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
          if (erreurs.get(0) != "NO_ERROR") {
            textPseudo.setStyle("-fx-border-color: red");
            String s = erreurs.get(0).toString();
            String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            s = newS.replace("_", " ");
            Label l = new Label(s + " | ");
            l.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(l);
          } else {
            textPseudo.setStyle("-fx-border-color: black");
          }
          if (erreurs.get(1) != "NO_ERROR") {
            String s = erreurs.get(1).toString();
            String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            s = newS.replace("_", " ");
            Label l = new Label(s + " | ");
            l.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(l);
            textNom.setStyle("-fx-border-color: red");
          } else {
            textNom.setStyle("-fx-border-color: black");
          }
          if (erreurs.get(2) != "NO_ERROR") {
            String s = erreurs.get(2).toString();
            String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            s = newS.replace("_", " ");
            Label l = new Label(s + " | ");
            l.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(l);
            textPrenom.setStyle("-fx-border-color: red");
          } else {
            textPrenom.setStyle("-fx-border-color: black");
          }
          if (erreurs.get(3) != "NO_ERROR") {
            String s = erreurs.get(3).toString();
            String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            s = newS.replace("_", " ");
            Label l = new Label(s + " | ");
            l.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(l);
            textVille.setStyle("-fx-border-color: red");
          } else {
            textVille.setStyle("-fx-border-color: black");
          }
          if (erreurs.get(4) != "NO_ERROR") {
            String s = erreurs.get(4).toString();
            String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            s = newS.replace("_", " ");
            Label l = new Label(s + " | ");
            l.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(l);
            textDate.setStyle("-fx-border-color: red");
          } else {
            textDate.setStyle("-fx-border-color: black");
          }
          if (erreurs.get(6) != "NO_ERROR") {
            String s = erreurs.get(6).toString();
            String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            s = newS.replace("_", " ");
            Label l = new Label(s + " | ");
            l.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(l);
            textAdresse.setStyle("-fx-border-color: red");
          } else {
            textAdresse.setStyle("-fx-border-color: black");
          }
          if (erreurs.get(7) != "NO_ERROR") {
            String s = erreurs.get(7).toString();
            String newS = s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
            s = newS.replace("_", " ");
            s=s+", Requis : au moins 6 caractères, 1 caractère spécial, 1 chiffre, 1 majuscule, 1 minuscule";
            Label l = new Label(s + " | ");
            l.setStyle("-fx-text-fill : red");
            hErreurs.getChildren().add(l);
            textMdp.setStyle("-fx-border-color: red");
            textConfirm.setStyle("-fx-border-color: red");
          } else {
            textMdp.setStyle("-fx-border-color: black");
            textConfirm.setStyle("-fx-border-color: black");
          }
        } else {
          App.setScene(new MembresView());
          newWindow.close();
        }

      } else {
        textConfirm.setStyle("-fx-border-color: red");
        hErreurs.getChildren().clear();
        Label lTxtErrMdp = new Label("Erreur confirmation mot de passe |");
        lTxtErrMdp.setStyle("-fx-text-fill : red");
        hErreurs.getChildren().add(lTxtErrMdp);
      }
    });


    BtnAnnuler.setOnAction(e -> {
      newWindow.close();
    });
  }

}
