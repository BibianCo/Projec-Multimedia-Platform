package co.edu.uptc.view.movies;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.MovieController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindMovieViewUser implements Initializable {

    @FXML
    private TextField movieName;

    @FXML
    private Label showMovie;

    @FXML
    private Label messageError;
    private MovieController movieController;
    private FilePersistence<Movie> filePersistence;
    private CategoryController categoryController;
    private FilePersistence<Category> persistenceCategory;
    private Movie movie;

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("users-main");
    }

    @FXML
    private void reproduce() throws IOException {
        if (movie != null) {
            Main.setRoot("reproduce");
        } else {
            messageError.setText("The movie does not exist");
        }

    }

    public void initialize(URL arg0, ResourceBundle arg1) {
        Type type = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        this.filePersistence = new FilePersistence<>(type, "movies");
        persistenceCategory = new FilePersistence<>(type2, "categories");
        categoryController = new CategoryController(persistenceCategory);
        this.movieController = new MovieController(filePersistence, categoryController);
    }

    @FXML
    private void findMovie() throws IOException {

        if (movieName.getText().isEmpty() || movieName.getText().trim().isEmpty()) {
            messageError.setText("Error empty string, enter name");
            movieName.clear();
        } else if (!movieName.getText().matches("[a-zA-Z]+")) {
            messageError.setText("Only words are accepted.");
            movieName.clear();
        } else {
            movie = movieController.get(existeMovie(movieName.getText()));
            if (movie != null) {
                showMovie.setWrapText(true);
                showMovie.setText("Movie Information:\n" +
                        "Name: " + movie.getTitle() + "\n" +
                        "Duration: " + movie.getDuration() + " minutes");
                movieName.clear();
                messageError.setText("");

            } else {
                showMovie.setWrapText(true);
                showMovie.setText("The movie does not exist");
                movieName.clear();
                messageError.setText("");
            }

        }

    }

    public int existeMovie(String nameMovie) {

        for (Movie movie : movieController.getAll()) {
            if (movie.getTitle().trim().equalsIgnoreCase(nameMovie.trim())) {
                return movie.getId();

            }
        }
        return -1;
    }

}