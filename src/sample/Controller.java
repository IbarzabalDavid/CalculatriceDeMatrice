package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class Controller {
    @FXML
    private Button but;
    @FXML
    private Spinner spinnerL;
    @FXML
    private Spinner spinnerC;
    @FXML
    private VBox vbScene;
    @FXML
    private Label textL;
    @FXML
    private Label textC;
    @FXML
    private HBox hb1;
    @FXML
    private HBox hb2;
    @FXML
    private HBox hbRep;
    @FXML
    private Button ok;
    @FXML
    private Label nombre;
    @FXML
    private Button ok1;
    public static Matrice matrice = new Matrice();
    @FXML
    private VBox vbTemp;
    @FXML
    private TextField text;
    @FXML
    public void nouvelleMatrice(){
        but.setVisible(false);
        SpinnerValueFactory<Integer> valeurs = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4,3);
        SpinnerValueFactory<Integer> valeurs1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,4,3);
        spinnerL.setValueFactory(valeurs);
        spinnerC.setValueFactory(valeurs1);
        vbScene.setVisible(true);

    }
    @FXML
    public void valeurMat(){
        hb1.setVisible(false);
        hb2.setVisible(false);
        ok.setVisible(false);
        hbRep.setVisible(true);
        int var = matrice.getTailleL()*matrice.getTailleC();
        Label [] tabLab = new Label[var];
        nombre.setText("Entrez un nombre ("+0+")");
        for (int i=0;i<var;i++){
            Label label = new Label("["+i+"]");
            tabLab[i]=label;
        }

        ArrayList<Element> element = new ArrayList<>();


        int constante=0;
        boolean ok=false;
        while (!ok){
            for (int i=0;i<matrice.getTailleL();i++){
                HBox hbLab = new HBox();
                hbLab.setSpacing(7);
                for (int j = 0; j<matrice.getTailleC();j++){
                    hbLab.getChildren().addAll(tabLab[constante]);
                    constante++;
                }
                vbTemp.setSpacing(7);
                vbTemp.getChildren().addAll(hbLab);
                if (i==matrice.getTailleL()-1){
                    vbTemp.getChildren().addAll(hbRep);

                }
            }
            if (constante==var){
                ok=true;
                constante=0;


            }
        }
        for (int i=0;i<var;i++){
            Element elementTemp = new Element();
            elementTemp.setValeur(Integer.parseInt(text.getText()));
            tabLab[i].setText(text.getText());
            element.add(elementTemp);
        }
        matrice.setElement(element);
        matrice.setTailleL((int)spinnerL.getValue());
        matrice.setTailleC((int)spinnerC.getValue());
    }
    @FXML
    public void addElement(){


    }
}
