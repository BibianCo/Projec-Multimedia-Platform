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
import javafx.scene.control.TextField;

public class FindCategoryView implements Initializable {

    @FXML
    private TextField categoryName;

    @FXML
    private Label showCategory;

    @FXML
    private Label messageError;

    public CategoryController controller;
    public FilePersistence<Category> filePersistence;
    private Type type;

    @FXML
    private void findCategory() throws IOException {

        if (categoryName.getText().isEmpty() || categoryName.getText().trim().isEmpty()) {
            messageError.setText("Error empty string, enter name");
            categoryName.clear();
        } else if (!categoryName.getText().matches("\\b[a-zA-Z]+(\\s+[a-zA-Z]+)*\\b")) {
            messageError.setText("Only words are accepted.");
            categoryName.clear();
        } else {
            Category category = controller.get(getId(categoryName.getText()));
            if (category != null) {
                showCategory.setWrapText(true);
                showCategory.setText(" The category existing " + category.getName());
                categoryName.clear();
                messageError.setText("");
            } else {
                showCategory.setWrapText(true);
                showCategory.setText("The category does not exist");
                categoryName.clear();
                messageError.setText("");
            }
        }

    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-categories");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        type = new TypeToken<ArrayList<Category>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "categories");
        controller = new CategoryController(filePersistence);
    }

    public int getId(String nameCategory) {
        Category category = controller.exitCategoryName(nameCategory);

        if (category == null) {
            return -1;
        } else {
            return category.getId();
        }
    }

}
