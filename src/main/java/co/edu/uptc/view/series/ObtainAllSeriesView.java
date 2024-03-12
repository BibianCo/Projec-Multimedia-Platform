package co.edu.uptc.view.series;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.google.gson.reflect.TypeToken;
import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

public class ObtainAllSeriesView {

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
    private Type type, type2;

    public void initialize(URL arg0, ResourceBundle arg1) {

        type = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        type2 = new TypeToken<ArrayList<Category>>() {
        }.getType();

        FilePersistence<Category> categoryFilePersistence = new FilePersistence<>(type2, "categories");
        CategoryController categoryController = new CategoryController(categoryFilePersistence);
        filePersistence = new FilePersistence<>(type, "series");
        controller = new SerieController(filePersistence, categoryController);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsisColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        try {
            obtainAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-series");
    }

    private void obtainAll() throws IOException {
        tableView.getItems().addAll(controller.getAll());
    }

}
