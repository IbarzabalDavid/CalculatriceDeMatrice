package sample;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polyline;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    @FXML
    Tab tabIni;
    @FXML
    public GridPane placeMat;

    public static ArrayList<Matrice> tabMat=new ArrayList<>();
    @FXML
    public void nouvelleMatrice(){
        if (tabMat.size()==9){
            Alert alerte2 = new Alert(Alert.AlertType.INFORMATION);
            alerte2.setTitle("Important");
            alerte2.setHeaderText("ERREUR");
            alerte2.setContentText("Vous avez deéjà le nombre maximum de matrices");
            alerte2.showAndWait();
        }
        else {
            printThis();
            Matrice matrice=new Matrice();
            matrice.setNomMat((char)(tabMat.size()+65));
            //dialog0
            Spinner spinnerL= new Spinner(1,5,3);
            Spinner spinnerC= new Spinner(1,5,3);
            Label labelL = new Label("Entrez le nombre de lignes de votre matrice     ");
            Label labelC = new Label("Entrez le nombre de colonnes de votre matrice");
            HBox hb = new HBox(labelL,spinnerL);
            hb.setSpacing(7);
            HBox hb1 = new HBox(labelC,spinnerC);
            hb1.setSpacing(7);
            VBox vb = new VBox(hb,hb1);
            vb.setSpacing(7);
            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(vb);
            dialog.getDialogPane().getButtonTypes().addAll( ButtonType.OK,ButtonType.CANCEL);
            ButtonType resultat = (ButtonType) dialog.showAndWait().get();
            if(resultat == ButtonType.OK){
                matrice.setTailleL((int)spinnerL.getValue());
                matrice.setTailleC((int)spinnerC.getValue());
                //dialog1
                matrice.setNbElement(matrice.getTailleL()*matrice.getTailleC());
                NewButton[] tabBut = new NewButton[matrice.getNbElement()];
                for (int i=0;i<matrice.getNbElement();i++){
                    tabBut[i]=new NewButton();
                    tabBut[i].setRank(i);
                    tabBut[i].setText("["+i+"]");
                    tabBut[i].setMaxSize(50,50);
                    tabBut[i].setMinSize(50,50);
                }
                VBox vbTemp= new VBox();
                int constante=0;
                for (int i=0;i<matrice.getTailleL();i++){
                    HBox hbTemp = new HBox();
                    hbTemp.setSpacing(7);
                    for (int j=0;j<matrice.getTailleC();j++){
                        hbTemp.getChildren().addAll(tabBut[constante]);
                        constante++;
                    }
                    vbTemp.getChildren().addAll(hbTemp);
                    vbTemp.setSpacing(7);
                }
                Dialog dialog1 = new Dialog();
                dialog1.getDialogPane().setContent(vbTemp);
                //dialog2
                Label question = new Label("Entrez la valeur ");
                TextField tf = new TextField();
                HBox labTf = new HBox(question,tf);
                labTf.setSpacing(7);
                Dialog dialog2 = new Dialog();
                dialog2.getDialogPane().setContent(labTf);
                dialog2.getDialogPane().getButtonTypes().add( new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
                //Alerte erreur
                Alert alerte = new Alert(Alert.AlertType.INFORMATION);
                alerte.setTitle("Important");
                alerte.setHeaderText("ERREUR");
                alerte.setContentText("Vous devez entrez un nombre. \n(nombres décimaux sont aussi accepté ex: 3.5)");
                //Action des boutons dans dialog1
                for (int i=0;i<matrice.getNbElement();i++){
                    final int temp=i;
                    tabBut[i].setOnAction((event)->{
                        Element elem= new Element();
                        elem.setPosition(tabBut[temp].getRank());
                        dialog2.showAndWait();
                        try {
                            double value=Double.parseDouble(tf.getText());
                            elem.setValeur(value);
                            for (int m=0; m<matrice.getElement().size();m++){
                                if (matrice.getElement().get(m).getPosition()==elem.getPosition()){
                                    matrice.getElement().remove(m);
                                }
                            }
                            matrice.getElement().add(elem);
                            tabBut[temp].setText(tf.getText());
                            Boolean ttChiffrreOk=false;
                            for (int j=0;j<matrice.getNbElement();j++){
                                ttChiffrreOk=true;
                                if (tabBut[j].getText().charAt(0)=='['){
                                    ttChiffrreOk=false;
                                    j=matrice.getNbElement();
                                }
                            }
                            if (ttChiffrreOk){
                                if (dialog1.getDialogPane().getButtonTypes().size()==0){
                                    dialog1.getDialogPane().getButtonTypes().add( new ButtonType("ok", ButtonBar.ButtonData.OK_DONE));
                                    dialog1.setWidth(matrice.getTailleC()*60+40);
                                    dialog1.setHeight(matrice.getTailleL()*60+90);
                                }
                            }
                        }
                        catch (Exception e){
                            alerte.showAndWait();
                        }
                        tf.setText("");
                    });
                }
                dialog1.showAndWait();
                Collections.sort(matrice.getElement());
                tabMat.add(matrice);
                afficherMat();
            }
        }
    }
    @FXML
    public void supprimerMatrice(){
        Label label=new Label("Entrez le nom de la matrice à supprimer");
        TextField textField=new TextField();
        HBox hb=new HBox(label,textField);
        hb.setSpacing(7);
        Dialog dialog3 = new Dialog();
        dialog3.getDialogPane().setContent(hb);
        dialog3.getDialogPane().getButtonTypes().add( new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        Alert alerte = new Alert(Alert.AlertType.INFORMATION);
        alerte.setTitle("Important");
        alerte.setHeaderText("ERREUR");
        alerte.setContentText("Aucune matrice trouvée :(");
        Alert alerte1 = new Alert(Alert.AlertType.INFORMATION);
        alerte1.setTitle("Réussi");
        alerte1.setContentText("Attention!!! Il est possible que vos autres matrices aient changés de noms.");
        Alert alerte2 = new Alert(Alert.AlertType.INFORMATION);
        alerte2.setTitle("Important");
        alerte2.setHeaderText("Erreur");
        alerte2.setContentText("Vous n'avez aucune matrice!");
        if (tabMat.size()!=0){
            dialog3.showAndWait();
            if (textField.getText().length()!=1){
                alerte.showAndWait();
            }
            else if ((int)textField.getText().toUpperCase().charAt(0)<65||(int)textField.getText().toUpperCase().charAt(0)>(65+tabMat.size())){
                alerte.showAndWait();
            }
            else {
                for (int i=0;i<tabMat.size();i++){
                    if (tabMat.get(i).getNomMat()==textField.getText().toUpperCase().charAt(0)){
                        alerte1.setHeaderText("Opération réussi, la matrice "+textField.getText().toUpperCase()+" a été supprimée.");
                        alerte1.showAndWait();
 //pt afficher la matrice qui a ete delete
                        tabMat.remove(i);
                        renameMat();
                        afficherMat();
                    }
                }
            }
        }
        else {
            alerte2.showAndWait();
        }
    }
    public void renameMat(){
        for (int i=0;i<tabMat.size();i++){
            tabMat.get(i).setNomMat((char)(i+65));
        }
    }
    public void afficherMat(){
        placeMat.getChildren().clear();
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                HBox matriceEtNom=new HBox();
                if (3*i+j<tabMat.size()){
                    Label name=new Label(Character.toString(tabMat.get(3*i+j).getNomMat())+" = ");
                    name.setScaleX(2.5);
                    name.setScaleY(2.5);
                    Polyline crochetOpen=new Polyline(5,0,0,0,0,35,5,35);
                    Polyline crochetClose=new Polyline(0,0,5,0,5,35,0,35);
                    crochetClose.setScaleY(1.4*tabMat.get(3*i+j).getTailleL());
                    crochetOpen.setScaleY(1.4*tabMat.get(3*i+j).getTailleL());
                    crochetClose.setScaleX(tabMat.get(3*i+j).getTailleL());
                    crochetOpen.setScaleX(tabMat.get(3*i+j).getTailleL());
                    VBox mat=new VBox();
                    for (int l=0;l<tabMat.get(3*i+j).getTailleL();l++){
                        HBox ligne= new HBox();
                        for (int k=0;k<tabMat.get(3*i+j).getTailleC();k++){
                            double d=tabMat.get(3*i+j).getElement().get((tabMat.get(3*i+j).getTailleC()*l)+k).getValeur();
                            Label chiffre=new Label();
                            if ((d % 1) == 0){
                                chiffre.setText(Integer.toString((int)d));
                            }
                            else {
                                chiffre.setText(Double.toString(d));
                            }
                            chiffre.setScaleX(2);
                            chiffre.setScaleY(2);
                            ligne.getChildren().add(chiffre);
                            ligne.setAlignment(Pos.CENTER);
                            ligne.setSpacing(40);
                        }
                        mat.getChildren().add(ligne);
                        mat.setAlignment(Pos.CENTER);
                        mat.setSpacing(40);
                    }
                    matriceEtNom.getChildren().addAll(name,crochetOpen,mat,crochetClose);
                    matriceEtNom.setSpacing(23);
                    matriceEtNom.setAlignment(Pos.CENTER);
                }
                placeMat.add(matriceEtNom,j,i);
            }
        }
    }
    //https://stackoverflow.com/questions/34815660/javafx-image-getting-scaled-to-25-and-then-getting-printed
    //Manque de changer la chose qu'on veut imprimer.
    public void printThis() {
        ImageView imageView = new ImageView(tabIni.getTabPane().snapshot(null,null));
        new Thread(() -> printImage(imageView)).start();
    }

    public void printImage(ImageView image) {
        PrinterJob job = PrinterJob.createPrinterJob();
        PageLayout pageLayout = job.getJobSettings().getPageLayout();
        //PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        job.getJobSettings().setPageLayout(pageLayout);
        if (job != null && job.showPrintDialog(Main.returnRoot().getScene().getWindow())) {
            boolean success = job.printPage(image);
            if (success) {
                job.endJob();
            }
        }
    }



}
