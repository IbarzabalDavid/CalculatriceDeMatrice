package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller {
    public static Matrice matrice = new Matrice();
    public static ArrayList<Element> element = new ArrayList<>();
    public static Spinner spinnerL= new Spinner(1,4,3);
    public static Spinner spinnerC= new Spinner(1,4,3);
    public static Label labelL = new Label("Entrez le nombre de lignes de votre matrice     ");
    public static Label labelC = new Label("Entrez le nombre de colonnes de votre matrice");
    public static ArrayList<Matrice> tabMat = new ArrayList<>();
    public static ArrayList<CheckBox> tabCheck = new ArrayList<>();
    public static Button but = new Button("OK");
    //https://stackoverflow.com/questions/30543460/multiple-but-limited-checkboxes-in-fxml-javafx
    public static ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();
    public static ObservableSet<CheckBox> unselectedCheckBoxes = FXCollections.observableSet();
    public static int maxNumSelected =  2;
    public static IntegerBinding numCheckBoxesSelected = Bindings.size(selectedCheckBoxes);
    public static Dialog dialogCheckBox = new Dialog();
    @FXML
    public void nouvelleMatrice(){
        ButtonType buttonDialog1 = new ButtonType("ok", ButtonBar.ButtonData.OK_DONE);
        AtomicInteger varTemp=new AtomicInteger();
        varTemp.set(0);
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
        int var = matrice.getTailleL()*matrice.getTailleC();
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

        Label question = new Label("Entrez la valeur ");
        TextField tf = new TextField("1");
        HBox labTf = new HBox(question,tf);
        labTf.setSpacing(7);
        int l=0;
        for (int i=0;i<var;i++){
            tabBut[i].setDisable(true);
            if (tabBut[l]==tabBut[i] && tabBut[l].getText().equals("["+l+"]") || l==0){
                tabBut[i].setDisable(false);
                l++;
            }
        }
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
               tabBut[temp].setText(tf.getText());
               tf.setText("1");
               varTemp.set(varTemp.get()+1);
               if (varTemp.get()==var){
                  dialog1.getDialogPane().getButtonTypes().add(buttonDialog1);
                  dialog1.setWidth(500);
                  dialog1.setHeight(500);

               }
               if (element.size()==var){
                   matrice.setElement(element);
                   tabMat.add(matrice);
                   int nombre = element.size();
                   for (int j =0;j<nombre;j++){
                       element.clear();
                   }
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
            dialog1.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
            dialog1.getDialogPane().setContent(vb);
            dialog1.showAndWait();
        }
    }
    @FXML
    public void addition() {
        int chiffre = 65;
        Label label1 = new Label("Quelle matrice voulez-vous additionner?");
        dialogCheckBox.getDialogPane().setContent(label1);
    }
}
