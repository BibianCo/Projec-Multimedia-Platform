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
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FindSeasonView implements Initializable {

    @FXML
    private Label messageError, showCategory;

    @FXML
    private TextField categoryName;

    private SeasonController seasonController;
    private FilePersistence<Season> filePersistence;
    private SerieController serieController;
    private FilePersistence<Serie> filePersistenceSerie;
    private CategoryController categoryController;
    private FilePersistence<Category> filePersistenceCategory;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Type type = new TypeToken<ArrayList<Season>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        Type type3 = new TypeToken<ArrayList<Category>>() {
        }.getType();
        this.filePersistenceCategory = new FilePersistence<>(type3, "categories");
        this.categoryController = new CategoryController(filePersistenceCategory);
        this.filePersistenceSerie = new FilePersistence<>(type2, "series");
        this.serieController = new SerieController(filePersistenceSerie, categoryController);
        this.filePersistence = new FilePersistence<>(type, "seasons");
        this.seasonController = new SeasonController(filePersistence, serieController);

        filePersistenceCategory.createFile();
        filePersistenceSerie.createFile();
        filePersistence.createFile();

    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-season");
    }

    @FXML
    private void findSeason() throws IOException {

        if (categoryName.getText().trim().isEmpty()) {
            messageError.setText("Error empty string, enter number");
        } else if (!categoryName.getText().matches("\\d+")) {
            messageError.setText("Only numers are accepted.");
        } else {
            Season season = seasonController.get(existeSeason(categoryName.getText()));
            if (season != null) {

                showCategory.setWrapText(true);

                showCategory.setText("Season Information:\n" +
                        "Number: " + season.getNumber() + "\n" +
                        "Number id :" + season.getId());
                messageError.setText("");

            } else {
                showCategory.setWrapText(true);
                messageError.setText("The season does not exist");
            }
        }

    }

    public int existeSeason(String nameMovie) {
        for (Season season : seasonController.getAll()) {
            if (season.getNumber() == Integer.parseInt(nameMovie)) {
                return season.getId();

            }
        }
        return -1;
    }

}