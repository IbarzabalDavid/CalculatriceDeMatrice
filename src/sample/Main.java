package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
   private static Parent root;
   public static Stage stage1;
    @Override
    public void start(Stage stage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        stage.setTitle("Matrice");
        stage.setMaximized(true);
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static Parent returnRoot(){
        return root;
    }
}
