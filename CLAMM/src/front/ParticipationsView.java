package front;

import gestion.CodeErreur;
import gestion.evenements.Evenement;
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
public class ParticipationsView {

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
  public ParticipationsView(Evenement e) {
    System.out.println(App.getGestion().getListeMembresParticipation(e.getId()).get(0)); 

    // setup de la nouvelle fenêtre
    GridPane grid = new GridPane();

    Scene secondScene =
        new Scene(grid, App.getStage().getWidth() / 2, App.getStage().getHeight() / 2);
    // New window (Stage)
    Stage newWindow = new Stage();

    newWindow.setTitle("Participations à l'évènement");

    newWindow.setScene(secondScene);

    newWindow.show();

  }

}
