package front;

import gestion.evenements.Evenement;
import gestion.participations.Participation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
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
  String result;

  /**
   * Constructeur de la page CreerMembresView, avec la création et le placement de tous les
   * éléments.
   * 
   * @param m un nombre variable de Membres. Si aucun membre n'est passé en paramètre, il s'agit
   *        d'une création, sinon d'une modification. Lors du passage en paramètre de plusieurs
   *        membres, seul le premier est utilisé pour la modification.
   */
  public ParticipationsView(Evenement e) {
    
    GridPane grid = new GridPane();
    
    Scene secondScene =
        new Scene(grid, App.getStage().getWidth() / 2, App.getStage().getHeight() / 2);
    // New window (Stage)
    Stage newWindow = new Stage();

    newWindow.setTitle("Participants à l'évènement '"+e.getNom()+"'");

    newWindow.setScene(secondScene);
    
    newWindow.setMinHeight(newWindow.getHeight()/1.5);
    newWindow.setMinWidth(newWindow.getWidth()/1.5);

    newWindow.show();

    // setup de la nouvelle fenêtre
    
    ListView<String> list = new ListView<String>();
    
    ColumnConstraints col = new ColumnConstraints();
    col.setHgrow(Priority.ALWAYS);
    grid.getColumnConstraints().add(col);

    RowConstraints row10 = new RowConstraints();
    row10.setPercentHeight(10);
    RowConstraints row = new RowConstraints();
    row.setVgrow(Priority.ALWAYS);
    grid.getRowConstraints().addAll(row10, row);
    
    HBox btnAjoutParticipation = new HBox();
    btnAjoutParticipation.getStyleClass().add("hover");
    btnAjoutParticipation.setCursor(Cursor.HAND);
    Label l = new Label("Ajouter une participation");
    btnAjoutParticipation.setAlignment(Pos.CENTER);
    btnAjoutParticipation.getChildren().add(l);
    
    btnAjoutParticipation.setOnMouseClicked(r->{
      new AjoutParticipationView(e); 
      newWindow.close();
    });
    
    grid.add(btnAjoutParticipation, 0, 0);
    grid.add(list, 0, 1);
   
    if(App.getGestion().getListeMembresParticipation(e.getId()).size()>0) {
      for (Participation p : App.getGestion().getListeMembresParticipation(e.getId())) {
        if(p.getMembre() != null) {
          String s = p.getMembre().getPseudo()+" | "+p.getMembre().getPrenom()+" | "+p.getMembre().getNom();
          list.getItems().add(s);
          list.setOnMouseClicked(r->{
            if(list.getSelectionModel().getSelectedItem() != null) {
              result = list.getSelectionModel().getSelectedItem();
              result = result.substring(0, result.indexOf("|")-1);
              new AjoutParticipationView(e,App.getGestion().getMembre(result));
              newWindow.close();
            }
          });
        }
      }
    }

  }

}
