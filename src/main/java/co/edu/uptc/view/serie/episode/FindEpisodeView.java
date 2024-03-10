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
import javafx.scene.control.TextField;

public class FindEpisodeView implements Initializable {

    @FXML
    private ComboBox<Serie> comboBox;

    @FXML
    private ComboBox<Season> comboBoxSeason;

    @FXML
    private TextField episodeNumber;

    @FXML
    private Label showSerie, showSeason, messageError, showEpisode;

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
    private void findEpisode() throws IOException {

        if (findSerie == null || findSeason == null) {
            viewSerie();
            return;
        }
        if (episodeNumber.getText().length() > 6) {
            messageError.setText(
                    "Please enter a valid episode number. Episode numbers must be numeric and can have up to 6 digits.");
            episodeNumber.clear();
        } else if (episodeNumber.getText().isEmpty() || episodeNumber.getText().trim().isEmpty()) {
            messageError.setText("Error empty string, enter name");
            episodeNumber.clear();
        } else if (!episodeNumber.getText().matches("[0-9]+")) {
            messageError.setText("Only number are accepted.");
            episodeNumber.clear();
        } else {
            Episode findEpisode = episodeController.get(getId(Integer.valueOf(episodeNumber.getText())),
                    findSeason.getId());
            if (findEpisode != null) {
                showEpisode.setWrapText(true);
                showEpisode.setText(" The episode existing " + findEpisode.getNumber());
                episodeNumber.clear();
                messageError.setText("");
            } else {
                showEpisode.setWrapText(true);
                showEpisode.setText("The episode does not exist");
                episodeNumber.clear();
                messageError.setText("");
            }
        }

    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-episode");
    }

    public void comboBoxEpisodeAction(ActionEvent event) {
        if (findSerie != null || findSeason != null) {
            findSeason = comboBoxSeason.getValue();
            findSerie = comboBox.getValue();
            showSeason();
            return;
        }
        findSerie = comboBox.getValue();

        if (findSerie != null) {
            List<Season> seasons = serieController.get(findSerie.getId()).getSeasons();
            comboBoxSeason.getItems().addAll(seasons);
            findSeason = comboBoxSeason.getValue();
            viewSerie();
        }

    }

    private void viewSerie() {
        if (findSerie == null) {
            messageError.setText("Error, select series to add episode");

        } else if (serieController.get(findSerie.getId()) == null) {
            messageError.setText(" The serie does not exisy");
        } else {
            showSerie.setWrapText(true);
            showSerie.setText("The serie " + findSerie.getTitle());
            messageError.setText("");
            showSeason();
        }

    }

    private void showSeason() {

        if (findSeason == null) {
            messageError.setText("Error, select season to add episode");
        } else if (serieController.get(findSerie.getId()) == null) {
            messageError.setText(" The season does not exisy");
        } else {
            showSeason.setWrapText(true);
            showSeason.setText("Season " + String.valueOf(findSeason.getNumber()));
            messageError.setText("");
        }
    }

    public int getId(int numberEpisode) {
        Episode episode = episodeController.existsEpisodeNumber(numberEpisode, findSeason.getId());

        if (episode == null) {
            return -1;
        } else {
            return episode.getId();
        }
    }

}
