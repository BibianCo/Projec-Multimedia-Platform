package co.edu.uptc.view.users;

import java.lang.reflect.Type;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.ListReproduceController;
import co.edu.uptc.controller.MovieController;
import co.edu.uptc.controller.PlanController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.controller.SubscriptionController;
import co.edu.uptc.controller.UserController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Serie;
import co.edu.uptc.model.Subscription;
import co.edu.uptc.model.User;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListReproduceView implements Initializable {
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
    private Button saveButton;

    public ListReproduceController listReproduceController;
    public SerieController serieController;
    public MovieController movieController;
    public CategoryController categoryController;
    public UserController userController;
    public FilePersistence<Movie> impm;
    public FilePersistence<Serie> imps;
    public FilePersistence<Category> impc;
    public FilePersistence<User> impu;
    public SubscriptionController subscriptionController;
    public FilePersistence<Subscription> impsb;
    public PlanController planController;
    public FilePersistence<Plan> impp;
    public Type movieType;
    public Type categoryType;
    public Type serieType;
    public Type userType;
    public Type subscriptionType;
    public Type planType;

    private int YOUR_USER_ID;
    private Movie selectedMovie;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        movieType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        categoryType = new TypeToken<ArrayList<Category>>() {
        }.getType();
        serieType = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        userType = new TypeToken<ArrayList<User>>() {
        }.getType();
        subscriptionType = new TypeToken<ArrayList<Subscription>>() {
        }.getType();
        planType = new TypeToken<ArrayList<Plan>>() {
        }.getType();

        impm = new FilePersistence<>(movieType, "movies");
        impc = new FilePersistence<>(categoryType, "categories");
        imps = new FilePersistence<>(serieType, "series");
        impu = new FilePersistence<>(userType, "users");
        impsb = new FilePersistence<>(subscriptionType, "subscription");
        impp = new FilePersistence<>(planType, "plans");
        planController = new PlanController(impp);
        subscriptionController = new SubscriptionController(impsb, planController);
        categoryController = new CategoryController(impc);
        serieController = new SerieController(imps, categoryController);
        movieController = new MovieController(impm, categoryController);
        userController = new UserController(impu, subscriptionController);
        listReproduceController = new ListReproduceController(movieController, serieController,
                userController);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        synopsispColumn.setCellValueFactory(new PropertyValueFactory<>("synopsis"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        loadItems();

    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(movieController.getAll());
    }

    @FXML
    private void saveMovie() {
        selectedMovie = tableView.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            // Aquí implementa la lógica para guardar la película seleccionada
            System.out.println("Película guardada: " + selectedMovie.getTitle());
        }
    }

}
