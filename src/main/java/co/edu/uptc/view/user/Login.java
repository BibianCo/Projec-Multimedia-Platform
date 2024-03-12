package co.edu.uptc.view.user;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.PlanController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Login implements Initializable {

    @FXML
    private TextField userNameField;

    @FXML
    private Label messageError, messageLabel;
    @FXML
    private TextField passwordField;

    private UserController userController;
    private FilePersistence<User> filePersistence;
    private SubscriptionController subscriptionController;
    private FilePersistence<Subscription> filePersistence2;
    private PlanController planController;
    private FilePersistence<Plan> filePersistence3;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Type typeEpisode = new TypeToken<ArrayList<User>>() {
        }.getType();
        Type typeSuscription = new TypeToken<ArrayList<Subscription>>() {
        }.getType();
        this.filePersistence3 = new FilePersistence<>(typeSuscription, "plans");
        this.planController = new PlanController(filePersistence3);
        this.filePersistence2 = new FilePersistence<>(typeSuscription, "suscription");
        this.subscriptionController = new SubscriptionController(filePersistence2, planController);
        this.filePersistence = new FilePersistence<>(typeEpisode, "users");
        this.userController = new UserController(filePersistence, subscriptionController);
        filePersistence.createFile();
    }

    @FXML
    public void login() throws IOException {

        if (userNameField.getText().isEmpty() || userNameField.getText().trim().isEmpty()) {
            messageError.setText("Empty username, enter an email.");
        } else if (!emailValidation(userNameField.getText())) {
            messageError.setText("Invalid email, enter a valid format.");
        } else if (passwordField.getText().trim().isEmpty() || passwordField.getText().isEmpty()) {
            messageLabel.setText("Empty password, enter a password.");
        } else {
            Role userRole = userController.validarRol(userNameField.getText(), passwordField.getText());
            System.out.println(userRole);
            if (userRole != null && userRole.getName().equals("admin")) {
                Main.setRoot("menuadmi-view");
            } else if (userController.logIn(userNameField.getText(), passwordField.getText())) {
                Main.setRoot("users-main");
            }

            messageLabel.setText("Incorrect username or password.");
            messageError.setText("");
            userNameField.clear();
            passwordField.clear();
        }
    }

    @FXML
    public void regis() {
        try {
            Main.setRoot("administrador-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void menu() {
        try {
            Main.setRoot("visitante-view");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean emailValidation(String email) {

        ArrayList<String> listDominio = new ArrayList<>();
        listDominio.add("@gmail.com");
        listDominio.add("@uptc.edu.co");
        listDominio.add("@outlook.es");
        listDominio.add("@yahoo.com");

        for (String s : listDominio) {
            if (email.contains(s)) {
                int position = email.length() - s.length();
                String aux = email.substring(0, position);

                if (aux.contains("@")) {
                    return false;
                } else {
                    return true;
                }

            }

        }
        return false;
    }

}
