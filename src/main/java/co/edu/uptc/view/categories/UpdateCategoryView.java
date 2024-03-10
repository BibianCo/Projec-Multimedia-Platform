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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpdateCategoryView implements Initializable {
    @FXML
    private TextField categoryName;
    @FXML
    private TableView<Category> tableView;

    @FXML
    private ComboBox<Category> comboBox;

    @FXML
    private TableColumn<Category, Integer> idColumn;

    @FXML
    private TableColumn<Category, String> nameColumn;

    @FXML
    private Label messageError;
    @FXML
    private Label messageError1;

    public CategoryController controller;
    public FilePersistence<Category> filePersistence;
    private Type type;
    private Category updateCategory;

    @FXML
    private void updateCategory() throws IOException {

        if (updateCategory == null) {
            messageError1.setText("Error, select category to update");

        } else if (categoryName.getText().isEmpty() || categoryName.getText().trim().isEmpty()) {
            messageError.setText("Error empty string, enter name");
            messageError1.setText("");

        } else if (!categoryName.getText().matches("\\b[a-zA-Z]+(\\s+[a-zA-Z]+)*\\b")) {
            messageError.setText("Only words are accepted.");
            messageError1.setText("");

        } else {
            Category category = new Category(updateCategory.getId(), categoryName.getText());
            boolean existCategory = controller.update(updateCategory.getId(), category);

            if (existCategory == true) {
                categoryName.clear();
                messageError.setText("");
                messageError1.setText("");
                comboBox.getItems().clear();
                comboBox.getItems().addAll(controller.getAll());
                loadItems();
            } else {
                messageError.setText("The category does exist");
            }
        }

        if (controller.getAll().isEmpty() || controller.getAll() == null) {
            messageError.setText("There are no categories created to delete, please create categories.");
            comboBox.getItems().clear();
        }
        categoryName.clear();

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
        comboBox.getItems().addAll(controller.getAll());

        comboBox.setOnAction(this::comboBoxCategoryAction);
        loadItems();
    }

    public void comboBoxCategoryAction(ActionEvent event) {
        updateCategory = comboBox.getValue();
        comboBox.getEditor().clear();

    }
}
