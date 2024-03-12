package co.edu.uptc.view.users;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.MovieController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
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

public class SearchByCategorySerie implements Initializable {
    @FXML
    private TableView<Serie> tableView;

    @FXML
    private TableColumn<Serie, Integer> idColumn;

    @FXML
    private TableColumn<Serie, String> nameColumn;

    @FXML
    private TableColumn<Serie, String> synopsispColumn;

    @FXML
    private TableColumn<Serie, LocalDate> dateColumn;

    @FXML
    private ComboBox<Category> comoboBoxMovies;

    @FXML
    private TableColumn<Serie, Category> categoryColum;

    @FXML
    private ComboBox<Category> comboBox;

    @FXML
    private Label messageError;

    private SerieController serieController;
    public CategoryController controller;
    public FilePersistence<Category> fpc;
    public FilePersistence<Serie> fps;
    private Type typeCategory;
    private Type typeSerie;
    private Category findCategory;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        typeCategory = new TypeToken<ArrayList<Category>>() {
        }.getType();
        typeSerie = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        fps = new FilePersistence<>(typeSerie, "series");
        fpc = new FilePersistence<>(typeCategory, "categories");
        controller = new CategoryController(fpc);
        serieController = new SerieController(fps, controller);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsispColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        categoryColum.setCellValueFactory(new PropertyValueFactory<>("categories"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        comboBox.getItems().addAll(controller.getAll());
        comboBox.setOnAction(this::comboBoxCategoryAction);
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-categories");
    }

    public void comboBoxCategoryAction(ActionEvent event) {
        findCategory = comboBox.getValue();
        comboBox.getEditor().clear();
        showMovies();

    }

    public void showMovies() {
        if (findCategory != null) {
            validateArrays();
        } else {
            messageError.setText("Error, select category to delete");
        }
    }

    public void validateArrays() {
        if (serieController.groupByCategory(findCategory.getId()) == null) {
            messageError.setText("There are no series with that category " + findCategory.getName());
            tableView.getItems().setAll(new ArrayList<>());
        } else {
            tableView.getItems().setAll(new ArrayList<>());
            tableView.getItems().addAll(serieController.groupByCategory(findCategory.getId()));
            messageError.setText("");
        }
    }

}
