package co.edu.uptc;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("menu-crud-episode"), 700, 600);

        stage.setScene(scene);
        stage.setTitle("Menu de categorias");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));

        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}