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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpdateSeriesView implements Initializable {
    @FXML
    private TextField seriesName;

    @FXML
    private TextField synopsis;

    @FXML
    private TextField seasons;

    @FXML
    private DatePicker date;

    @FXML
    private Button button;

    @FXML
    private TableView<Serie> tableView;

    @FXML
    private Label messageName, messageSynopsis, messageSeasons, messageDate, messageCategory, messageSeries;

    @FXML
    private TableColumn<Serie, Integer> idColumn;

    @FXML
    private TableColumn<Serie, String> titleColumn;

    @FXML
    private TableColumn<Serie, String> synopsisColumn;

    @FXML
    private TableColumn<Serie, Integer> seasonsColumn;

    @FXML
    private TableColumn<Serie, LocalDate> dateColumn;

    @FXML
    private TableColumn<Serie, Category> categoryColumn;

    @FXML
    private ComboBox<Category> comboBoxCategories;

    @FXML
    private ComboBox<Serie> comoboBox;

    private SerieController controller;
    private CategoryController categoryController;
    private FilePersistence<Category> persistenceCategory;
    private FilePersistence<Serie> filePersistence;
    private Type type, type2;
    private Serie currentSeries;
    private Category category;
    ArrayList<Category> categories = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        type = new TypeToken<ArrayList<Serie>>() {}.getType();

        type2 = new TypeToken<ArrayList<Category>>() {}.getType();

        filePersistence = new FilePersistence<>(type, "series");
        persistenceCategory = new FilePersistence<>(type2, "categories");
        categoryController = new CategoryController(persistenceCategory);
        controller = new SerieController(filePersistence, categoryController);
        filePersistence.createFile();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsisColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        // seasonsColumn.setCellValueFactory(new PropertyValueFactory<>("seasons"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        date.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        comboBoxCategories.getItems().addAll(categoryController.getAll());
        comoboBox.getItems().addAll(controller.getAll());
        loadItems();

        comboBoxCategories.setOnAction(this::handleComboBoxAction);
        comoboBox.setOnAction(this::handleComboBoxActionSeries);
    }

    public void handleComboBoxAction(ActionEvent event) {
        category = comboBoxCategories.getValue();

        if (category != null) {
            categories.add(category);
        }

    }

    public void handleComboBoxActionSeries(ActionEvent event) {
        currentSeries = comoboBox.getValue();
    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(controller.getAll());
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-series");
    }

    @FXML
    private void updateSeries() throws IOException {
    
        if (currentSeries == null) {
            messageSeries.setText("Error, select series to update\"");
            clearMessage();
        }
        if (seriesName.getText().trim().isEmpty() || synopsis.getText().trim().isEmpty()
                || date.getValue() == null) {
            if (seriesName.getText().isEmpty()) {
                messageName.setText("Series name is required");
            } else {
                messageName.setText("");
            }
            if (synopsis.getText().isEmpty()) {
                messageSynopsis.setText("Synopsis is required");
            } else {
                messageSynopsis.setText("");
            }
            messageDate.setText(date.getValue() == null ? "Date is required" : "");
        } else if (!synopsis.getText().matches("^[a-zA-Z ]+$")) {
            messageSynopsis.setText("Only letters are accepted");
            messageName.setText("");
            messageDate.setText("");
        } else {
            clearMessage();
    
            Serie serie = new Serie(currentSeries.getId(), seriesName.getText(), synopsis.getText(), date.getValue());
    
            if (controller.update(currentSeries.getId(), serie)) {
                comoboBox.getItems().clear();
                comoboBox.getItems().addAll(controller.getAll());
                comboBoxCategories.getItems().clear();
                comboBoxCategories.getItems().addAll(categoryController.getAll());
    
                clearFields();
                clearMessage();
                loadItems();
            }
        }
    }

    public int setId() {
        if (controller.getAll().isEmpty()) {
            return 1;
        } else {
            return controller.getAll().get(controller.getAll().size() - 1).getId() + 1;
        }

    }

    public void clearFields() {
        seriesName.clear();
        synopsis.clear();
        seasons.clear();
        this.date.setValue(null);
    }

    public void clearMessage() {
        messageSeasons.setText("");
        messageSynopsis.setText("");
        messageName.setText("");
        messageDate.setText("");

    }
}

