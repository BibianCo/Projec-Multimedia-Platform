package co.edu.uptc.view.serie.episode;

import java.io.IOException;

import co.edu.uptc.Main;
import javafx.fxml.FXML;

public class Menusec {

    @FXML
    private void menuCaregories() throws IOException {
        Main.setRoot("menu-crud-categories");

    }

    @FXML
    private void menuSerie() throws IOException {
        Main.setRoot("menu-crud-series");

    }

    @FXML
    private void menuMovie() throws IOException {
        Main.setRoot("menu-crud-movies");

    }

    @FXML
    private void menuPlanes() throws IOException {
        Main.setRoot("create-plans");

    }

}
