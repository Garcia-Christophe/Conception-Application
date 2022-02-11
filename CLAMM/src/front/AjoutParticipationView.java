package front;

import java.sql.SQLException;
import gestion.evenements.Evenement;
import gestion.membres.Membre;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class AjoutParticipationView {

  @SuppressWarnings("unchecked")
  public AjoutParticipationView(Evenement e, Membre... m) {
    // setup de la nouvelle fenêtre
    GridPane grid = new GridPane();
    
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(50);
    grid.getColumnConstraints().addAll(col, col);

    RowConstraints row10 = new RowConstraints();
    row10.setPercentHeight(10);
    RowConstraints row = new RowConstraints();
    row.setVgrow(Priority.ALWAYS);
    grid.getRowConstraints().addAll(row, row, row10, row10);

    Scene secondScene = new Scene(grid, App.getStage().getWidth() / 2, App.getStage().getHeight() / 2);
    // New window (Stage)
    Stage newWindow = new Stage();
    if (m.length == 0) {
      newWindow.setTitle("Ajouter une participation");
    } else {
      newWindow.setTitle("Modifier la participation de " + m[0].getPseudo());
    }
    newWindow.setScene(secondScene);

    newWindow.show();
    
    newWindow.setMinHeight(newWindow.getHeight()/1.5);
    newWindow.setMinWidth(newWindow.getWidth()/1.5);
    
    VBox vMembre = new VBox();
    vMembre.setPadding(new Insets(5));
    ComboBox<String> comboBox = new ComboBox<String>();
    if(m.length == 0) {
      Label lMembre = new Label("Membre");
      vMembre.getChildren().addAll(lMembre, comboBox);
      vMembre.setAlignment(Pos.CENTER_LEFT);
      
      for(Membre a : App.getGestion().getListeMembres()) {
        comboBox.getItems().add(a.getPseudo());
      }
    }else {
      Label lMembre = new Label(m[0].getPseudo());
      vMembre.getChildren().addAll(lMembre);
      vMembre.setAlignment(Pos.CENTER);
    }
    
    Label lNombre = new Label("Nombre de participants");
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
    VBox vNombre= new VBox();
    vNombre.getChildren().addAll(lNombre, textNombre);
    vNombre.setAlignment(Pos.CENTER_LEFT);
    vNombre.setPadding(new Insets(5));
    
    Label lDescription = new Label("Description");
    TextArea textDescription = new TextArea();
    VBox vDesc= new VBox();
    vDesc.getChildren().addAll(lDescription, textDescription);
    vDesc.setAlignment(Pos.CENTER_LEFT);
    vDesc.setPadding(new Insets(5));
    
    HBox hButtons = new HBox();
    hButtons.setAlignment(Pos.CENTER);
    Button bAnnuler = new Button("Annuler");
    bAnnuler.setPrefHeight(Integer.MAX_VALUE);
    bAnnuler.setPrefWidth(Integer.MAX_VALUE);
    bAnnuler.setOnAction(r ->{
      new ParticipationsView(e);
      newWindow.close();
    });
    
    Button bAjouter = new Button();
    if(m.length != 0) {
      bAjouter.setText("Modifier");
    }else {
      bAjouter.setText("Ajouter");
    }
    bAjouter.setPrefHeight(Integer.MAX_VALUE);
    bAjouter.setPrefWidth(Integer.MAX_VALUE);
    if(m.length != 0) {
      bAjouter.setOnAction(r ->{
        System.out.println("Modification...");
        new ParticipationsView(e);
        newWindow.close();
      });
    }else {
      bAjouter.setOnAction(r ->{
        try {
          App.getGestion().ajouterParticipation(e, App.getGestion().getMembre(comboBox.getValue()), Integer.parseInt(textNombre.getText()), textDescription.getText());
        } catch (Exception e1) {
          e1.printStackTrace();
        }
        new ParticipationsView(e);
        newWindow.close();
      });
    }
    
    Button bSupprimer = new Button("Supprimer");
    bSupprimer.setPrefHeight(Integer.MAX_VALUE);
    bSupprimer.setPrefWidth(Integer.MAX_VALUE);
    bSupprimer.setOnAction(r ->{
      App.getGestion().supprimerParticipation(m[0], e);
      new ParticipationsView(e);
      newWindow.close();
    });
    
    if(m.length != 0) {
      hButtons.getChildren().addAll(bAnnuler, bSupprimer, bAjouter);
    }else {
      hButtons.getChildren().addAll(bAnnuler, bAjouter);
    }
    
    grid.add(vMembre, 0, 0);
    grid.add(vNombre, 1, 0);
    grid.add(vDesc, 0, 1, 2, 1);
    grid.add(hButtons, 1, 3);
  }
  
}
