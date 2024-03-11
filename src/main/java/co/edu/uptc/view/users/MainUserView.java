package co.edu.uptc.view.users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uptc.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;

public class MainUserView implements Initializable {

    @FXML
    private ScrollPane rightPane;

    @FXML
    private void sceneSearchMovies() throws IOException {
        Main.setRoot("search-categories-movie");
    }

    @FXML
    private void sceneSearchSerie() throws IOException {
        Main.setRoot("search-categories-serie");
    }

    @FXML
    private void sceneShowMovies() {
    }

    @FXML
    private void sceneShowSerie() {

    }

    @FXML
    private void sceneCostomList() {

    }

    @FXML
    private void sceneExit() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        rightPane.setVvalue(0.0);

    }

}
