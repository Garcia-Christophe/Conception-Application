package front;

import gestion.CodeErreur;
import gestion.evenements.Evenement;
import gestion.membres.Membre;
import gestion.participation.Participation;
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
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * La classe CreerMembreView est l'une des pages de l'application, elle g�re l'ajout et la
 * modification des membres.
 * 
 * @author L�o Couedor
 * @version 1.00
 */
public class ParticipationsView {

  /**
   * Variable contenant l'ancien pseudo du membre, utilis�e dans le cas de la modification d'un
   * membre pour pouvoir acc�der au membre et faire les modifications.
   */
  String oldPseudo;

  /**
   * Constructeur de la page CreerMembresView, avec la cr�ation et le placement de tous les
   * �l�ments.
   * 
   * @param m un nombre variable de Membres. Si aucun membre n'est pass� en param�tre, il s'agit
   *        d'une cr�ation, sinon d'une modification. Lors du passage en param�tre de plusieurs
   *        membres, seul le premier est utilis� pour la modification.
   */
  public ParticipationsView(Evenement e) {

    // setup de la nouvelle fen�tre
    
    ListView<String> list = new ListView<String>();
   

    Scene secondScene =
        new Scene(list, App.getStage().getWidth() / 2, App.getStage().getHeight() / 2);
    // New window (Stage)
    Stage newWindow = new Stage();

    newWindow.setTitle("Participants � l'�v�nement '"+e.getNom()+"'");

    newWindow.setScene(secondScene);
    
    newWindow.setMinHeight(newWindow.getHeight()/1.5);
    newWindow.setMinWidth(newWindow.getWidth()/1.5);

    newWindow.show();
    if(App.getGestion().getListeMembresParticipation(e.getId()).size()>0) {
      System.out.println("Y a des membres");
      for (Participation p : App.getGestion().getListeMembresParticipation(e.getId())) {
        
        String s = p.getMembre().getPseudo()+" | "+p.getMembre().getPrenom()+" | "+p.getMembre().getNom();
        
        list.getItems().add(s);
      }
    }

  }

}
