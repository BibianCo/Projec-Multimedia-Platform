package co.edu.uptc.view.series;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateSeriesView implements Initializable {
    @FXML
    private TextField serieTitle;

    @FXML
    private TextField serieSynopsis;

    @FXML
    private DatePicker releaseDate;

    @FXML
    private TableView<Serie> tableView;

    @FXML
    private TableColumn<Serie, Integer> idColumn;

    @FXML
    private TableColumn<Serie, String> titleColumn;

    @FXML
    private TableColumn<Serie, String> synopsisColumn;

    @FXML
    private TableColumn<Serie, LocalDate> dateColumn;

    @FXML
    private Label messageError;

    @FXML
    private TableColumn<Serie, String> categoryColumn;

    @FXML
    private ComboBox<Category> serieCategoriesComboBox;

    public SerieController controller;
    public FilePersistence<Serie> filePersistence;
    private Type type;
    private Serie serie;

    @Override

    public void initialize(URL arg0, ResourceBundle arg1) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsisColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        type = new TypeToken<ArrayList<Category>>() {
        }.getType();

        FilePersistence<Category> categoryFilePersistence = new FilePersistence<>(type, "categories");
        CategoryController categoryController = new CategoryController(categoryFilePersistence);
        ArrayList<Category> categories = categoryController.getAll();
        serieCategoriesComboBox.getItems().addAll(categories);

        type = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "series");
        controller = new SerieController(filePersistence, categoryController);
        filePersistence.createFile();
        loadItems();
    }

    @FXML
    private void createSerie() throws IOException {
        if (serieTitle.getText().isEmpty() || serieTitle.getText().trim().isEmpty() ||
                serieSynopsis.getText().isEmpty() || serieSynopsis.getText().trim().isEmpty() ||
                releaseDate.getValue() == null || serieCategoriesComboBox.getValue() == null) {
            messageError.setText("Error: empty fields, please fill all information");
        } else {
            LocalDate selectedReleaseDate = releaseDate.getValue();
            Category selectedCategory = serieCategoriesComboBox.getValue();
            serie = new Serie(setId(), serieTitle.getText(), serieSynopsis.getText(), selectedReleaseDate,
                    new ArrayList<>(List.of(selectedCategory)), new ArrayList<>());
            if (controller.add(serie)) {
                serieTitle.clear();
                serieSynopsis.clear();
                releaseDate.setValue(null);
                serieCategoriesComboBox.setValue(null);
                messageError.setText("");
                loadItems();
            } else {
                messageError.setText("Error: failed to create serie");
            }
        }
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-series");
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
