package co.edu.uptc.view.categories;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.model.Category;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateCategoryView implements Initializable {
    @FXML
    private TextField categoryName;

    @FXML
    private TableView<Category> tableView;

    @FXML
    private TableColumn<Category, Integer> idColumn;

    @FXML
    private TableColumn<Category, String> nameColumn;

    @FXML
    private Label messageError;

    public CategoryController controller;
    public FilePersistence<Category> filePersistence;
    private Type type;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        type = new TypeToken<ArrayList<Category>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "categories");
        controller = new CategoryController(filePersistence);
        filePersistence.createFile();
        loadItems();

    }

    @FXML
    private void createCategory() throws IOException {
        if (categoryName.getText().isEmpty() || categoryName.getText().trim().isEmpty()) {
            messageError.setText("Error empty string, enter name");
        } else if (!categoryName.getText().matches("[a-zA-Z]+")) {
            messageError.setText("Only letters are accepted");
        } else {
            Category category = new Category(setId(), categoryName.getText());
            if (controller.add(category)) {
                categoryName.clear();
                messageError.setText("");
                loadItems();
            } else {
                messageError.setText("The category does exist");
                categoryName.clear();
            }
        }
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-categories");
    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(controller.getAll());
    }

    public int setId() {
        if (controller.getAll().isEmpty()) {
            return 1;
        } else {
            return controller.getAll().get(controller.getAll().size() - 1).getId() + 1;
        }

    }

    public boolean validateEntries(String entrie) {
        if (entrie.isEmpty()) {
            messageError.setText("Error empty string");
            return true;
        } else if (entrie.matches("[a-zA-Z]+")) {
            messageError.setText("Only letters are accepted");
            return true;
        } else {
            return false;
        }

    }

}
