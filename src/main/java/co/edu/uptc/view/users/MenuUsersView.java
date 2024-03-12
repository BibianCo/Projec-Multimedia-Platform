package co.edu.uptc.view.users;

import java.io.IOException;

import co.edu.uptc.Main;
import javafx.fxml.FXML;

public class MenuUsersView {

    @FXML
    private void sceneCreate() throws IOException {
        Main.setRoot("create-users");
    }

    @FXML
    private void sceneDelete() throws IOException {
        Main.setRoot("delete-series");
    }

    @FXML
    private void sceneUpdate() throws IOException {
        Main.setRoot("update-series");
    }

    @FXML
    private void sceneFind() throws IOException {
        Main.setRoot("find-series");
    }

    @FXML
    private void sceneObtainAll() throws IOException {
        Main.setRoot("obtainAll-series");
    }
}
