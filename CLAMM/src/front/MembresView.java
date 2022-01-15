package front;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class MembresView extends GridPane{
  public MembresView() {
    //création des colonnes
    ColumnConstraints col = new ColumnConstraints();
    col.setPercentWidth(50);
    getColumnConstraints().addAll(col,col);
    
    //création des lignes
    RowConstraints row20 = new RowConstraints();
    row20.setPercentHeight(20);
    RowConstraints row80 = new RowConstraints();
    row80.setPercentHeight(80);
    getRowConstraints().addAll(row20,row80);
    
    setGridLinesVisible(true);    
    
    Button BtnMembres = new Button("Membres");
    Button BtnEvenements = new Button("Evenements");
    //head.setStyle("-fx-background-color: #0097d8;");
    add(BtnMembres, 0, 0, 1, 1);
    add(BtnEvenements, 1, 0, 1, 1);
  }

}
