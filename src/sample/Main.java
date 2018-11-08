package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        // Modèle
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Matrice");
        stage.setMaximized(true);
        stage.setResizable(false);


        //Vue
        stage.setScene(new Scene(root));
        stage.show();


        //Controlleur
    }


    public static void main(String[] args) {
        launch(args);
    }
}
