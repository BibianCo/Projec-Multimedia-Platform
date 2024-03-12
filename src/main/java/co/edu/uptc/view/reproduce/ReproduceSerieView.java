package co.edu.uptc.view.reproduce;

import java.io.File;
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
import co.edu.uptc.model.Movie;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class ReproduceSerieView implements Initializable {
    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button reseButton;

    @FXML
    private Label messageError;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        try {
            file = new File(
                    "C:\\Users\\juan3\\Documents\\Project-Multimedia-Platform\\src\\main\\resources\\co\\edu\\uptc\\images\\video .mp4");
            // Crear un objeto Media con la ruta del archivo multimedia
        } catch (Exception e) {
            e.printStackTrace();
        }

        media = new Media((file).toURI().toString());

        // Crear un objeto MediaPlayer con la media
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);

    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("users-main");
    }

    private void playMovie(Movie movie) {
        // Media media = new Media(new File(movie.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    public void playMedia() {
        mediaPlayer.play();
    }

    public void pauseMedia() {
        mediaPlayer.pause();
    }

    public void resetMedia() {
        mediaPlayer.stop();
        mediaPlayer.seek(Duration.ZERO);
    }

    public void advance10Seconds() {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(10)));
    }

    public void rewind10Seconds() {
        mediaPlayer.seek(mediaPlayer.getCurrentTime().subtract(Duration.seconds(10)));
    }

    private List<Episode> episodes;
    private int currentEpisodeIndex;

    public List<Episode> getEpisodesBySeasonAndSerie(Season season) {
        List<Episode> allEpisodes = season.getEpisodes();

        return allEpisodes;
    }

    public void playNextEpisode() {
        if (episodes != null && currentEpisodeIndex < episodes.size() - 1) {
            currentEpisodeIndex++;
            Episode nextEpisode = episodes.get(currentEpisodeIndex);
            playMovie(nextEpisode);
        } else {
            messageError.setText("no episodes available");
        }
    }

    public void playPreviousEpisode() {
        if (episodes != null && currentEpisodeIndex > 0) {
            currentEpisodeIndex--;
            Episode previousEpisode = episodes.get(currentEpisodeIndex);
            playMovie(previousEpisode);
        } else {
            messageError.setText("no episodes available");
        }
    }

    private void playMovie(Episode episode) {
        // Media media = new Media(new File(episode.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaView.setMediaPlayer(mediaPlayer);
    }
}
