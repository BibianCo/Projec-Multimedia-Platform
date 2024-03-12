package co.edu.uptc.view.movies;

import java.io.IOException;

import co.edu.uptc.Main;
import javafx.fxml.FXML;

public class MenuMovieView {

    @FXML
    private void sceneCreate() throws IOException {
        Main.setRoot("create-movie");

    }

    @FXML
    private void sceneDelete() throws IOException {
        Main.setRoot("delete-movies");
    }

    @FXML
    private void sceneUpdate() throws IOException {
        Main.setRoot("update-movies");
    }

    @FXML
    private void sceneFind() throws IOException {
        Main.setRoot("find-movie");
    }

    @FXML
    private void sceneObtainAll() throws IOException {
        Main.setRoot("obtainAll-movies");
    }

    @FXML
    private void salir() throws IOException {
        Main.setRoot("menuadmi-view");
    }
}
