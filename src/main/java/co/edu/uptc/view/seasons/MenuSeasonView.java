package co.edu.uptc.view.seasons;

import java.io.IOException;

import co.edu.uptc.Main;
import javafx.fxml.FXML;

public class MenuSeasonView {

    @FXML
    private void sceneCreate() throws IOException {
        Main.setRoot("create-season");

    }

    @FXML
    private void sceneDelete() throws IOException {
        Main.setRoot("delete-season");
    }

    @FXML
    private void sceneUpdate() throws IOException {
        Main.setRoot("update-season");
    }

    @FXML
    private void sceneFind() throws IOException {
        Main.setRoot("find-seasons");
    }

    @FXML
    private void sceneObtainAll() throws IOException {
        Main.setRoot("obtainAll-season");
    }

    @FXML
    private void createEpiosode() throws IOException {
        Main.setRoot("create-episode");
    }
}