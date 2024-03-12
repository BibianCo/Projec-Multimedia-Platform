package co.edu.uptc.view.user;

import java.io.IOException;

import co.edu.uptc.Main;
import javafx.fxml.FXML;

public class Visitante {

    @FXML
    private void goOut() {
        System.exit(0);
    }

    @FXML
    private void singIn() throws IOException {
        Main.setRoot("administrador-view");

    }

    @FXML
    private void logIn() throws IOException {
        Main.setRoot("login-User");

    }

}
