package co.edu.uptc.view.serie.episode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.EpisodeController;
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

public class ObtainAllEpisodeView implements Initializable {

    @FXML
    private ComboBox<Serie> comboBox;

    @FXML
    private ComboBox<Season> comboBoxSeason;

    @FXML
    private TableView<Episode> tableView;

    @FXML
    private Label messageError;

    @FXML
    private TableColumn<Episode, Integer> idColumn, numberColumn, durationColumn;

    private EpisodeController episodeController;
    private SeasonController seasonController;
    private SerieController serieController;
    private CategoryController categoryController;
    private FilePersistence<Episode> filePersistence;
    private FilePersistence<Season> fpsn;
    private FilePersistence<Serie> fpse;
    private FilePersistence<Category> fpcy;
    private Type typeEpisode;
    private Type typeSeason;
    private Type typeSerie;
    private Type typeCategory;

    private Serie findSerie;
    private Season findSeason;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("Duration"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        typeEpisode = new TypeToken<ArrayList<Episode>>() {
        }.getType();
        typeSeason = new TypeToken<ArrayList<Season>>() {
        }.getType();
        typeSerie = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        typeCategory = new TypeToken<ArrayList<Serie>>() {
        }.getType();

        filePersistence = new FilePersistence<>(typeEpisode, "episodes");
        fpsn = new FilePersistence<>(typeSeason, "seasons");
        fpse = new FilePersistence<>(typeSerie, "series");
        fpcy = new FilePersistence<>(typeCategory, "categories");

        categoryController = new CategoryController(fpcy);
        serieController = new SerieController(fpse, categoryController);
        seasonController = new SeasonController(fpsn, serieController);
        episodeController = new EpisodeController(filePersistence, seasonController);

        comboBox.getItems().addAll(serieController.getAll());
        comboBox.setOnAction(this::comboBoxEpisodeAction);
        comboBoxSeason.setOnAction(this::comboBoxEpisodeAction);

    }

    @FXML
    private void obtainAllEpisode() throws IOException {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(episodeController.getAll(findSeason.getId()));
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-episode");
    }

    private void viewSerie() {
        if (findSerie == null) {
            messageError.setText("Error, select series to add episode");
        } else if (findSeason == null) {
            messageError.setText("Error, select season to add episode");
        } else {
            try {
                obtainAllEpisode();
                messageError.setText("");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(seasonController.get(findSeason.getId()).getEpisodes());
    }

    public void comboBoxEpisodeAction(ActionEvent event) {
        if (findSerie != null || findSeason != null) {
            findSeason = comboBoxSeason.getValue();
            findSerie = comboBox.getValue();
            loadItems();
            viewSerie();
            return;
        } else {
            viewSerie();
        }
        findSerie = comboBox.getValue();

        if (findSerie != null) {
            List<Season> seasons = serieController.get(findSerie.getId()).getSeasons();
            comboBoxSeason.getItems().addAll(seasons);
            findSeason = comboBoxSeason.getValue();
            if (findSeason != null) {
                viewSerie();
            }

        }

    }

}
