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
        Main.setRoot("update-categories");
    }

    @FXML
    private void sceneFind() throws IOException {
        Main.setRoot("find-categories");
    }

    @FXML
    private void sceneObtainAll() throws IOException {
        Main.setRoot("obtainall-categories");
    }

    @FXML
    private void salir() throws IOException {
        Main.setRoot("menuadmi-view");
    }
}
