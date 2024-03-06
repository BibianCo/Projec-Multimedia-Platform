package co.edu.uptc.controller;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Category;
import co.edu.uptc.model.Episode;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Serie;
import co.edu.uptc.persistence.FilePersistence;

public class SerieControllerTest {

    private FilePersistence<Serie> infilePersistenceSerie;
    private FilePersistence<Season> inFilePersistenceSeason;
    private FilePersistence<Category> inFilePeristenceCategory;
    private FilePersistence<Episode> inFilePeristenceEpisode;

    private CategoryController categoryController;
    private SerieController serieController;
    private SeasonController seasonControllers;
    private EpisodeController episodeController;

    @Before
    public void setup() {
        Type type = new TypeToken<ArrayList<Season>>() {
        }.getType();
        Type type2 = new TypeToken<ArrayList<Serie>>() {
        }.getType();
        Type type3 = new TypeToken<ArrayList<Category>>() {
        }.getType();
        Type type4 = new TypeToken<ArrayList<Episode>>() {
        }.getType();

        this.inFilePeristenceCategory = new FilePersistence<>(type3, "category");
        this.infilePersistenceSerie = new FilePersistence<>(type2, "serie");
        this.inFilePersistenceSeason = new FilePersistence<>(type, "season");
        this.inFilePeristenceEpisode = new FilePersistence<>(type4, "episode");

        this.categoryController = new CategoryController(inFilePeristenceCategory);
        this.serieController = new SerieController(infilePersistenceSerie, categoryController);
        this.seasonControllers = new SeasonController(inFilePersistenceSeason, serieController);
        this.episodeController = new EpisodeController(inFilePeristenceEpisode, seasonControllers);
        infilePersistenceSerie.createFile();
        inFilePeristenceCategory.createFile();
        inFilePersistenceSeason.createFile();
        inFilePeristenceEpisode.createFile();
        // adicionar categorias
        Category category = new Category(5, "gold");
        categoryController.add(category);

    }

    @Test
    public void addSerie() {

        Serie serie = new Serie(45, "juan", "asdasd", LocalDate.parse("2003-02-03"));
        Season season = new Season(5, 5, 45);

        serie.setCategories(categoryController.getAll());
        assertTrue(serieController.add(serie));

        assertTrue(seasonControllers.add(season));
        serie.setSeasons(seasonControllers.getAll());

        assertTrue(serieController.update(45, serie));

    }

}