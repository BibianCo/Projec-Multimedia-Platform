package co.edu.uptc.view.users;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import co.edu.uptc.Main;
import co.edu.uptc.controller.PlanController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.FilePersistence;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uptc.model.Plan;
import co.edu.uptc.persistence.Persistence;

public class CreateUsersView implements Initializable {
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label messageError;

    @FXML
    private ComboBox<Plan> planComboBox;

    private UserController controller;
    private FilePersistence<User> filePersistence;
    private Persistence<Plan> persistence;
    private Type type;
    private PlanController planController;
    private Plan plan;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        type = new TypeToken<ArrayList<User>>() {
        }.getType();

        filePersistence = new FilePersistence<>(type, "users");
        SubscriptionController subscriptionController = new SubscriptionController();
        controller = new UserController(filePersistence, subscriptionController);
        filePersistence.createFile();

        persistence = new FilePersistence<>(new TypeToken<ArrayList<Plan>>() {
        }.getType(), "plans");
        planController = new PlanController(persistence);

        planComboBox.getItems().addAll(planController.getAll());
        planComboBox.setOnAction(this::handleComboBoxAction);

    }

    @FXML
    private void createUser() {
        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty()
                || password.getText().isEmpty()) {
            messageError.setText("Error: Fields cannot be empty");
            return;
        }

        if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty()) {
            messageError.setText("Error: Fields cannot be empty");
            return;
        }

        if (plan != null) {
            Role role = new Role(setId(), "user");
            Subscription subscription = new Subscription(setId(), plan);
            User user2 = new User(setId(), firstName.getText(), lastName.getText(), email.getText(), password.getText(),
                    role, subscription, new ArrayList<>());
            System.out.println(user2);
            if (controller.add(user2)) {
                clearFields();
                try {
                    Main.setRoot("users-main");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        } else {
            messageError.setText("Error: Unable to create user");
        }

    }

    @FXML
    private void menu() throws IOException {
        Main.setRoot("visitante-view");
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

    public void handleComboBoxAction(ActionEvent event) {
        plan = planComboBox.getValue();

    }

    private void clearFields() {
        firstName.clear();
        lastName.clear();
        email.clear();
        password.clear();
    }

    public int setId() {
        if (controller.getAll().isEmpty()) {
            return 1;
        } else {
            return controller.getAll().get(controller.getAll().size() - 1).getId() + 1;
        }
    }
}
