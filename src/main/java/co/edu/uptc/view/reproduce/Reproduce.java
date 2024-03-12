package co.edu.uptc.view.reproduce;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uptc.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Reproduce implements Initializable {

    @FXML
    private MediaView mediaView;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button reseButton;

    private File file;
    private Media media;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            file = new File(
                    "C:\\Users\\juan3\\Documents\\Project-Multimedia-Platform\\src\\main\\resources\\co\\edu\\uptc\\images\\video.mp4");
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
}
