package co.edu.uptc.view.movies;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ObtainAllMovies implements Initializable {

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
    private TableColumn<Movie, Category> categoryColum;

    private MovieController controller;
    private CategoryController categoryController;
    private FilePersistence<Category> persistenceCategory;
    private FilePersistence<Movie> filePersistence;
    private Type type, type2;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        type = new TypeToken<ArrayList<Movie>>() {
        }.getType();

        type2 = new TypeToken<ArrayList<Category>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "movies");
        persistenceCategory = new FilePersistence<>(type2, "categories");
        categoryController = new CategoryController(persistenceCategory);
        controller = new MovieController(filePersistence, categoryController);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsispColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        categoryColum.setCellValueFactory(new PropertyValueFactory<>("categories"));
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
        Main.setRoot("menu-crud-movies");
    }

    @FXML
    private void obtainAll() throws IOException {
        tableView.getItems().addAll(controller.getAll());
    }
}
