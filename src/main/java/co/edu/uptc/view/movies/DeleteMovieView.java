package co.edu.uptc.view.movies;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;
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

public class DeleteMovieView implements Initializable {

    @FXML
    private Label messageError;

    @FXML
    private ComboBox<Movie> comoboBoxMovies;

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
    private TableView<Movie> tableView;

    @FXML
    private TableColumn<Movie, Category> categoryColum;

    private MovieController movieController;
    private FilePersistence<Movie> filePersistence;
    private CategoryController categoryController;
    private FilePersistence<Category> persistenceCategory;

    private Movie movie;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Type type = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        this.filePersistence = new FilePersistence<>(type, "movies");
        persistenceCategory = new FilePersistence<>(type2, "categories");
        categoryController = new CategoryController(persistenceCategory);
        this.movieController = new MovieController(filePersistence, categoryController);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsispColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        categoryColum.setCellValueFactory(new PropertyValueFactory<>("categories"));

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        comoboBoxMovies.getItems().addAll(movieController.getAll());
        loadItems();
        comoboBoxMovies.setOnAction(this::handleComboBoxAction);
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-movies");
    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(movieController.getAll());
    }

    public void deleteMovie() {

        if (movie == null) {
            messageError.setText("select a movie");
        } else {
            messageError.setText("");
        }
        movieController.delete(movie.getId());

        loadItems();

    }

    public void handleComboBoxAction(ActionEvent event) {
        movie = comoboBoxMovies.getValue();

    }
}
