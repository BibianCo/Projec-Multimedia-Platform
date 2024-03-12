package co.edu.uptc.view.series;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.Main;
import co.edu.uptc.controller.CategoryController;
import co.edu.uptc.controller.SerieController;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindSerieViewUsers implements Initializable {

    @FXML
    private TextField serieTitle;

    @FXML
    private Label showSerie;

    @FXML
    private Label messageError;

    private SerieController serieController;
    private FilePersistence<Serie> filePersistence;
    private CategoryController categoryController;
    private FilePersistence<Category> categoryPersistence;

    private Serie serie;

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("users-main");
    }

    @FXML
    private void reproduce() throws IOException {
        if (serie != null) {
            Main.setRoot("reproduce-serie");
        } else {
            messageError.setText("The serie does not exist");
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Type serieType = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        Type categoryType = new TypeToken<ArrayList<Category>>() {
        }.getType();

        this.filePersistence = new FilePersistence<>(serieType, "serie");
        this.categoryPersistence = new FilePersistence<>(categoryType, "categories");

        this.categoryController = new CategoryController(categoryPersistence);
        this.serieController = new SerieController(filePersistence, categoryController);
    }

    @FXML
    private void findSerie() throws IOException {
        if (serieTitle.getText().isEmpty() || serieTitle.getText().trim().isEmpty()) {
            messageError.setText("Error: el campo está vacío, ingrese un título");
            serieTitle.clear();
        } else {
            serie = serieController.get(existsSerie(serieTitle.getText()));
            if (serie != null) {
                showSerie.setWrapText(true);
                showSerie.setText("Información de la serie:\n" +
                        "Título: " + serie.getTitle() + "\n" +
                        "Sinopsis: " + serie.getSynopsis());
                serieTitle.clear();
                messageError.setText("");
            } else {
                showSerie.setWrapText(true);
                showSerie.setText("La serie no existe");
                serieTitle.clear();
                messageError.setText("");
            }
        }
    }

    public int existsSerie(String title) {
        for (Serie serie : serieController.getAll()) {
            if (serie.getTitle().trim().equalsIgnoreCase(title.trim())) {
                return serie.getId();
            }
        }
        return -1;
    }
}