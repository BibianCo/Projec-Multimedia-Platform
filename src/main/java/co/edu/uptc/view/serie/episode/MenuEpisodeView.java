package co.edu.uptc.view.serie.episode;

import java.io.IOException;

import co.edu.uptc.Main;
import javafx.fxml.FXML;

public class MenuEpisodeView {
    @FXML
    private void sceneCreate() throws IOException {
        Main.setRoot("create-episode");

    }

    @FXML
    private void sceneDelete() throws IOException {
        Main.setRoot("delete-episode");
    }

    @FXML
    private void sceneFind() throws IOException {
        Main.setRoot("find-episode");
    }

    @FXML
    private void sceneObtainAll() throws IOException {
        Main.setRoot("obtainAll-episode");
    }

    @FXML
    private void sceneMenuSeries() throws IOException {
        Main.setRoot("menu-crud-series");
    }

}
