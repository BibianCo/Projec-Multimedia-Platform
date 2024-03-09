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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteCategoryView implements Initializable {

    @FXML
    private TableView<Category> tableView;

    @FXML
    private ComboBox<Category> comboBoxCategory;

    @FXML
    private TableColumn<Category, Integer> idColumn;

    @FXML
    private TableColumn<Category, String> nameColumn;
    @FXML
    private Label messageError;

    public CategoryController controller;
    public FilePersistence<Category> filePersistence;
    private Type type;
    private Category deleteCategory;

    @FXML
    private void deleteCategory() throws IOException {

        if (deleteCategory == null) {
            messageError.setText("Error, select category to delete");
        } else if (controller.delete(deleteCategory.getId())) {
            loadItems();
            messageError.setText("");
            comboBoxCategory.getItems().clear();
            comboBoxCategory.getItems().addAll(controller.getAll());
        } else {
            messageError.setText("The category does not exist");
        }

        if (controller.getAll().isEmpty() || controller.getAll() == null) {
            messageError.setText("There are no categories created to delete, please create categories.");
            comboBoxCategory.getItems().clear();
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
        comboBoxCategory.getItems().addAll(controller.getAll());

        comboBoxCategory.setOnAction(this::comboBoxCategoryAction);
        loadItems();
    }

    public void comboBoxCategoryAction(ActionEvent event) {
        deleteCategory = comboBoxCategory.getValue();
        comboBoxCategory.getEditor().clear();

    }
}
