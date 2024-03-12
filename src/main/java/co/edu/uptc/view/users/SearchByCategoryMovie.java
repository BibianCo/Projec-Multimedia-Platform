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
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.persistence.FilePersistence;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchByCategoryMovie implements Initializable {

    @FXML
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, Integer> idColumn;

    @FXML
    private TableColumn<Movie, String> nameColumn;

    @FXML
    private TableColumn<Movie, String> synopsispColumn;

    @FXML
    private TableColumn<Movie, Integer> durationColumn;

    @FXML
    private TableColumn<Movie, LocalDate> dateColumn;

    @FXML
    private ComboBox<Category> comoboBoxMovies;

    @FXML
    private TableColumn<Movie, Category> categoryColum;

    @FXML
    private ComboBox<Category> comboBox;

    @FXML
    private Label messageError;

    private MovieController movieController;
    public CategoryController controller;
    public FilePersistence<Category> fpc;
    public FilePersistence<Movie> fpm;
    private Type typeCategory;
    private Type typeMovie;
    private Category findCategory;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        typeCategory = new TypeToken<ArrayList<Category>>() {
        }.getType();
        typeMovie = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        fpm = new FilePersistence<>(typeMovie, "movies");
        fpc = new FilePersistence<>(typeCategory, "categories");
        controller = new CategoryController(fpc);
        movieController = new MovieController(fpm, controller);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsispColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
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
        if (movieController.groupByCategory(findCategory.getId()) == null) {
            messageError.setText("There are no movies with that category " + findCategory.getName());
            tableView.getItems().setAll(new ArrayList<>());
        } else {
            tableView.getItems().setAll(new ArrayList<>());
            tableView.getItems().addAll(movieController.groupByCategory(findCategory.getId()));
            messageError.setText("");
        }
    }

}
