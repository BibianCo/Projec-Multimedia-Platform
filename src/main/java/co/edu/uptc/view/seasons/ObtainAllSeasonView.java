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

public class ObtainAllSeasonView implements Initializable {

    @FXML
    private Label messageError;

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

    private Season serie;

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

        loadItems();

    }

    private void loadItems() {
        tableView.getItems().setAll(new ArrayList<>());
        tableView.getItems().addAll(seasonController.getAll());
    }

    @FXML
    private void sceneMenu() throws IOException {
        Main.setRoot("menu-crud-season");
    }

    @FXML
    private void ObtainAllSeason() throws IOException {
        loadItems();
    }

}
