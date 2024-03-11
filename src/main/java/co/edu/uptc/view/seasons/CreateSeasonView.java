package co.edu.uptc.view.seasons;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.SeasonController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
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

public class CreateSeasonView implements Initializable {

    @FXML
    private ComboBox<Serie> comoboBoxMovies;

    @FXML
    private Label messageError, showMovie;

    @FXML
    private TableView<Season> tableView;

    @FXML
    private TableColumn<Season, Integer> idColumn;

    @FXML
    private TableColumn<Season, Integer> numberColumn;

    @FXML
    private TableColumn<Season, Integer> idSerieColumn;

    @FXML
    private TableColumn<Season, Episode> episodesColumn;

    private SeasonController seasonController;
    private FilePersistence<Season> filePersistence;
    private SerieController serieController;
    private FilePersistence<Serie> filePersistenceSerie;
    private CategoryController categoryController;
    private FilePersistence<Category> filePersistenceCategory;

    private Serie serie;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        Type type = new TypeToken<ArrayList<Season>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        Type type3 = new TypeToken<ArrayList<Category>>() {
        }.getType();
        this.filePersistenceCategory = new FilePersistence<>(type3, "categories");
        this.categoryController = new CategoryController(filePersistenceCategory);
        this.filePersistenceSerie = new FilePersistence<>(type2, "serie");
        this.serieController = new SerieController(filePersistenceSerie, categoryController);
        this.filePersistence = new FilePersistence<>(type, "season");
        this.seasonController = new SeasonController(filePersistence, serieController);

        filePersistenceCategory.createFile();
        filePersistenceSerie.createFile();
        filePersistence.createFile();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        idSerieColumn.setCellValueFactory(new PropertyValueFactory<>("idSerie"));
        episodesColumn.setCellValueFactory(new PropertyValueFactory<>("episodes"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        comoboBoxMovies.getItems().addAll(serieController.getAll());
        comoboBoxMovies.setOnAction(this::handleComboBoxAction);
        loadItems();

    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(seasonController.getAll());
    }

    public void handleComboBoxAction(ActionEvent event) {
        serie = comoboBoxMovies.getValue();

        if (serie == null) {
            messageError.setText("it is necessary to have a series");
        }
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-season");
    }

    @FXML
    private void createSeason() throws IOException {

        if (serie == null) {
            messageError.setText("series is required");

        } else {
            showMovie.setWrapText(true);
            showMovie.setText(serie.getTitle());
            Season season = new Season(setId(), setId(), serie.getId());
            showMovie.setWrapText(true);
            showMovie.setText("Movie Information:\n" +
                    "Name: " + serie.getTitle() + "\n" +
                    "Synopsis: " + serie.getSynopsis());

            if (season != null) {
                seasonController.add(season);
                comoboBoxMovies.getItems().clear();
                comoboBoxMovies.getItems().addAll(serieController.getAll());
                loadItems();
                Main.setRoot("create-episode");
            }
        }
    }

    public int setId() {
        if (seasonController.getAll().isEmpty()) {
            return 1;
        } else {
            return seasonController.getAll().get(seasonController.getAll().size() - 1).getId() + 1;
        }

    }

}