package co.edu.uptc.view.users;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Role;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.FilePersistence;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class CreateUsersView implements Initializable {
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField email;

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, Integer> idColumn;

    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, String> emailColumn;

    @FXML
    private Label messageError;

    public UserController controller;
    public FilePersistence<User> filePersistence;
     private Type type;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        type = new TypeToken<ArrayList<User>>() {}.getType();

        // Inicializar el controlador de usuarios y la persistencia de archivos
        filePersistence = new FilePersistence<>(type, "users");
        SubscriptionController subscriptionController = new SubscriptionController();
        controller = new UserController(filePersistence, subscriptionController);
        filePersistence.createFile();
        loadItems();
    }

    @FXML
    private void createUser() {

            if (firstName.getText().isEmpty() || lastName.getText().isEmpty() || email.getText().isEmpty()) {
                messageError.setText("Error: Fields cannot be empty");
                return;
            }

            String generatedPassword = lastName.getText().substring(0, 1) + firstName.getText().substring(0, 1) + "1234";

            Role role = new Role(1, "usuario");
            User user = new User(setId(), firstName.getText(), lastName.getText(), email.getText(), generatedPassword, role);

            if (controller.add(user)) {
                clearFields();
                messageError.setText("");
                loadItems();
            } else {
                messageError.setText("Error: Unable to create user");
            }
        
    }

    private void clearFields() {
        firstName.clear();
        lastName.clear();
        email.clear();
    }

    private void loadItems() {
        tableView.getItems().setAll(controller.getAll());
    }

    public int setId() {
        if (controller.getAll().isEmpty()) {
            return 1;
        } else {
            return controller.getAll().get(controller.getAll().size() - 1).getId() + 1;
        }
    }
}
