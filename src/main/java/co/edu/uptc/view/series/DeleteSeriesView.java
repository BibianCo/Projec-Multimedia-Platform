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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteSeriesView implements Initializable {

    @FXML
    private Label messageError;

    @FXML
    private ComboBox<Serie> comboBoxSeries;

    @FXML
    private TableColumn<Serie, Integer> idColumn;

    @FXML
    private TableColumn<Serie, String> titleColumn;

    @FXML
    private TableColumn<Serie, String> synopsisColumn;

    @FXML
    private TableColumn<Serie, LocalDate> dateColumn;

    @FXML
    private TableColumn<Serie, String> categoryColumn;

    @FXML
    private TableView<Serie> tableView;

    private SerieController serieController;

    private FilePersistence<Serie> filePersistence;

    private CategoryController categoryController;

    private FilePersistence<Category> persistenceCategory;

    public SerieController controller;

    private Serie serie;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Type type = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Category>>() {
        }.getType();

        this.filePersistence = new FilePersistence<>(type, "series");
        persistenceCategory = new FilePersistence<>(type2, "categories");
        categoryController = new CategoryController(persistenceCategory);
        this.serieController = new SerieController(filePersistence, categoryController);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsisColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Cargar datos iniciales en la tabla y el ComboBox
        tableView.getItems().addAll(serieController.getAll());
        loadItems();
        comboBoxSeries.getItems().addAll(serieController.getAll());

    }

    @FXML
    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(serieController.getAll());
    }

    @FXML
    private void deleteSerie() {

        if (serie == null) {
            messageError.setText("Select a series");
        } else {
            messageError.setText("");
            int selectedId = serie.getId();

            if (serieController.delete(selectedId)) {

                comboBoxSeries.getItems().clear();
                comboBoxSeries.getItems().addAll(serieController.getAll());
                comboBoxSeries.getSelectionModel().clearSelection();
                loadItems();
            } else {
                messageError.setText("Error: Could not delete series");
            }
        }
    }

    @FXML
    private void handleComboBoxAction(ActionEvent event) {
        serie = comboBoxSeries.getValue();
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-series");

    }
}