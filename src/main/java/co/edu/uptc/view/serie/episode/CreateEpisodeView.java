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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CreateEpisodeView implements Initializable {

    @FXML
    private ComboBox<Serie> comboBox;
    @FXML
    private ComboBox<Season> comboBoxSeason;

    @FXML
    private Label showSerie, showSeason, messageError;

    @FXML
    private TextField episodeDuration;
    @FXML
    private TableView<Episode> tableView;

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
        fpsn = new FilePersistence<>(typeSeason, "season");
        fpse = new FilePersistence<>(typeSerie, "serie");
        fpcy = new FilePersistence<>(typeCategory, "categories");

        categoryController = new CategoryController(fpcy);
        serieController = new SerieController(fpse, categoryController);
        seasonController = new SeasonController(fpsn, serieController);
        episodeController = new EpisodeController(filePersistence, seasonController);

        filePersistence.createFile();

        comboBox.getItems().addAll(serieController.getAll());
        comboBox.setOnAction(this::comboBoxEpisodeAction);
        comboBoxSeason.setOnAction(this::comboBoxEpisodeAction);

    }

    @FXML
    private void createEpisode() throws IOException {

        if (findSerie == null || findSeason == null) {
            viewSerie();
            return;
        }
        if (episodeDuration.getText().length() > 6) {
            messageError.setText(
                    "Enter a valid episode duration. The episode duration must be numerical and can be up to 6 digits.");
            episodeDuration.clear();
        } else if (episodeDuration.getText().isEmpty() || episodeDuration.getText().trim().isEmpty()) {
            messageError.setText("Error empty string, enter number");

        } else if (episodeDuration.getText().matches("[0-9]+")) {
            Episode episode = new Episode(setId(), numberEpisode(), Integer.valueOf(episodeDuration.getText()),
                    findSeason.getId());
            if (episodeController.add(episode)) {

                loadItems();
                messageError.setText("");

            }
        } else {
            messageError.setText("Only numbers are accepted.");

        }
        episodeDuration.clear();
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
            loadItems();
            messageError.setText("");

        }
    }

    public void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(seasonController.get(findSeason.getId()).getEpisodes());
    }

    public int numberEpisode() {
        ArrayList<Episode> episodes = seasonController.get(findSeason.getId()).getEpisodes();
        if (episodes == null || episodes.isEmpty()) {
            return 1;
        }
        return episodes.size() + 1;
    }

    public int setId() {
        if (episodeController.getAll(findSeason.getId()).isEmpty()) {
            return 1;
        } else {
            return episodeController.getAll(findSeason.getId())
                    .get(episodeController.getAll(findSeason.getId()).size() - 1).getId() + 1;
        }

    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-episode");
    }

    public void comboBoxEpisodeAction(ActionEvent event) {
        findSerie = comboBox.getValue();
        findSeason = comboBoxSeason.getValue();

        // Limpiar el ComboBox de temporadas antes de agregar nuevas temporadas
        comboBoxSeason.getItems().clear();

        if (findSerie != null) {
            // Obtener las temporadas asociadas a la serie seleccionada
            List<Season> seasons = serieController.get(findSerie.getId()).getSeasons();
            comboBoxSeason.getItems().addAll(seasons);
        }

        // Mostrar la serie y la temporada seleccionada
        viewSerie();
        showSeason();
    }

}
