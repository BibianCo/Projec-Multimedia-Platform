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

public class DeleteCategoryView implements Initializable {

    @FXML
    private TextField categoryId;

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

    @FXML
    private void deleteCategory() throws IOException {
        if (categoryId.getText().isEmpty() || !categoryId.getText().matches("[0-9]+")) {
            messageError.setText("error empty string or only numbers accepted");
            categoryId.clear();

        } else if (controller.delete(Integer.parseInt(categoryId.getText()))) {
            categoryId.clear();
            loadItems();
        } else {
            messageError.setText("The category does not exist");
            categoryId.clear();
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        type = new TypeToken<ArrayList<Category>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "categories");
        controller = new CategoryController(filePersistence);
        loadItems();
    }
}
