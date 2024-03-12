package co.edu.uptc.view.users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uptc.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

public class MainUserView {

    @FXML
    private void sceneSearchMovies() throws IOException {
        Main.setRoot("search-categories-movie");
    }

    @FXML
    private void sceneSearchSerie() throws IOException {
        Main.setRoot("search-categories-serie");
    }

    @FXML
    private void sceneShowMovies() throws IOException {
        Main.setRoot("obtainAll-movies");
    }

    @FXML
    private void sceneShowSerie() {

    }

    @FXML
    private void sceneCostomList() throws IOException {
        Main.setRoot("list-reproduce");

    }

    @FXML
    private void sceneExit() {

    }

}
