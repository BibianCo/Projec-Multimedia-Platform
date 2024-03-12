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

public class UpdateMovieView implements Initializable {
    @FXML
    private TextField movieName;

    @FXML
    private TextField synopsisp;

    @FXML
    private TextField duration;

    @FXML
    private DatePicker date;

    @FXML
    private Button button;

    @FXML
    private TableView<Movie> tableView;

    @FXML
    private Label messageName, messageSynopsis, messageDuration, messageDate, messageCategory, messageMovies;

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

    @FXML
    private ComboBox<Category> comboBoxCategories;

    @FXML
    private ComboBox<Movie> comoboBox;

    private MovieController controller;
    private CategoryController categoryController;
    private FilePersistence<Category> persistenceCategory;
    private FilePersistence<Movie> filePersistence;
    private Type type, type2;
    private Movie currentMovie;
    private Category category;
    ArrayList<Category> categories = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        type = new TypeToken<ArrayList<Movie>>() {
        }.getType();

        type2 = new TypeToken<ArrayList<Category>>() {
        }.getType();
        filePersistence = new FilePersistence<>(type, "movies");
        persistenceCategory = new FilePersistence<>(type2, "categories");
        categoryController = new CategoryController(persistenceCategory);
        controller = new MovieController(filePersistence, categoryController);
        filePersistence.createFile();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsispColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        categoryColum.setCellValueFactory(new PropertyValueFactory<>("categories"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        date.setEditable(false);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        comboBoxCategories.getItems().addAll(categoryController.getAll());
        comoboBox.getItems().addAll(controller.getAll());
        loadItems();

        comboBoxCategories.setOnAction(this::handleComboBoxAction);
        comoboBox.setOnAction(this::handleComboBoxActionMovie);
    }

    public void handleComboBoxAction(ActionEvent event) {
        category = comboBoxCategories.getValue();

        if (category != null) {
            categories.add(category);
        }

    }

    public void handleComboBoxActionMovie(ActionEvent event) {
        currentMovie = comoboBox.getValue();
    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(controller.getAll());
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-movies");
    }

    @FXML
    private void updateMovie() throws IOException {

        if (currentMovie == null) {
            messageMovies.setText("Error, select movie to update\"");
            clearMessage();
        }
        if (movieName.getText().trim().isEmpty() || synopsisp.getText().trim().isEmpty()
                || duration.getText().trim().isEmpty()
                || date.getValue() == null) {
            if (movieName.getText().isEmpty()) {
                messageName.setText("Movie name is required");
            } else {
                messageName.setText("");
            }
            if (synopsisp.getText().isEmpty()) {
                messageSynopsis.setText("Synopsis is required");
            } else {
                messageSynopsis.setText("");
            }
            messageDuration.setText(duration.getText().isEmpty() ? "Duration is required" : "");
            messageDate.setText(date.getValue() == null ? "Date is required" : "");
        } else if (!duration.getText().trim().matches("\\d+")) {
            messageDuration.setText("Only numbers are accepted");
            messageSynopsis.setText("");
            messageName.setText("");
            messageDate.setText("");
        } else if (!synopsisp.getText().matches("^[a-zA-Z ]+$")) {
            messageSynopsis.setText("Only letters are accepted");
            messageName.setText("");
            messageDate.setText("");
            messageDuration.setText("");

        } else {
            clearMessage();

            Movie movie = new Movie(currentMovie.getId(), movieName.getText(), synopsisp.getText(), date.getValue(),
                    Integer.parseInt(duration.getText()), categories);

            if (controller.update(currentMovie.getId(), movie)) {
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
        movieName.clear();
        synopsisp.clear();
        duration.clear();
        this.date.setValue(null);
    }

    public void clearMessage() {
        messageDuration.setText("");
        messageSynopsis.setText("");
        messageName.setText("");
        messageDate.setText("");

    }
}
