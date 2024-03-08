package co.edu.uptc.view.categories;

import java.io.IOException;

import co.edu.uptc.Main;
import javafx.fxml.FXML;

public class MenuCategoryView {

    @FXML
    private void sceneCreate() throws IOException {
        Main.setRoot("create-categories");
    }

    @FXML
    private void sceneDelete() throws IOException {
        Main.setRoot("delete-categories");
    }

    @FXML
    private void sceneUpdate() throws IOException {
        System.out.println("hola");
    }

    @FXML
    private void sceneFind() throws IOException {
        System.out.println("hola");
    }

    @FXML
    private void sceneObtainAll() throws IOException {
        System.out.println("hola");
    }
}
