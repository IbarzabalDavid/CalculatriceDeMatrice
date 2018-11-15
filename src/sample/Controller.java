package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {
    @FXML
    private Button but;
    public static Matrice matrice = new Matrice();
    public static ArrayList<Element> element = new ArrayList<>();
    public static Spinner spinnerL= new Spinner(1,5,3);
    public static Spinner spinnerC= new Spinner(1,5,3);
    public static Label labelL = new Label("Entrez le nombre de lignes de votre matrice     ");
    public static Label labelC = new Label("Entrez le nombre de colonnes de votre matrice");
    public static ArrayList<Matrice> tabMat=new ArrayList<>();
    @FXML
    public void nouvelleMatrice(){
        //dialog1 nb ligne et colones
        HBox hb = new HBox(labelL,spinnerL);
        hb.setSpacing(7);
        HBox hb1 = new HBox(labelC,spinnerC);
        hb1.setSpacing(7);
        VBox vb = new VBox(hb,hb1);
        vb.setSpacing(7);
        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(vb);
        dialog.getDialogPane().getButtonTypes().add( new ButtonType("OK",ButtonBar.ButtonData.OK_DONE));
        dialog.showAndWait();
        matrice.setTailleL((int)spinnerL.getValue());
        matrice.setTailleC((int)spinnerC.getValue());
        //fin dialog1
        ButtonType buttonDialog1 = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
        AtomicInteger varTemp=new AtomicInteger();
        varTemp.set(0);
        int var = matrice.getTailleL()*matrice.getTailleC();
        //dialog2 matrice button
        Button[] tabBut = new Button[var];
        for (int i=0;i<var;i++){
            tabBut[i]=new Button();
            tabBut[i].setText("["+i+"]");
            tabBut[i].setMaxSize(50,50);
            tabBut[i].setMinSize(50,50);
        }
        VBox vbTemp= new VBox();
        int constante=0;
        for (int i=0;i<matrice.getTailleL();i++){
            HBox hbTemp = new HBox();
            hbTemp.setSpacing(7);
            for (int j=0;j<matrice.tailleC;j++){
                hbTemp.getChildren().addAll(tabBut[constante]);
                constante++;
            }
            vbTemp.getChildren().addAll(hbTemp);
            vbTemp.setSpacing(7);
        }
        Dialog dialog1 = new Dialog();
        dialog1.getDialogPane().setContent(vbTemp);
        dialog1.show();
        //fin dialog2
        //dialoge3 entrez valur case
        Label question = new Label("Entrez la valeur ");
        TextField tf = new TextField();
        HBox labTf = new HBox(question,tf);
        labTf.setSpacing(7);
        //Disable i dont like
        /*
        int l=0;
        for (int i=0;i<var;i++){
            tabBut[i].setDisable(true);
            if (tabBut[l]==tabBut[i] && tabBut[l].getText().equals("["+l+"]") || l==0){
                tabBut[i].setDisable(false);
                l++;
            }
        }*/
        for (int i=0;i<var;i++){
            final int temp=i;
            tabBut[i].setOnAction((event)->{
                Element elem= new Element();
               Dialog dialog2 = new Dialog();
               dialog2.getDialogPane().setContent(labTf);
               dialog2.getDialogPane().getButtonTypes().add( new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
               dialog2.showAndWait();
               elem.setValeur(Integer.parseInt(tf.getText()));
               element.add(elem);
               matrice.setElement(element);
               tabBut[temp].setText(tf.getText());
               tf.setText("1");
               varTemp.set(varTemp.get()+1);
               if (varTemp.get()==var){
                  dialog1.getDialogPane().getButtonTypes().add(buttonDialog1);
                  dialog1.setWidth(500);
                  dialog1.setHeight(500);

               }
            });
        }

    }
    @FXML
    public void supprimerMatrice(){
        if (tabMat.size()==0){
            Label label = new Label("Vous n'avez pas de matrice");
            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(label);
            dialog.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
            dialog.showAndWait();
        }
        else if (tabMat.size()==1){
            tabMat.get(0).getElement().remove(0);
        }
        else {
            int chiffre=65;
            Dialog dialog1 = new Dialog();
            CheckBox[] tabCheck = new CheckBox[tabMat.size()];
            Label label1 = new Label("Quelle matrice voulez-vous supprimer?");
            dialog1.getDialogPane().setContent(label1);
            for (int i=0;i<tabMat.size();i++){
                CheckBox cb = new CheckBox("Matrice: "+ (char)chiffre);
                tabCheck[i]=cb;
                chiffre++;
            }
            VBox vb = new VBox();
            for (int i=0;i<tabCheck.length;i++){
                vb.getChildren().add(tabCheck[i]);
            }
            dialog1.getDialogPane().setContent(vb);
            dialog1.showAndWait();
        }
    }
}
