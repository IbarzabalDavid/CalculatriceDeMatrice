package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    public void nouvelleMatrice(){
        Spinner spinnerL = new Spinner(0,4,3);
        Spinner spinnerC = new Spinner(0,4,3);
        Label textL = new Label("Nombre de lignes de votre matrice :     ");
        Label textC = new Label("Nombre de colonnes de votre matrice :");
        HBox hb1 = new HBox(textL,spinnerL);
        hb1.setSpacing(5);
        HBox hb2 = new HBox(textC,spinnerC);
        hb2.setSpacing(5);
        VBox vb1 = new VBox(hb1,hb2);
        vb1.setSpacing(5);


        Dialog dialog = new Dialog();
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        dialog.getDialogPane().setContent(vb1);
        Matrice matrice = new Matrice();
        dialog.showAndWait();


    }
}
