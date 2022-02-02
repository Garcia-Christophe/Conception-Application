package front;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * La classe ErreurConnexionView est l'une des pages de l'application, 
 * elle apparait lors d'une erreur au lancement de l'application en cas de non connexion à la base de données.
 * Elle est de type GridPane pour pouvoir l'intégrer au stage de 
 * l'application par l'intermédiaire du controlleur App.
 *   
 * @author Léo Couedor
 * @version 1.00
 */
public class ErreurConnexionView extends GridPane{

  /**
   * Constructeur de la page ErreurConnexionView, avec la création et le placement de tous les éléments.
   */
  public ErreurConnexionView() {
    HBox texte = new HBox();
    texte.setPrefWidth(Integer.MAX_VALUE);
    texte.setPrefHeight(Integer.MAX_VALUE);
    texte.setAlignment(Pos.CENTER);
    Label err = new Label("Erreur lors de la connexion à la base de données. Veuillez vérifier votre connexion au réseau et relancer l'application.");
    err.setPadding(new Insets(30,30,30,30));
    err.setWrapText(true);
    err.setStyle("-fx-font-size : 40; -fx-text-fill : red;");
    texte.getChildren().add(err);
    this.add(texte, 0, 0);
  }
}
