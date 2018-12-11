package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polyline;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Controller {
    @FXML
    public BorderPane bPaneOp;
    @FXML
    public VBox resultView;
    @FXML
    public HBox ligneOp;
    @FXML
    public ChoiceBox<String> matrice1;
    @FXML
    public ChoiceBox operation1;
    Tab tabIni;
    @FXML
    public GridPane placeMat;
    @FXML
    public BorderPane bp;


    public static ArrayList<Matrice> tabMat=new ArrayList<>();
    public static ChoiceBox<String> matrice2=new ChoiceBox<>();
    public static TextField scalaire2=new TextField();
    public static int answerScalair;
    public static Matrice answerMat;
    public static List<String> listeOp = Arrays.asList("01-addition","02-soustraction","03-produit vectorielle","04-produit matricielle","05-produit tensorielle","06-produit d'Hadamard","07-puissance","08-multiplication par scalaire","09-tranposition","10-inversion","11-Déterminant");
    public static  ObservableList<String> observableListOp = FXCollections.observableList(listeOp);

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
            Matrice matrice=new Matrice();
            matrice.setNomMat((char)(tabMat.size()+65));
            //dialog0
            Spinner spinnerL= new Spinner(1,5,1);
            Spinner spinnerC= new Spinner(1,5,1);
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
                setChoiceMat1();
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
                        setChoiceMat1();
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
    public void setChoiceMat1(){
        matrice1.setItems(setObservForMat());
        matrice1.setOnAction((event -> {
            ObservableList<String> observableList=FXCollections.observableList(new ArrayList<>());
            operation1.setItems(observableList);
            operation1.setItems(observableListOp);
        }));
        operation1.setOnAction((event -> {
            ligneOp.getChildren().remove(2,ligneOp.getChildren().size());
            try {
                if (Integer.parseInt(operation1.getValue().toString().substring(0,2))<7){
                    matrice2.setItems(setObservForMat());
                    matrice2.setPrefWidth(50);
                    VBox mat2=new VBox(new Label("Matrice"), matrice2);
                    mat2.setAlignment(Pos.CENTER);
                    ligneOp.getChildren().add(mat2);
                }
                else if (Integer.parseInt(operation1.getValue().toString().substring(0,2))==7 ||Integer.parseInt(operation1.getValue().toString().substring(0,2))==8){
                    scalaire2.clear();
                    VBox scal2=new VBox(new Label("Scalaire"), scalaire2);
                    scal2.setAlignment(Pos.CENTER);
                    ligneOp.getChildren().add(scal2);
                }
            }catch (Exception e){
                //C'est juste pcq la choice box est vide, c'est pas grave
            }


        }));
    }
    public ObservableList setObservForMat(){
        ArrayList<String> liste = new ArrayList<>();
        for (int i=0;i<tabMat.size();i++){
            liste.add(Character.toString(tabMat.get(i).getNomMat()));
        }
        ObservableList<String> observableList = FXCollections.observableList(liste);
        return observableList;
    }
    @FXML
    public void reset(){
        resultView.getChildren().clear();
        matrice1.getSelectionModel().clearSelection();
        ObservableList<String> observableList=FXCollections.observableList(new ArrayList<>());
        operation1.setItems(observableList);
        ligneOp.getChildren().remove(2,ligneOp.getChildren().size());
    }
    @FXML
    public void calcul(){
        try {
            HBox hBox=new HBox(new Label("Le résultat est dans une autre page -->"));
            hBox.setAlignment(Pos.CENTER);
            bPaneOp.setBottom(hBox);
            Alert alerte5 = new Alert(Alert.AlertType.INFORMATION);
            alerte5.setTitle("Important");
            alerte5.setHeaderText("ERREUR");
            alerte5.setContentText("L'opération ne peut s'effectuer, vérifier les dimensions et propriétés");
            int op=Integer.parseInt(operation1.getValue().toString().substring(0,2));
            switch (op){
                case 1:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).addition(tabMat.get((int)matrice2.getValue().charAt(0)-65));
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 2:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).soustraction(tabMat.get((int)matrice2.getValue().charAt(0)-65));
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 3:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).produitVect(tabMat.get((int)matrice2.getValue().charAt(0)-65));
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 4:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).multiplication(tabMat.get((int)matrice2.getValue().charAt(0)-65));
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 5:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).produitTensoriel(tabMat.get((int)matrice2.getValue().charAt(0)-65));
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 6:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).produitHadamard(tabMat.get((int)matrice2.getValue().charAt(0)-65));
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 7:
                    int value=Integer.parseInt(scalaire2.getText());
                    if (value<0){
                        Alert alerte6 = new Alert(Alert.AlertType.INFORMATION);
                        alerte6.setTitle("Important");
                        alerte6.setHeaderText("ERREUR");
                        alerte6.setContentText("Entrez un nombre plus grand ou égal à 0");
                        alerte6.showAndWait();
                    }
                    else {
                        answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).puissance(value);
                    }
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 8:
                    int value2=Integer.parseInt(scalaire2.getText());
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).multiScalaire(value2);
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 9:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).transposition();
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 10:
                    answerMat=tabMat.get((int)matrice1.getValue().charAt(0)-65).inversion();
                    if (answerMat==null){
                        alerte5.showAndWait();
                    }
                    else
                        afficherResultat();
                    break;
                case 11:
                    if (tabMat.get((int)matrice1.getValue().charAt(0)-65).determinant()==null){
                        alerte5.showAndWait();
                    }
                    else {
                        Label answer=new Label("R = "+Double.toString(tabMat.get((int)matrice1.getValue().charAt(0)-65).determinant().getDeterminant()));
                        answer.setScaleX(2.5);
                        answer.setScaleY(2.5);
                        resultView.getChildren().clear();
                        resultView.getChildren().add(answer);
                        resultView.setAlignment(Pos.CENTER);
                    }

                    break;
                default:
            }
        }catch (Exception e){
            Alert alerte7 = new Alert(Alert.AlertType.INFORMATION);
            alerte7.setTitle("Important");
            alerte7.setHeaderText("ERREUR");
            alerte7.setContentText("Entrez des valeur avant de calculer!!!!");
            alerte7.showAndWait();
        }



    }
    public void afficherResultat(){
        resultView.getChildren().clear();
        HBox matriceEtNom=new HBox();
        Label name=new Label("R = ");
        name.setScaleX(2.5);
        name.setScaleY(2.5);
        Polyline crochetOpen=new Polyline(5,0,0,0,0,35,5,35);
        Polyline crochetClose=new Polyline(0,0,5,0,5,35,0,35);
        crochetClose.setScaleY(1.4*answerMat.getTailleL());
        crochetOpen.setScaleY(1.4*answerMat.getTailleL());
        crochetClose.setScaleX(answerMat.getTailleL());
        crochetOpen.setScaleX(answerMat.getTailleL());
        VBox mat=new VBox();
        for (int l=0;l<answerMat.getTailleL();l++){
            HBox ligne= new HBox();
            for (int k=0;k<answerMat.getTailleC();k++){
                double d=answerMat.getElement().get((answerMat.getTailleC()*l)+k).getValeur();
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
        resultView.getChildren().add(matriceEtNom);
        resultView.setAlignment(Pos.CENTER);

    }
    //https://stackoverflow.com/questions/34815660/javafx-image-getting-scaled-to-25-and-then-getting-printed
    @FXML
    public void printThis() {
        ImageView imageView = new ImageView(resultView.snapshot(null,null));
        imageView.setPreserveRatio(true);
        new Thread(() -> printImage(imageView)).start();
    }

    public void printImage(ImageView image) {
        PrinterJob job = PrinterJob.createPrinterJob();
        PageLayout pageLayout = job.getJobSettings().getPageLayout();
        image.setFitHeight(pageLayout.getPrintableHeight());
        image.setFitWidth(pageLayout.getPrintableWidth());
        image.setScaleX(2);
        image.setScaleY(2);

        //PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        job.getJobSettings().setPageLayout(pageLayout);
        if (job != null && job.showPrintDialog(Main.returnRoot().getScene().getWindow())) {
            boolean success = job.printPage(image);
            if (success) {
                job.endJob();
            }
        }
    }
    public void loadCSV() {
        Matrice matriceRes= new Matrice();
        ArrayList<Element> elem = new ArrayList<>();
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Veuillez sélectionner un fichier");
            fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
            File fichier = fc.showOpenDialog(Main.stage1);
            List<String> ligne = Files.readAllLines(fichier.toPath());
            String string;
            for (int i = 0; i < ligne.size(); i++) {
                string = ligne.get(i);
                String[] parts = string.split(",");
                for (String part : parts) {
                    Element element = new Element();
                    element.setValeur(Double.parseDouble(part));
                    elem.add(element);
                }
                matriceRes.setTailleC(parts.length);
            }
            matriceRes.setElement(elem);
            matriceRes.setTailleL(ligne.size());
            tabMat.add(matriceRes);
            renameMat();
            afficherMat();
            setChoiceMat1();

        } catch (Exception e) {
            System.out.println("Marche pas");
        }
    }
    public void addOp(){
        Alert alerte8 = new Alert(Alert.AlertType.INFORMATION);
        alerte8.setTitle("ERREUR ERREUR ERREUR ERREUR ERREUR");
        alerte8.setHeaderText("ERREUR ERREUR ERREUR ERREUR ERREUR ERREUR");
        alerte8.setContentText("Lol! on la pas fait");
        alerte8.showAndWait();
    }

}
