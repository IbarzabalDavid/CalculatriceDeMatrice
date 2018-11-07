package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    public void nouvelleMatrice(){
        Spinner spinnerL = new Spinner(1,4,3);
        Spinner spinnerC = new Spinner(1,4,3);
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

        matrice.setTailleL((int)spinnerL.getValue());
        matrice.setTailleC((int)spinnerC.getValue());

        Label chiffre = new Label("Entrez un nombre: ");
        TextField text = new TextField();
        HBox hb = new HBox(chiffre,text);


        Dialog dialog1 = new Dialog();
        dialog1.getDialogPane().getButtonTypes().add(new ButtonType("OK",ButtonBar.ButtonData.OK_DONE));
        dialog1.getDialogPane().setContent(hb);

        int var = matrice.getTailleL()*matrice.getTailleC();
        Element[] element = new Element[var];
       /* Label [] tabLab = new Label[var];
        for (int i=0;i<var;i++){
            Label label = new Label("["+i+"]");
            tabLab[i]=label;
        }

        int constante=0;
        boolean ok=false;
        while (!ok){
            for (int i=0;i<matrice.getTailleL();i++){
                HBox hbLab = new HBox();
                for (int j = 0; j<matrice.getTailleC();j++){
                    hbLab.getChildren().addAll(tabLab[constante]);
                    constante++;
                }
                VBox vbox = new VBox();
                vbox.getChildren().addAll(hbLab);
                if (i==matrice.getTailleL()-1){
                    vbox.getChildren().addAll(hb);
                    dialog1.getDialogPane().setContent(vbox);
                }
            }
            if (constante==var){
                ok=true;
                constante=0;


            }
        }*/

        for (int i=0;i<var;i++){
            text.setText("");
            chiffre.setText("Entrez un nombre ("+i+")");
            dialog1.showAndWait();
            Element elementTemp = new Element();
            elementTemp.setValeur(Integer.parseInt(text.getText()));
            //tabLab[i].setText(text.getText());
            element[i]=elementTemp;
        }

    }
}
